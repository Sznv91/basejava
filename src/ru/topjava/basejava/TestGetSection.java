package ru.topjava.basejava;

import ru.topjava.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class TestGetSection {

    public static void main(String args[]) {
        Resume r1 = new Resume("123", "Test User");
        TextSection personal = new TextSection("Personal block");
        TextSection objective = new TextSection("OBJ Block");
        ListSection achievements = new ListSection(new ArrayList<>(Arrays.asList("Test writer"
                , "good boy", "just text")));
        ListSection qualification = new ListSection("Item One", "item two",
                "anywhere", "anytime");
        Company c1 = new Company("TestCompany", YearMonth.of(2010,11),
                YearMonth.of(2012,12),"boring company","www.test.net");
        Company c2 = new Company("Company two", YearMonth.of(2013,9),
                YearMonth.of(2014,1),"not boring company",
                "www.speedtest.net");
        CompanySection experience = new CompanySection();
        experience.addCompany(c1);
        experience.addCompany(c2);
        Company c3 = new Company("EDU comp", YearMonth.of(2001,9),
                YearMonth.of(2010,9),"Education",
                "www.edu.net");
        CompanySection education = new CompanySection();
        education.addCompany(c3);

        r1.setSection(SectionType.PERSONAL,personal);
        r1.setSection(SectionType.OBJECTIVE, objective);
        r1.setSection(SectionType.ACHIEVEMENT,achievements);
        r1.setSection(SectionType.QUALIFICATIONS, qualification);
        r1.setSection(SectionType.EXPERIENCE, experience);
        r1.setSection(SectionType.EDUCATION,education);

        System.out.println(r1.getSection(SectionType.EXPERIENCE));
    }

}
