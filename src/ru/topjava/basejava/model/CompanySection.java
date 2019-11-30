package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {

    public CompanySection() {
    }

    private final List<Organization> companies = new ArrayList<>();

    public void addCompany(Organization organization) {
        companies.add(organization);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Organization item : companies) {
            result.append(item).append(System.lineSeparator());
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }
}
