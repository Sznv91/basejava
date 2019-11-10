package ru.topjava.basejava.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private final List<Position> positions = new ArrayList<>();

    private final String name;
    private String url;

    public Organization(String name, String url, Position... positions) {
        Objects.requireNonNull(positions, "Position must not be null");
        this.name = name;
        this.url = url;
        this.positions.addAll(Arrays.asList(positions));
    }

    public Organization(String name, Position... positions) {
        Objects.requireNonNull(positions,"Position must not be null");
        this.name = name;
        this.positions.addAll(Arrays.asList(positions));
    }

    public void addPeriod(Position... positions) {
        this.positions.addAll(Arrays.asList(positions));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String resultPeriods = "";
        for (Position item : positions) {
            resultPeriods += item + System.lineSeparator();
        }
        resultPeriods = resultPeriods.substring(0, resultPeriods.length() - 2);
        return "<company>" + System.lineSeparator() +
                name + System.lineSeparator() +
                url + System.lineSeparator() +
                resultPeriods + System.lineSeparator() +
                "</company>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization organization = (Organization) o;

        if (positions != null ? !positions.equals(organization.positions) : organization.positions != null) return false;
        if (name != null ? !name.equals(organization.name) : organization.name != null) return false;
        return url != null ? url.equals(organization.url) : organization.url == null;
    }

    @Override
    public int hashCode() {
        int result = positions != null ? positions.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    public static class Position implements Serializable {
        private final YearMonth startDate;
        private final YearMonth endDate;
        private final String title;
        private String description;

        public Position(YearMonth startDate, YearMonth endDate, String title) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
        }

        public Position(YearMonth startDate, YearMonth endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        @Override
        public String toString() {
            return "<CompanyPeriod>" + System.lineSeparator() +
                    startDate.getYear() + System.lineSeparator() +
                    startDate.getMonth() + System.lineSeparator() +
                    endDate.getYear() + System.lineSeparator() +
                    endDate.getMonth() + System.lineSeparator() +
                    title + System.lineSeparator() +
                    description + System.lineSeparator() +
                    "</CompanyPeriod>";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (startDate != null ? !startDate.equals(position.startDate) : position.startDate != null) return false;
            if (endDate != null ? !endDate.equals(position.endDate) : position.endDate != null) return false;
            if (title != null ? !title.equals(position.title) : position.title != null) return false;
            return description != null ? description.equals(position.description) : position.description == null;
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
}
