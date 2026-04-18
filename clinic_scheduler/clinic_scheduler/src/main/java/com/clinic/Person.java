package com.clinic;

public abstract class Person {
    private String id;
    protected String name;
    protected String contactInfo;

    public Person(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public abstract String displayInfo();
}
