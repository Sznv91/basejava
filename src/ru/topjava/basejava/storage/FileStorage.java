package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage extends AbstractFileStorage {

    private int readCounter;

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
                writer.write("<" + String.valueOf(section) + ">" + System.lineSeparator());
                writer.write(String.valueOf(resume.getSection(section)) + System.lineSeparator());
                writer.write("</" + String.valueOf(section) + ">" + System.lineSeparator());
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

    @Override
    protected Resume doRead(File file) {
        List<String> readFromFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readFromFile.add(line);
            }
        } catch (IOException e) {
            // log error
        }

        Resume result = new Resume(readFromFile.get(0), readFromFile.get(1));
        result.setContact(ContactType.PHONE, readFromFile.get(2));
        result.setContact(ContactType.SKYPE, readFromFile.get(3));
        result.setContact(ContactType.EMAIL, readFromFile.get(4));
        result.setContact(ContactType.LINKEDIN, readFromFile.get(5));
        result.setContact(ContactType.GITHUB, readFromFile.get(6));
        result.setContact(ContactType.STACKOVERFLOW, readFromFile.get(7));
        result.setContact(ContactType.HOMEPAGE, readFromFile.get(8));
        result.setSection(SectionType.PERSONAL, new TextSection(readFromFile.get(10)));
        result.setSection(SectionType.OBJECTIVE, new TextSection(readFromFile.get(13)));

        readCounter = 14;

        ListSection achievements = new ListSection(searchSection(String.valueOf
                (SectionType.ACHIEVEMENT), readFromFile));
        ListSection qualification = new ListSection(searchSection(String.valueOf
                (SectionType.QUALIFICATIONS), readFromFile));
        result.setSection(SectionType.ACHIEVEMENT,achievements);
        result.setSection(SectionType.QUALIFICATIONS,qualification);
        return result;
    }

    private List<String> searchSection(String SectionName, List<String> original) {
        List<String> result = new ArrayList<>();
        SectionName = "<" + SectionName + ">";
        int startSearch = readCounter;
        int endSearch = 0;

        while (true) {
            if (original.get(startSearch).equals(SectionName)) {
                startSearch++;
                endSearch = startSearch;
                SectionName = "</" + SectionName.substring(1, SectionName.length());
                while (!original.get(endSearch).equals(SectionName)) {
                    result.add(original.get(endSearch));
                    endSearch++;
                }
                readCounter = endSearch;
                break;
            } else {
                if (startSearch < original.size()) {
                    startSearch++;
                } else {
                    break;
                }
            }
        }
        return result;
    }
}