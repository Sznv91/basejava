package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class DataSerializeStrategyStorage implements StorageStrategy {

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        int contactCount = r.getContacts().size();
        int sectionCount = r.getSections().size();

        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeUTF(r.getUuid());
        dos.writeUTF(r.getFullName());
        dos.writeInt(contactCount);

        for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
            dos.writeUTF(entry.getKey().name());
            dos.writeUTF(entry.getValue());
        }
        dos.writeInt(sectionCount);

        for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()) {
            switch (entry.getKey().ordinal()) {
                case 0:
                case 1:
                    dos.writeUTF(String.valueOf(entry.getValue()));
                    break;
                case 2:
                case 3:
                    writeListSection(dos, (ListSection) r.getSection(entry.getKey()));
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        }

        dos.close();
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        Resume result;
        DataInputStream dis = new DataInputStream(inputStream);
        String uuid = dis.readUTF();
        String fullName = dis.readUTF();
        result = new Resume(uuid, fullName);
        int contactCount = dis.readInt();

        for (int i = 0; i < contactCount; i++) {
            result.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
        }

        int sectionCounter = dis.readInt();
        result.setSection(SectionType.PERSONAL, new TextSection(dis.readUTF()));
        result.setSection(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
        result.setSection(SectionType.ACHIEVEMENT, readListSection(dis));
        result.setSection(SectionType.QUALIFICATIONS, readListSection(dis));
        dis.close();
        return result;
    }


    private void writeListSection(DataOutputStream dataOutputStream, ListSection section) throws IOException {
        dataOutputStream.writeInt(section.getContent().size());
        for (Object content : section.getContent()) {
            dataOutputStream.writeUTF((String) content);
        }
    }

    private ListSection readListSection(DataInputStream inputStream) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        int listCounter = inputStream.readInt();
        for (int i = 0; i < listCounter; i++) {
            result.add(inputStream.readUTF());
        }
        return new ListSection(result);
    }

}
