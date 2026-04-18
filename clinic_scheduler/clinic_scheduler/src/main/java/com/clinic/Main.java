package com.clinic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Clinic Appointment Scheduler (Java) ===");
            System.out.println("1. Register Patient");
            System.out.println("2. Register Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View Patient Medical History");
            System.out.println("6. Add Medical Record");
            System.out.println("7. View All Doctors");
            System.out.println("8. View All Patients");
            System.out.println("9. View All Appointments");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Patient Name: ");
                    String pName = scanner.nextLine();
                    System.out.print("Enter Contact Info: ");
                    String pContact = scanner.nextLine();
                    String pId = clinic.registerPatient(pName, pContact);
                    System.out.println("Patient registered with ID: " + pId);
                    break;
                case "2":
                    System.out.print("Enter Doctor Name: ");
                    String dName = scanner.nextLine();
                    System.out.print("Enter Contact Info: ");
                    String dContact = scanner.nextLine();
                    System.out.print("Enter Specialization: ");
                    String spec = scanner.nextLine();
                    String dId = clinic.registerDoctor(dName, dContact, spec);
                    System.out.println("Doctor registered with ID: " + dId);
                    break;
                case "3":
                    System.out.print("Enter Patient ID: ");
                    String patId = scanner.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    String docId = scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Time (HH:MM): ");
                    String time = scanner.nextLine();
                    System.out.println(clinic.bookAppointment(patId, docId, date, time));
                    break;
                case "4":
                    System.out.print("Enter Appointment ID: ");
                    String aId = scanner.nextLine();
                    System.out.println(clinic.cancelAppointment(aId));
                    break;
                case "5":
                    System.out.print("Enter Patient ID: ");
                    String historyId = scanner.nextLine();
                    Patient p = clinic.getPatients().get(historyId);
                    if (p != null) {
                        System.out.println("\nMedical History for " + p.getName() + ":");
                        System.out.println(p.viewHistory());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case "6":
                    System.out.print("Enter Patient ID: ");
                    String recPatId = scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String recDate = scanner.nextLine();
                    System.out.print("Enter Diagnosis: ");
                    String diag = scanner.nextLine();
                    System.out.print("Enter Prescription: ");
                    String presc = scanner.nextLine();
                    System.out.println(clinic.addMedicalRecord(recPatId, recDate, diag, presc));
                    break;
                case "7":
                    System.out.println("\nList of Doctors:");
                    for (Doctor d : clinic.getDoctors().values()) System.out.println(d.displayInfo());
                    break;
                case "8":
                    System.out.println("\nList of Patients:");
                    for (Patient pat : clinic.getPatients().values()) System.out.println(pat.displayInfo());
                    break;
                case "9":
                    System.out.println("\nAll Appointments:");
                    for (Appointment a : clinic.getAppointments().values()) System.out.println(a);
                    break;
                case "0":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
