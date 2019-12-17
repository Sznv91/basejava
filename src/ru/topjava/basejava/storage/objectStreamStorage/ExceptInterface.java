package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.*;

import java.io.DataOutputStream;
import java.io.IOException;

import static ru.topjava.basejava.storage.objectStreamStorage.DataSerializeStrategyStorage.writeCompanySections;

@FunctionalInterface
public interface ExceptInterface<Key, Value> {

    default <Key extends ContactType, Value extends String> void writeContacts(Key key, Value value, DataOutputStream dos) throws IOException {
        dos.writeUTF(key.name());
        dos.writeUTF(value);
    }

    default <Key extends SectionType, Value extends AbstractSection> void writeSections(Key key, Value value, DataOutputStream dos) throws IOException {
        dos.writeUTF(key.name());
        switch (key.name()) {
            case "PERSONAL":
            case "OBJECTIVE":
                dos.writeUTF(String.valueOf(value));
                break;
            case "ACHIEVEMENT":
            case "QUALIFICATIONS":
                DataSerializeStrategyStorage.writeListSection(dos, (ListSection) value);
                break;
            case "EXPERIENCE":
            case "EDUCATION":
                writeCompanySections(dos, ((CompanySection) value));
                break;
            default:
                break;
        }
    }

    void writeSomething();
}
