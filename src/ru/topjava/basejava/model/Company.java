package ru.topjava.basejava.model;

public class Company {

    private final String companyName;
    private String url;

    public Company(String companyName, String url) {
        this.companyName = companyName;
        this.url = url;
    }

    public Company (String companyName){
        this.companyName = companyName;
    }

    public String getName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
