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
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey().name()) {
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
            if (sectionCounter > 0) {
                result.setSection(SectionType.PERSONAL, sectionSelector(dis, SectionType.PERSONAL));
                result.setSection(SectionType.OBJECTIVE, sectionSelector(dis, SectionType.OBJECTIVE));
                result.setSection(SectionType.ACHIEVEMENT, sectionSelector(dis, SectionType.ACHIEVEMENT));
                result.setSection(SectionType.QUALIFICATIONS, sectionSelector(dis, SectionType.QUALIFICATIONS));
                result.setSection(SectionType.EXPERIENCE, sectionSelector(dis, SectionType.EXPERIENCE));
                result.setSection(SectionType.EDUCATION, sectionSelector(dis, SectionType.EDUCATION));
            }
        }
        return result;
    }


    private void writeListSection(DataOutputStream dataOutputStream, ListSection section) throws IOException {
        if (section != null) {
            dataOutputStream.writeInt(section.getContent().size());
            for (String content : section.getContent()) {
                dataOutputStream.writeUTF(content);
            }
        } else {
            dataOutputStream.writeInt(0);
        }
    }

    private ListSection readListSection(DataInputStream inputStream) throws IOException {
        int listCounter = inputStream.readInt();
        if (listCounter == 0) {
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < listCounter; i++) {
            result.add(inputStream.readUTF());
        }
        return new ListSection(result);
    }

    private void writeCompanySections(DataOutputStream dataOutputStream, CompanySection section) throws IOException {
        if (section != null) {
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
        } else {
            dataOutputStream.writeInt(0);
        }
    }

    private CompanySection readCompanySection(DataInputStream dataInputStream) throws IOException {

        int companyCount = dataInputStream.readInt();
        if (companyCount > 0) {
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
        } else {
            return null;
        }
    }

    private AbstractSection sectionSelector(DataInputStream dis, SectionType type) throws IOException {
        String sectionType = dis.readUTF();
        if (sectionType.equals(type.name())) {
            switch (sectionType) {
                case "PERSONAL":
                case "OBJECTIVE":
                    String readText = dis.readUTF();
                    if (readText.equals("null")) {
                        return null;
                    }
                    return new TextSection(readText);
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
        return null;
    }

}
