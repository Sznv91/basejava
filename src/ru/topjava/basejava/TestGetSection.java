package ru.topjava.basejava;

import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.model.SectionType;

public class TestGetSection {

    public static void main (String args[]){
        Resume r1 = new Resume("123","IvanGovnov","Genius");
        System.out.println(r1.getSection(SectionType.PERSONAL));
    }

}
