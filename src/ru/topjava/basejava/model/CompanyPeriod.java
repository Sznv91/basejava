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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyPeriod that = (CompanyPeriod) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
