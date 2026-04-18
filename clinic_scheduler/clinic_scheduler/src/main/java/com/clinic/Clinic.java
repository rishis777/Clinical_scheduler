package com.clinic;

import java.util.*;

public class Clinic {
    private Map<String, Patient> patients;
    private Map<String, Doctor> doctors;
    private Map<String, Staff> staff;
    private Map<String, Appointment> appointments;
    private ExcelManager excelManager;

    public Clinic() {
        this.patients = new HashMap<>();
        this.doctors = new HashMap<>();
        this.staff = new HashMap<>();
        this.appointments = new HashMap<>();
        this.excelManager = new ExcelManager();
        loadAllData();
    }

    public String registerPatient(String name, String contact) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Patient patient = new Patient(id, name, contact);
        patients.put(id, patient);
        saveAllData();
        return id;
    }

    public String registerDoctor(String name, String contact, String specialization) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Doctor doctor = new Doctor(id, name, contact, specialization);
        doctors.put(id, doctor);
        saveAllData();
        return id;
    }

    public String bookAppointment(String patientId, String doctorId, String date, String time) {
        if (!patients.containsKey(patientId)) return "Error: Patient not found.";
        if (!doctors.containsKey(doctorId)) return "Error: Doctor not found.";

        Doctor doctor = doctors.get(doctorId);
        if (!doctor.isAvailable(date, time)) return "Error: Doctor is not available.";

        String id = UUID.randomUUID().toString().substring(0, 8);
        Appointment appt = new Appointment(id, patientId, doctorId, date, time, "Scheduled");
        appointments.put(id, appt);
        doctor.bookSlot(date, time);
        saveAllData();
        return "Success: Appointment booked. ID: " + id;
    }

    public String cancelAppointment(String apptId) {
        if (!appointments.containsKey(apptId)) return "Error: Appointment not found.";
        Appointment appt = appointments.get(apptId);
        appt.cancel();
        Doctor doctor = doctors.get(appt.getDoctorId());
        if (doctor != null) {
            doctor.releaseSlot(appt.getDate(), appt.getTime());
        }
        saveAllData();
        return "Success: Appointment canceled.";
    }

    public String addMedicalRecord(String patientId, String date, String diagnosis, String prescription) {
        if (!patients.containsKey(patientId)) return "Error: Patient not found.";
        String id = UUID.randomUUID().toString().substring(0, 8);
        MedicalRecord rec = new MedicalRecord(id, patientId, date, diagnosis, prescription);
        patients.get(patientId).addRecord(rec);
        saveAllData();
        return "Success: Medical record added. ID: " + id;
    }

    public Map<String, Patient> getPatients() { return patients; }
    public Map<String, Doctor> getDoctors() { return doctors; }
    public Map<String, Staff> getStaff() { return staff; }
    public Map<String, Appointment> getAppointments() { return appointments; }

    public void saveAllData() { excelManager.saveData(this); }
    public void loadAllData() { excelManager.loadData(this); }
}
