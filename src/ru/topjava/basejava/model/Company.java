package ru.topjava.basejava.model;

import java.time.YearMonth;

public class Company {
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String description;
    private final String companyName;
    private final String url;

    public Company(String companyName, YearMonth startDate, YearMonth endDate,
                          String description, String url){
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.url = url;
    }
}
