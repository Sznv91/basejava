package ru.topjava.basejava.model;

public class Section{

    private TextSection personal;
    private TextSection objective;

    public Section(String personal) {
        this.personal = new TextSection(personal);
    }

    public Section getSection(SectionType type){
        switch (type){
            case PERSONAL:
                return personal;
            case OBJECTIVE:
                return objective;
        }
        return null;
    }

    Section() {
    }
}
