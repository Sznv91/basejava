package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Company {

    private final List<Period> periods = new ArrayList<>();

    private final String name;
    private String url;

    public Company(String name, String url, Period... periods) {
        Objects.nonNull(periods);
        this.name = name;
        this.url = url;
        this.periods.addAll(Arrays.asList(periods));
    }

    public Company(String name, Period... periods) {
        Objects.nonNull(periods);
        this.name = name;
        this.periods.addAll(Arrays.asList(periods));
    }

    public void addPeriod(Period... periods) {
        this.periods.addAll(Arrays.asList(periods));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + name + '\'' +
                ", url='" + url + '\'' +
                "companyPeriods=" + periods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (periods != null ? !periods.equals(company.periods) : company.periods != null) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        return url != null ? url.equals(company.url) : company.url == null;
    }

    @Override
    public int hashCode() {
        int result = periods != null ? periods.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
