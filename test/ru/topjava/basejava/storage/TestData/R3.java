package ru.topjava.basejava.storage.TestData;

import ru.topjava.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class R3 {
    private static Resume r3;

    private R3() {
        fillResume();
    }

    public static Resume getResume() {
        if (r3 == null) {
            new R3();
        }
        return r3;
    }

    private void fillResume() {
        String uuid = "UUID_3";
        String fullName = "Carl  Johnson";

        r3 = new Resume(uuid, fullName);

        r3.setContact(ContactType.PHONE, "+7(928) 222-33-44");
        r3.setContact(ContactType.SKYPE, "CarlJoh");
        r3.setContact(ContactType.EMAIL, "Carl@gmail.com");
        r3.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/CarlJ");
        r3.setContact(ContactType.GITHUB, "https://github.com/crlj");
        r3.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/222");
        //r2.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection personal = new TextSection("Является первым чернокожим (афроамериканским) протагонистом GTA вселенной 3D");

        TextSection objective = new TextSection("Лидер уличной банды Grove Street Families в городе Лос-Сантос");

        ListSection achievements = new ListSection(new ArrayList<>(Arrays.asList("Родился в Лос-Сантосе в 1968 году"
                , "Менеджер Мэдд Догга")));

        ListSection qualification = new ListSection("Автоугонщик"
                , "Захватыватель территорий");

        Organization.Position p1 = new Organization.Position(YearMonth.of(1987, 10), YearMonth.now(), "Член банды", "роль");
        Organization c1 = new Organization("Grove Street Families", p1);

        CompanySection experience = new CompanySection();
        experience.addCompany(c1);

        Organization.Position p9 = new Organization.Position(YearMonth.of(2010, 9), YearMonth.of(2014, 9), "Факультет никополигональных моделей");
        Organization c9 = new Organization("Несуществующий университет", "https://www.ya.ru", p9);

        Organization.Position p10 = new Organization.Position(YearMonth.of(2007, 9), YearMonth.of(2010, 9), "Наплавление Low resolution of textures");
        Organization c10 = new Organization("Несуществующий лицей", "https://www.youtube.ru", p10);

        CompanySection education = new CompanySection();
        education.addCompany(c9);
        education.addCompany(c10);

        r3.setSection(SectionType.PERSONAL, personal);
        r3.setSection(SectionType.OBJECTIVE, objective);
        r3.setSection(SectionType.ACHIEVEMENT, achievements);
        r3.setSection(SectionType.QUALIFICATIONS, qualification);
        r3.setSection(SectionType.EXPERIENCE, experience);
        r3.setSection(SectionType.EDUCATION, education);

    }
}
