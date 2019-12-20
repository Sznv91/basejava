package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.*;

public class DataSerializeStrategyStorage implements StorageStrategy {

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            writeCollection(dos, r.getContacts().entrySet(), contacts -> {
                dos.writeUTF(contacts.getKey().name());
                dos.writeUTF(contacts.getValue());
            });

            writeCollection(dos, r.getSections().entrySet(), sections -> {
                dos.writeUTF(sections.getKey().name());
                switch (sections.getKey().name()) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        dos.writeUTF(String.valueOf(sections.getValue()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        writeListSection(dos, (ListSection) sections.getValue());
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        writeCompanySections(dos, ((CompanySection) sections.getValue()));
                        break;
                    default:
                        break;
                }
            });
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void writeListSection(DataOutputStream dataOutputStream, ListSection section) throws IOException {
        writeCollection(dataOutputStream, section.getContent(), content -> dataOutputStream.writeUTF(content));
    }

    private void writeCompanySections(DataOutputStream dataOutputStream, CompanySection section) throws IOException {
        writeCollection(dataOutputStream, section.getCompanies(), organization -> {
            dataOutputStream.writeUTF(organization.getName());
            dataOutputStream.writeUTF(organization.getUrl());
            writeCollection(dataOutputStream, organization.getPositionsList(), position -> {
                dataOutputStream.writeUTF(position.getTitle());
                dataOutputStream.writeUTF(position.getDescription());
                dataOutputStream.writeUTF(position.getStartDate().toString());
                dataOutputStream.writeUTF(position.getEndDate().toString());
            });
        });
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

    private ListSection readListSection(DataInputStream inputStream) throws IOException {
        int listCounter = inputStream.readInt();

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < listCounter; i++) {
            result.add(inputStream.readUTF());
        }
        return new ListSection(result);
    }

    private CompanySection readCompanySection(DataInputStream dataInputStream) throws IOException {
        int companyCount = dataInputStream.readInt();
        List<Organization> organizationList = new ArrayList<>();
        for (int i = 0; i < companyCount; i++) {
            String name = dataInputStream.readUTF();
            String url = dataInputStream.readUTF();
            if (url.equals("")) {
                url = null;
            }
            List<Organization.Position> positionList = new ArrayList<>();

            int positionCount;
            positionCount = dataInputStream.readInt();
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

}
