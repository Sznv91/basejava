package ru.topjava.basejava.model;

import java.time.YearMonth;

public class CompanyPeriod {
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String title;
    private String description;

    public CompanyPeriod(YearMonth startDate, YearMonth endDate, String title) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public CompanyPeriod(YearMonth startDate, YearMonth endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "CompanyPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
