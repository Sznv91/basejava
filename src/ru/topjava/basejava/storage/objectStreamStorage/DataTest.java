package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.ResumeTestData;
import ru.topjava.basejava.model.ContactType;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.model.SectionType;
import ru.topjava.basejava.storage.PathStorage;

public class DataTest {

    private static final String UUID_TEST = "UUID-1";
    private static ResumeTestData rtd = new ResumeTestData(UUID_TEST, "Григорий Кислин");
    private static Resume r1 = rtd.getR1();
    private final static String PATH = "./storage";
    private static PathStorage storage = new PathStorage(PATH, new DataSerializeStrategyStorage());

    public static void main(String[] args) {
        storage.clear();
        storage.save(r1);

        Resume tst = storage.get(UUID_TEST);
        System.out.println(tst.getFullName());
        System.out.println(tst.getUuid());
        System.out.println(tst.getContact(ContactType.GITHUB));
        System.out.println(tst.getSection(SectionType.PERSONAL));
        System.out.println(tst.getSection(SectionType.ACHIEVEMENT));
        System.out.println(tst.getSection(SectionType.QUALIFICATIONS));
    }
}
