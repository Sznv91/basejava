package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.*;

import java.io.*;
import java.util.Map;

public class DataSerializeStrategyStorage implements StorageStrategy {

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        System.out.println(r.getSection(SectionType.PERSONAL));
        int contactCount = r.getContacts().size();
        int sectionCount = r.getSections().size();
        for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()){
            //entry.getKey().ordinal();

            System.out.println(entry.getKey().ordinal());
            System.out.println(entry.getKey());

        }

        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeUTF(r.getUuid());
        dos.writeUTF(r.getFullName());
        dos.writeInt(contactCount);
        for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
            dos.writeUTF(entry.getKey().name());
            dos.writeUTF(entry.getValue());
        }
        dos.writeInt(sectionCount);

        //TODO неправильно. Надо сериализовывать каждую секцию отдельно.
        for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()) {
            //entry.getValue();
            //dos.writeUTF(entry.getKey().name());
            //dos.writeUTF(String.valueOf(entry.getValue()));
            switch (entry.getKey().ordinal()-1){
                case 0:
                case 1:
                    dos.writeUTF(String.valueOf(entry.getValue()));
                    break;
                case 2: //TODO подумать как лучше поступить с ListSection
                    break;
                case 3:
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
       // dis.readUTF();
        TextSection personal = new TextSection(dis.readUTF());
        //dis.readUTF();
        TextSection objective = new TextSection(dis.readUTF());
        result.setSection(SectionType.PERSONAL, personal);
        result.setSection(SectionType.OBJECTIVE, objective);
        dis.close();
        return result;
    }
}
