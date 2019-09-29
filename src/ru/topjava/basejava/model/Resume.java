package ru.topjava.basejava.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    private final String uuid;
    private final String fullName;
    private Section sectionCollector;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(fullName,"Full Name must not be null");
        Objects.requireNonNull(uuid, "UUID must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume (String uuid, String fullName, String personal){
        this.uuid = uuid;
        this.fullName = fullName;
        sectionCollector = new Section(personal);
    }

    public Section getSection (@NotNull SectionType type){
        return sectionCollector.getSection(type);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

}
