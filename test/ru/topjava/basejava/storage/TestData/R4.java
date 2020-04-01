package ru.topjava.basejava.storage.TestData;

import ru.topjava.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class R4 {
    private static Resume r4;

    private R4() {
        fillResume();
    }

    public static Resume getResume() {
        if (r4 == null) {
            new R4();
        }
        return r4;
    }

    private void fillResume() {
        String uuid = "UUID_4";
        String fullName = "Михаил Сазонов";

        r4 = new Resume(uuid, fullName);

        r4.setContact(ContactType.PHONE, "+7(928) 751-83-19");
        r4.setContact(ContactType.SKYPE, "sazonov91");
        r4.setContact(ContactType.EMAIL, "sazonov91@gmail.com");
        //r2.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        r4.setContact(ContactType.GITHUB, "https://github.com/sznv91");
        //r2.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        //r2.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection personal = new TextSection("Студент курса Base Java");

        TextSection objective = new TextSection("Заинтересованность процессом");

        ListSection achievements = new ListSection(new ArrayList<>(Arrays.asList("Дошел до HW 16."
                , "Создание каждого обьекта Resume Test Date в отдельном методе")));

        ListSection qualification = new ListSection("JSE "
                , "Version control: Git"
                , "DB: PostgreSQL"
                , "Languages: Java"
                , "XML, SQL, JSON"
                , "Родной русский");

        Organization.Position p1 = new Organization.Position(YearMonth.of(2019, 10), YearMonth.now(), "Участник курсов", "web app");
        Organization c1 = new Organization("Java Online Projects", "http://javaops.ru/", p1);

        Organization.Position p2 = new Organization.Position(YearMonth.of(2017, 1), YearMonth.now(), "Cистемный администратор", "Бумагомарание, отчеты, контрпродуктивность, корпоратинвый бюррократизм");
        Organization c2 = new Organization("Тандер", "https://www.magnit.ru/", p2);

        Organization.Position p3 = new Organization.Position(YearMonth.of(2016, 4), YearMonth.of(2017, 1), "Мастер производственного предприятия", "Заводской бюррократизм, формализация очевидных вещей");
        Organization c3 = new Organization("Авангард", p3);

        Organization.Position p4 = new Organization.Position(YearMonth.of(2010, 7), YearMonth.of(2016, 4), "Инженер судебных участков", "Бюррократия, неоплачиваемые коммандировки, некомпитентное руководство");
        Organization c4 = new Organization("Департамент по обеспечению деятельности мировых судей", "http://www.msudro.ru/", p4);

        CompanySection experience = new CompanySection();
        experience.addCompany(c1);
        experience.addCompany(c2);
        experience.addCompany(c3);
        experience.addCompany(c4);

        Organization.Position p9 = new Organization.Position(YearMonth.of(2010, 9), YearMonth.of(2014, 9), "Бакалавр техники и технологий в области телекоммуникаций.");
        Organization c9 = new Organization("ЮРГУЭС", "https://www.sssu.ru", p9);

        Organization.Position p10 = new Organization.Position(YearMonth.of(2007, 9), YearMonth.of(2010, 9), "Радиотехника");
        Organization c10 = new Organization("ПЛЭС ЮРГУЭС", "https://www.sssu.ru", p10);

        CompanySection education = new CompanySection();
        education.addCompany(c9);
        education.addCompany(c10);

        r4.setSection(SectionType.PERSONAL, personal);
        r4.setSection(SectionType.OBJECTIVE, objective);
        r4.setSection(SectionType.ACHIEVEMENT, achievements);
        r4.setSection(SectionType.QUALIFICATIONS, qualification);
        r4.setSection(SectionType.EXPERIENCE, experience);
        r4.setSection(SectionType.EDUCATION, education);

    }
}
