package ru.topjava.basejava.model;

import java.util.HashMap;
import java.util.Map;

public class CompanySection extends Section {
    private Map<String, Company> companies = new HashMap<>();

    public void addCompany(Company company){
        companies.put(company.getName(),company);
    }
    public Company getCompany(String companyName){
        return companies.get(companyName);
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}';
    }
}
