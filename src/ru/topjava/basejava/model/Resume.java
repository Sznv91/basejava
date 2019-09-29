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
    //private Section sectionCollector;
    private Map<ContactType, String> contacts = new EnumMap<ContactType, String>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<SectionType, Section>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(fullName,"Full Name must not be null");
        Objects.requireNonNull(uuid, "UUID must not be null");
        this.uuid = uuid;
        contacts.put(ContactType.FULL_NAME,fullName);
    }

    public Section getSection (@NotNull SectionType type){
        return sections.get(type);
    }

    public String getUuid() {
        return uuid;
    }

    public String getContact(ContactType type) {
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
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, contacts, sections);
    }
}
