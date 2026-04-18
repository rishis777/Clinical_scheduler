package com.clinic;

public class Staff extends Person {
    private String role;
    private String shift;

    public Staff(String id, String name, String contactInfo, String role, String shift) {
        super(id, name, contactInfo);
        this.role = role;
        this.shift = shift;
    }

    public String getRole() { return role; }
    public String getShift() { return shift; }

    @Override
    public String displayInfo() {
        return "Staff ID: " + getId() + ", Name: " + name + ", Role: " + role + ", Shift: " + shift;
    }
}
