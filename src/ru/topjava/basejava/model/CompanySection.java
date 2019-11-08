package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class CompanySection extends AbstractSection {
    private List<Organization> companies = new ArrayList<>();

    public void addCompany(Organization organization) {
        companies.add(organization);
    }

    @Override
    public String toString() {
        String result = "";
        for (Organization item : companies) {
            result += item + System.lineSeparator();
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return companies != null ? companies.equals(that.companies) : that.companies == null;
    }

    @Override
    public int hashCode() {
        return companies != null ? companies.hashCode() : 0;
    }
}
