package ru.topjava.basejava.model;

import ru.topjava.basejava.utils.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    public Organization() {
    }

    private final List<Position> positions = new ArrayList<>();

    private String name;
    private String url;

    public Organization(String name, String url, Position... positions) {
        Objects.requireNonNull(positions, "Position must not be null");
        this.name = name;
        this.url = url;
        this.positions.addAll(Arrays.asList(positions));
    }

    public Organization(String name, Position... positions) {
        Objects.requireNonNull(positions, "Position must not be null");
        this.name = name;
        this.positions.addAll(Arrays.asList(positions));
    }

    public Organization(String name, String url, List<Position> positions) {
        Objects.requireNonNull(positions, "Position must not be null");
        this.name = name;
        this.url = url;
        this.positions.addAll(positions);
    }

    public List<Position> getPositionsList() {
        return positions;
    }

    public void addPeriod(Position... positions) {
        this.positions.addAll(Arrays.asList(positions));
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        if (url == null) {
            return "empty";
        }
        return url;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "positions=" + positions +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization organization = (Organization) o;

        if (!positions.equals(organization.positions)) return false;
        if (!Objects.equals(name, organization.name)) return false;
        return Objects.equals(url, organization.url);
    }

    @Override
    public int hashCode() {
        int result = positions.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {

        public Position() {
        }

        @XmlJavaTypeAdapter(DateAdapter.class)
        private YearMonth startDate;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private YearMonth endDate;
        private String title;
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

        public String getStartDate() {
            return startDate.toString();
        }

        public String getEndDate() {
            return endDate.toString();
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            if (description == null) {
                return "empty";
            }
            return description;
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

            if (!Objects.equals(startDate, position.startDate)) return false;
            if (!Objects.equals(endDate, position.endDate)) return false;
            if (!Objects.equals(title, position.title)) return false;
            return Objects.equals(description, position.description);
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
