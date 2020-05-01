package ru.topjava.basejava.storage.TestData;

import ru.topjava.basejava.model.Resume;

public class ResumeTestData {

    private Resume r1 = R1.getResume();
    private Resume r4 = R4.getResume();
    private Resume r2 = R2.getResume();
    private Resume r3 = R3.getResume();

    public Resume getR1 (){
        return r1;
    }
    public Resume getR4 (){
        return r4;
    }
    public Resume getR2 () {
        return r2;
    }
    public Resume getR3 () {
        return r3;
    }
}
