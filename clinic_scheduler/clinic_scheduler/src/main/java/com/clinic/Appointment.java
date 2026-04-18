package com.clinic;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String status;

    public Appointment(String appointmentId, String patientId, String doctorId, String date, String time, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }

    public void cancel() { this.status = "Canceled"; }
    public void complete() { this.status = "Completed"; }

    @Override
    public String toString() {
        return "Appt ID: " + appointmentId + ", Patient: " + patientId + ", Doctor: " + doctorId + ", Date: " + date + ", Time: " + time + ", Status: " + status;
    }
}
