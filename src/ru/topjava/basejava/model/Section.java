package ru.topjava.basejava.model;

public class Section{

    private TextSection personal;
    private TextSection objective;
    private ListSection achievement;
    private ListSection qualifications;
    private CompanySection experience;
    private CompanySection education;

    public Section(String personal) {
        this.personal = new TextSection(personal);
    }

    public Section getSection(SectionType type){
        switch (type){
            case PERSONAL:
                return personal;
            case OBJECTIVE:
                return objective;
            case ACHIEVEMENT:
                return achievement;
            case QUALIFICATIONS:
                return qualifications;
            case EXPERIENCE:
                return experience;
            case EDUCATION:
                return education;
        }
        return null;
    }

    Section() {
    }
}
