package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSerializeStrategyStorage implements StorageStrategy {

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            int contactCount = r.getContacts().size();
            dos.writeInt(contactCount);

            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            int sectionCount = r.getSections().size();
            dos.writeInt(sectionCount);

            for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()) {
                String name = entry.getKey().name();
                switch (name) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        dos.writeUTF(name);
                        dos.writeUTF(String.valueOf(entry.getValue()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        dos.writeUTF(name);
                        writeListSection(dos, (ListSection) r.getSection(entry.getKey()));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        dos.writeUTF(name);
                        writeCompanySections(dos, ((CompanySection) r.getSection(entry.getKey())));
                        break;
                    default:
                        break;
                }
            }
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
                result.setSection(type, sectionSelector(dis, type));
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

    private void writeCompanySections(DataOutputStream dataOutputStream, CompanySection section) throws IOException {List<Organization> orgList = section.getCompanies();
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

    private AbstractSection sectionSelector(DataInputStream dis, SectionType type) throws IOException {
        switch (type.name()) {
            case "PERSONAL":
            case "OBJECTIVE":
                return new TextSection(dis.readUTF());
            case "ACHIEVEMENT":
            case "QUALIFICATIONS":
                return readListSection(dis);
            case "EXPERIENCE":
            case "EDUCATION":
                return readCompanySection(dis);
            default:
                return null;
        }
    }

}
