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

            ExceptInterface exceptInterface = () -> {
            };

            writeWithException(r.getContacts(), dos, exceptInterface);

            int sectionCount = r.getSections().size();
            dos.writeInt(sectionCount);

            writeWithException(r.getSections(),dos,exceptInterface);
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

    protected static void writeListSection(DataOutputStream dataOutputStream, ListSection section) throws IOException {
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

    protected static void writeCompanySections(DataOutputStream dataOutputStream, CompanySection section) throws IOException {
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

    private <K,V> void writeWithException(Map<K, V> map, DataOutputStream dos, ExceptInterface exceptInterface) throws IOException {

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey().getClass().equals(ContactType.class)){
                exceptInterface.writeContacts((ContactType) entry.getKey(), (String) entry.getValue(), dos);
            } else {
                exceptInterface.writeSections((SectionType) entry.getKey(), (AbstractSection) entry.getValue(), dos);
            }
        }
    }
}
