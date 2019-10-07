package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Company {

    private final List<CompanyPeriod> companyPeriods = new ArrayList<>();

    private final String companyName;
    private String url;

    public Company(String companyName, String url, CompanyPeriod... periods) {
        Objects.nonNull(periods);
        this.companyName = companyName;
        this.url = url;
        companyPeriods.addAll(Arrays.asList(periods));
    }

    public Company(String companyName, CompanyPeriod... periods) {
        Objects.nonNull(periods);
        this.companyName = companyName;
        companyPeriods.addAll(Arrays.asList(periods));
    }

    public void addPeriod(CompanyPeriod... periods) {
        companyPeriods.addAll(Arrays.asList(periods));
    }

    public String getName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", url='" + url + '\'' +
                "companyPeriods=" + companyPeriods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!companyPeriods.equals(company.companyPeriods)) return false;
        if (!companyName.equals(company.companyName)) return false;
        return url != null ? url.equals(company.url) : company.url == null;
    }

    @Override
    public int hashCode() {
        int result = companyPeriods.hashCode();
        result = 31 * result + companyName.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
