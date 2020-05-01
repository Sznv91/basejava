package ru.topjava.basejava.storage.TestData;

import ru.topjava.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class R2 {
    private static Resume r2;

    private R2() {
        fillResume();
    }

    public static Resume getResume() {
        if (r2 == null) {
            new R2();
        }
        return r2;
    }

    private void fillResume() {
        String uuid = "UUID_2";
        String fullName = "Boris Brirva";

        r2 = new Resume(uuid, fullName);

        r2.setContact(ContactType.PHONE, "+7(928) 111-22-33");
        r2.setContact(ContactType.SKYPE, "BorisBR");
        r2.setContact(ContactType.EMAIL, "Boris@gmail.com");
        //r2.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        r2.setContact(ContactType.GITHUB, "https://github.com/brs");
        r2.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/1111");
        //r2.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection personal = new TextSection("Резкий, как удар серпом по яйцам, жёсткий, как удар молотом — живой советский герб");

        TextSection objective = new TextSection("Торговец оружием");

        ListSection achievements = new ListSection(new ArrayList<>(Arrays.asList("Продал Smith & Wesson Model 681"
                , "Использует Colt 1911A1")));

        ListSection qualification = new ListSection("Владение холодным оружием"
                , "Владение огнестрельным"
                , "Феноменальная живучесть"
                , "Хрен попадешь");

        Organization.Position p1 = new Organization.Position(YearMonth.of(2000, 10), YearMonth.now(), "Участник", "роль");
        Organization c1 = new Organization("Большой куш", "https://ru.wikipedia.org/wiki/Большой_куш_(фильм,_2000)", p1);

        CompanySection experience = new CompanySection();
        experience.addCompany(c1);

        Organization.Position p9 = new Organization.Position(YearMonth.of(2010, 9), YearMonth.of(2014, 9), "Танцор всех жанров");
        Organization c9 = new Organization("Университет имени Рикардо Милоса", "https://www.mls.ru", p9);

        Organization.Position p10 = new Organization.Position(YearMonth.of(2007, 9), YearMonth.of(2010, 9), "Ученик");
        Organization c10 = new Organization("Школа", "https://www.sssu.ru", p10);

        CompanySection education = new CompanySection();
        education.addCompany(c9);
        education.addCompany(c10);

        r2.setSection(SectionType.PERSONAL, personal);
        r2.setSection(SectionType.OBJECTIVE, objective);
        r2.setSection(SectionType.ACHIEVEMENT, achievements);
        r2.setSection(SectionType.QUALIFICATIONS, qualification);
        r2.setSection(SectionType.EXPERIENCE, experience);
        r2.setSection(SectionType.EDUCATION, education);

    }
}
