package com.clinic;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelManager {
    private static final String FILE_NAME = "clinic_data.xlsx";
    private static final String[] SHEETS = {"Patients", "Doctors", "Staff", "Appointments", "MedicalRecords"};

    public ExcelManager() {
        initializeExcel();
    }

    private void initializeExcel() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (Workbook workbook = new XSSFWorkbook();
                 FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                for (String sheetName : SHEETS) {
                    Sheet sheet = workbook.createSheet(sheetName);
                    Row header = sheet.createRow(0);
                    setHeaders(sheetName, header);
                }
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setHeaders(String sheetName, Row header) {
        String[] columns;
        switch (sheetName) {
            case "Patients": columns = new String[]{"ID", "Name", "Contact"}; break;
            case "Doctors": columns = new String[]{"ID", "Name", "Contact", "Specialization"}; break;
            case "Staff": columns = new String[]{"ID", "Name", "Contact", "Role", "Shift"}; break;
            case "Appointments": columns = new String[]{"ID", "PatientID", "DoctorID", "Date", "Time", "Status"}; break;
            case "MedicalRecords": columns = new String[]{"ID", "PatientID", "Date", "Diagnosis", "Prescription"}; break;
            default: columns = new String[]{};
        }
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }
    }

    public void saveData(Clinic clinic) {
        try (Workbook workbook = new XSSFWorkbook()) {
            // Save Patients
            Sheet pSheet = workbook.createSheet("Patients");
            setHeaders("Patients", pSheet.createRow(0));
            int rowIdx = 1;
            for (Patient p : clinic.getPatients().values()) {
                Row row = pSheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(p.getId());
                row.createCell(1).setCellValue(p.getName());
                row.createCell(2).setCellValue(p.getContactInfo());
            }

            // Save Doctors
            Sheet dSheet = workbook.createSheet("Doctors");
            setHeaders("Doctors", dSheet.createRow(0));
            rowIdx = 1;
            for (Doctor d : clinic.getDoctors().values()) {
                Row row = dSheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(d.getId());
                row.createCell(1).setCellValue(d.getName());
                row.createCell(2).setCellValue(d.getContactInfo());
                row.createCell(3).setCellValue(d.getSpecialization());
            }

            // Save Appointments
            Sheet aSheet = workbook.createSheet("Appointments");
            setHeaders("Appointments", aSheet.createRow(0));
            rowIdx = 1;
            for (Appointment a : clinic.getAppointments().values()) {
                Row row = aSheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(a.getAppointmentId());
                row.createCell(1).setCellValue(a.getPatientId());
                row.createCell(2).setCellValue(a.getDoctorId());
                row.createCell(3).setCellValue(a.getDate());
                row.createCell(4).setCellValue(a.getTime());
                row.createCell(5).setCellValue(a.getStatus());
            }

            // Save Medical Records
            Sheet rSheet = workbook.createSheet("MedicalRecords");
            setHeaders("MedicalRecords", rSheet.createRow(0));
            rowIdx = 1;
            for (Patient p : clinic.getPatients().values()) {
                for (MedicalRecord rec : p.getMedicalRecords()) {
                    Row row = rSheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(rec.getRecordId());
                    row.createCell(1).setCellValue(rec.getPatientId());
                    row.createCell(2).setCellValue(rec.getDate());
                    row.createCell(3).setCellValue(rec.getDiagnosis());
                    row.createCell(4).setCellValue(rec.getPrescription());
                }
            }

            try (FileOutputStream out = new FileOutputStream(FILE_NAME)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(Clinic clinic) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Load Patients
            Sheet pSheet = workbook.getSheet("Patients");
            if (pSheet != null) {
                for (int i = 1; i <= pSheet.getLastRowNum(); i++) {
                    Row row = pSheet.getRow(i);
                    if (row == null) continue;
                    String id = getCellValue(row.getCell(0));
                    String name = getCellValue(row.getCell(1));
                    String contact = getCellValue(row.getCell(2));
                    clinic.getPatients().put(id, new Patient(id, name, contact));
                }
            }

            // Load Doctors
            Sheet dSheet = workbook.getSheet("Doctors");
            if (dSheet != null) {
                for (int i = 1; i <= dSheet.getLastRowNum(); i++) {
                    Row row = dSheet.getRow(i);
                    if (row == null) continue;
                    String id = getCellValue(row.getCell(0));
                    String name = getCellValue(row.getCell(1));
                    String contact = getCellValue(row.getCell(2));
                    String spec = getCellValue(row.getCell(3));
                    clinic.getDoctors().put(id, new Doctor(id, name, contact, spec));
                }
            }

            // Load Appointments
            Sheet aSheet = workbook.getSheet("Appointments");
            if (aSheet != null) {
                for (int i = 1; i <= aSheet.getLastRowNum(); i++) {
                    Row row = aSheet.getRow(i);
                    if (row == null) continue;
                    String id = getCellValue(row.getCell(0));
                    String pId = getCellValue(row.getCell(1));
                    String dId = getCellValue(row.getCell(2));
                    String date = getCellValue(row.getCell(3));
                    String time = getCellValue(row.getCell(4));
                    String status = getCellValue(row.getCell(5));
                    Appointment appt = new Appointment(id, pId, dId, date, time, status);
                    clinic.getAppointments().put(id, appt);
                    if ("Scheduled".equals(status)) {
                        Doctor d = clinic.getDoctors().get(dId);
                        if (d != null) d.bookSlot(date, time);
                    }
                }
            }

            // Load Medical Records
            Sheet rSheet = workbook.getSheet("MedicalRecords");
            if (rSheet != null) {
                for (int i = 1; i <= rSheet.getLastRowNum(); i++) {
                    Row row = rSheet.getRow(i);
                    if (row == null) continue;
                    String id = getCellValue(row.getCell(0));
                    String pId = getCellValue(row.getCell(1));
                    String date = getCellValue(row.getCell(2));
                    String diag = getCellValue(row.getCell(3));
                    String presc = getCellValue(row.getCell(4));
                    MedicalRecord rec = new MedicalRecord(id, pId, date, diag, presc);
                    Patient p = clinic.getPatients().get(pId);
                    if (p != null) p.addRecord(rec);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}
