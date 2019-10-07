package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class CompanySection extends Section {
    private List<Company> companies = new ArrayList<>();

    public void addCompany(Company company) {
        companies.add(company);
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return companies.equals(that.companies);
    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }
}
