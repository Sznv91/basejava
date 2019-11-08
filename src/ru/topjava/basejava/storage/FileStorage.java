package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.*;

import java.io.*;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class FileStorage extends AbstractFileStorage {

    private int readCounter;

    public FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume resume, FileOutputStream fileOutputStream) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileOutputStream, false);
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
    protected Resume doRead(InputStream file) {
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
        result.setSection(SectionType.ACHIEVEMENT, achievements);
        result.setSection(SectionType.QUALIFICATIONS, qualification);

        List expSection = searchSection(String.valueOf(SectionType.EXPERIENCE), readFromFile);
        List<Organization> organizationList = searchCompany(expSection);

        CompanySection companySection = new CompanySection();
        for (Organization organization : organizationList) {
            companySection.addCompany(organization);
        }
        result.setSection(SectionType.EXPERIENCE, companySection);

        List eduSection = searchSection(String.valueOf(SectionType.EDUCATION), readFromFile);
        List<Organization> educationList = searchCompany(eduSection);
        companySection = new CompanySection();
        for (Organization organization : educationList) {
            companySection.addCompany(organization);
        }
        result.setSection(SectionType.EDUCATION, companySection);
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

    private List<Organization> searchCompany(List<String> original) {
        List<Organization> result = new ArrayList<>();
        List<Organization.Position> companyPositions = null;
        String openTag = "<company>";
        String closeTag = "</company>";
        for (int i = 0; i < original.size(); i++) {
            if (original.get(i).equals(openTag)) {
                String name = original.get(i + 1);
                String url = original.get(i + 2);
                companyPositions = new ArrayList<>();
                i += 2;
                i = i + 1;
                while (!original.get(i).equals(closeTag)) {
                    if (original.get(i).equals("<CompanyPeriod>")) {
                        i++;
                        companyPositions.add(new Organization.Position(
                                YearMonth.of(Integer.parseInt(original.get(i)), Month.valueOf(original.get(i + 1))),
                                YearMonth.of(Integer.parseInt(original.get(i + 2)), Month.valueOf(original.get(i + 3))),
                                original.get(i + 4), original.get(i + 5)));
                        i += 5;
                    } else {
                        i++;
                    }
                }
                result.add(new Organization(name, url, companyPositions.toArray(new Organization.Position[companyPositions.size()])));
            }
        }
        return result;
    }
}
