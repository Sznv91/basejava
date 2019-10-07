package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class CompanySection extends Section {
    //private Map<Company, CompanyPeriod> companies = new HashMap<>();
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
}
