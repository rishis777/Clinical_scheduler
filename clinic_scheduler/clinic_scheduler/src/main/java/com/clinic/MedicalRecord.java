package com.clinic;

public class MedicalRecord {
    private String recordId;
    private String patientId;
    private String date;
    private String diagnosis;
    private String prescription;

    public MedicalRecord(String recordId, String patientId, String date, String diagnosis, String prescription) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public String getRecordId() { return recordId; }
    public String getPatientId() { return patientId; }
    public String getDate() { return date; }
    public String getDiagnosis() { return diagnosis; }
    public String getPrescription() { return prescription; }

    public String getDetails() {
        return "Date: " + date + ", Diagnosis: " + diagnosis + ", Prescription: " + prescription;
    }
}
