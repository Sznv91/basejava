package ru.topjava.basejava.storage.TestData;

import ru.topjava.basejava.model.Resume;

public class ResumeTestData {

    Resume r1 = R1.getResume();
    Resume r4 = R4.getResume();

    public Resume getR1 (){
        return r1;
    }
    public Resume getR4 (){
        return r4;
    }
}
