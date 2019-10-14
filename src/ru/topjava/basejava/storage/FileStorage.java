package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.ContactType;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.model.SectionType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage extends AbstractFileStorage {

    public FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume resume, File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, false);
            writer.write(resume.getUuid() + System.lineSeparator());
            writer.write(resume.getFullName() + System.lineSeparator());

            for (ContactType contact : ContactType.values()) {
                writer.write(resume.getContact(contact) + System.lineSeparator());
            }
            for (SectionType section : SectionType.values()) {
                writer.write(String.valueOf(resume.getSection(section)) + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
