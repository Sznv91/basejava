package ru.topjava.basejava.model;

import java.util.HashMap;
import java.util.Map;

public class CompanySection extends Section {
    private Map<String, Company> companies = new HashMap<>();

    public void addCompany(String companyName, Company company){
        companies.put(companyName,company);
    }
    public Company getCompany(String companyName){
        return companies.get(companyName);
    }
}
