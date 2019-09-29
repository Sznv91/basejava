package ru.topjava.basejava.model;

import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    private final String uuid;
    //private final String fullName;
    private Section sectionCollector;
    private Map<Contacts, String> contacts = new EnumMap<Contacts, String>(Contacts.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(fullName,"Full Name must not be null");
        Objects.requireNonNull(uuid, "UUID must not be null");
        this.uuid = uuid;
        contacts.put(Contacts.FULL_NAME,fullName);
    }

    public Resume (String uuid, String fullName, String personal){
        this.uuid = uuid;
        contacts.put(Contacts.FULL_NAME,fullName);
        sectionCollector = new Section(personal);
    }

    public Section getSection (@NotNull SectionType type){
        return sectionCollector.getSection(type);
    }

    public String getUuid() {
        return uuid;
    }

    public String getContact(Contacts type) {
        return contacts.get(type);
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                Objects.equals(sectionCollector, resume.sectionCollector) &&
                Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, sectionCollector, contacts);
    }
}
