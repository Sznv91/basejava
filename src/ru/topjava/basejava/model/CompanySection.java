package ru.topjava.basejava.model;

import java.util.HashMap;
import java.util.Map;

public class CompanySection extends Section {
    private Map<Company, CompanyPeriod> companies = new HashMap<>();

    public void addCompany(Company company, CompanyPeriod period){
        companies.put(company,period);
    }
    public CompanyPeriod getCompany(Company company){
        return companies.get(company);
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}';
    }
}
