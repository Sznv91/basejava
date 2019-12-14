package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.*;
import java.util.function.BiConsumer;

public class DataSerializeStrategyStorage implements StorageStrategy {

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            int contactCount = r.getContacts().size();
            dos.writeInt(contactCount);

            /*for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }*/
            r.getContacts().forEach((k, v) -> {
                try {
                    dos.writeUTF(k.name());
                    dos.writeUTF(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            int sectionCount = r.getSections().size();
            dos.writeInt(sectionCount);

            r.getSections().forEach((k, v) -> {
                try {
                    dos.writeUTF(k.name());
                    switch (k.name()) {
                        case "PERSONAL":
                        case "OBJECTIVE":
                            dos.writeUTF(String.valueOf(v));
                            break;
                        case "ACHIEVEMENT":
                        case "QUALIFICATIONS":
                            writeListSection(dos, (ListSection) v);
                            break;
                        case "EXPERIENCE":
                        case "EDUCATION":
                            writeCompanySections(dos, ((CompanySection) v));
                            break;
                        default:
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            SimpleFuncInterface<ContactType, String> sfc = new SimpleFuncInterface<ContactType, String>() {

                @Override
                public void doWork(BiConsumer<? super ContactType, ? super String> action) {

                }
            };

            /*for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()) {
                String name = entry.getKey().name();
                dos.writeUTF(name);
                switch (name) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        dos.writeUTF(String.valueOf(entry.getValue()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        writeListSection(dos, (ListSection) r.getSection(entry.getKey()));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        writeCompanySections(dos, ((CompanySection) r.getSection(entry.getKey())));
                        break;
                    default:
                        break;
                }
            }*/
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        Resume result;
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            result = new Resume(uuid, fullName);
            int contactCount = dis.readInt();

            for (int i = 0; i < contactCount; i++) {
                result.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionCounter = dis.readInt();
            for (int i = 0; i < sectionCounter; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type.name()) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        result.setSection(type, new TextSection(dis.readUTF()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        result.setSection(type, readListSection(dis));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        result.setSection(type, readCompanySection(dis));
                        break;
                    default:
                        return null;
                }
            }
        }
        return result;
    }

    private void writeListSection(DataOutputStream dataOutputStream, ListSection section) throws IOException {
        dataOutputStream.writeInt(section.getContent().size());
        for (String content : section.getContent()) {
            dataOutputStream.writeUTF(content);
        }
    }

    private ListSection readListSection(DataInputStream inputStream) throws IOException {
        int listCounter = inputStream.readInt();

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < listCounter; i++) {
            result.add(inputStream.readUTF());
        }
        return new ListSection(result);
    }

    private void writeCompanySections(DataOutputStream dataOutputStream, CompanySection section) throws IOException {
        List<Organization> orgList = section.getCompanies();
        dataOutputStream.writeInt(orgList.size());
        for (Organization org : orgList) {
            dataOutputStream.writeInt(org.getPositionsList().size());
            dataOutputStream.writeUTF(org.getName());
            dataOutputStream.writeUTF(org.getUrl()); //If "" (empty string) then in the reader to assign null
            for (Organization.Position position : org.getPositionsList()) {
                dataOutputStream.writeUTF(position.getTitle());
                dataOutputStream.writeUTF(position.getDescription()); //If "" then in the reader to assign null
                dataOutputStream.writeUTF(position.getStartDate().toString());
                dataOutputStream.writeUTF(position.getEndDate().toString());
            }
        }
    }

    private CompanySection readCompanySection(DataInputStream dataInputStream) throws IOException {
        int companyCount = dataInputStream.readInt();
        List<Organization> organizationList = new ArrayList<>();
        for (int i = 0; i < companyCount; i++) {
            int positionCount;
            positionCount = dataInputStream.readInt();
            String name = dataInputStream.readUTF();
            String url = dataInputStream.readUTF();
            if (url.equals("")) {
                url = null;
            }
            List<Organization.Position> positionList = new ArrayList<>();

            for (int u = 0; u < positionCount; u++) {
                String title = dataInputStream.readUTF();
                String descriptionPosition = dataInputStream.readUTF();
                if (descriptionPosition.equals("")) {
                    descriptionPosition = null;
                }
                String startDate = dataInputStream.readUTF();
                String endDte = dataInputStream.readUTF();

                positionList.add(new Organization.Position(
                        YearMonth.parse(startDate), YearMonth.parse(endDte), title, descriptionPosition));
            }
            organizationList.add(new Organization(name, url, positionList));
            positionList.clear();
        }
        CompanySection result = new CompanySection();
        result.setCompaniesList(organizationList);

        return result;
    }

    private void writeWithException(Collection collection, DataOutputStream dos, SimpleFuncInterface sfi) throws IOException {

    }
}
