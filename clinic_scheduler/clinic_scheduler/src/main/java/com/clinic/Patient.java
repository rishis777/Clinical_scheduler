package com.clinic;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<MedicalRecord> medicalRecords;

    public Patient(String id, String name, String contactInfo) {
        super(id, name, contactInfo);
        this.medicalRecords = new ArrayList<>();
    }

    public void addRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    @Override
    public String displayInfo() {
        return "Patient ID: " + getId() + ", Name: " + name + ", Contact: " + contactInfo;
    }

    public String viewHistory() {
        if (medicalRecords.isEmpty()) {
            return "No medical history available.";
        }
        StringBuilder sb = new StringBuilder();
        for (MedicalRecord rec : medicalRecords) {
            sb.append(rec.getDetails()).append("\n");
        }
        return sb.toString();
    }
}
