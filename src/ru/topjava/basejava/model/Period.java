package ru.topjava.basejava.model;

import java.time.YearMonth;

public class Period {
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String title;
    private String description;

    public Period(YearMonth startDate, YearMonth endDate, String title) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public Period(YearMonth startDate, YearMonth endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "<CompanyPeriod>" + System.lineSeparator() +
                startDate + System.lineSeparator() +
                endDate + System.lineSeparator() +
                title + System.lineSeparator() +
                description + System.lineSeparator() +
                "</CompanyPeriod>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (startDate != null ? !startDate.equals(period.startDate) : period.startDate != null) return false;
        if (endDate != null ? !endDate.equals(period.endDate) : period.endDate != null) return false;
        if (title != null ? !title.equals(period.title) : period.title != null) return false;
        return description != null ? description.equals(period.description) : period.description == null;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
