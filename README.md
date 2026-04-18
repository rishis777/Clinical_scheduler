# Clinical_scheduler
Clinic Appointment Scheduler (Java)

A robust, console-based Java application designed to streamline clinic operations. This project implements the four pillars of Object-Oriented Programming (OOP) and utilizes Apache POI for database-less persistence via Microsoft Excel.

📖 Table of Contents

Project Overview

Key Features

OOP Concepts Applied

System Architecture

Installation & Setup

Usage Guide

Screenshots

Contributors

🌟 Project Overview

The Clinic Appointment Scheduler automates administrative tasks including patient registration, doctor scheduling, and medical record management. Unlike traditional apps that require complex SQL setups, this system uses a .xlsx workbook as a flat-file database, making it portable and lightweight.

✨ Key Features

Smart Booking: Real-time availability checking for doctors to prevent double-booking.

Medical Records: Comprehensive tracking of patient diagnoses and prescriptions.

Data Persistence: Automatic saving/loading of system state using Apache POI.

Unique Identifiers: Automated UUID generation for patients, doctors, and appointments.

🏗 OOP Concepts Applied

Inheritance: Patient, Doctor, and Staff extend the abstract Person class.

Encapsulation: All entity fields are private with access controlled via getters/setters.

Abstraction: The Person class and ExcelManager interface hide complex implementation details.

Polymorphism: Method overriding of displayInfo() allows for dynamic behavior at runtime.

📂 System Architecture

src/main/java/com/clinic/
├── entities/           # Domain Models (Person, Patient, Doctor, etc.)
├── service/            # Business Logic (Clinic.java)
├── data/               # Data Access Layer (ExcelManager.java)
└── Main.java           # Entry Point & Menu Interface


🛠 Installation & Setup

Prerequisites

Java SDK 11 or higher.

Apache Maven for dependency management.

Build Instructions

Clone the repository:

git clone [https://github.com/your-username/clinic-scheduler.git](https://github.com/your-username/clinic-scheduler.git)
cd clinic-scheduler


Compile and Package:

mvn clean package


This creates a "fat JAR" in the target/ directory containing all dependencies.

🚀 Usage Guide

To run the application, use the following command (ensure your lib/ folder contains the necessary Apache POI JARs if not using the Maven JAR):

java -cp "bin;lib/*" com.clinic.Main


Main Menu Options:

Register Patient: Input name and contact to receive a unique Patient ID.

Register Doctor: Set doctor name and specialization.

Book Appointment: Match Patient ID with Doctor ID for a specific date/time.

View Medical History: Retrieve all past records for a specific patient.

📸 Screenshots

(Add your project screenshots here)

Main Menu

Patient Registration





👥 Contributors

Rishikesh Borude (A227)

Lokesh Patil (A240)

Bilal Hadiry (A223)

Academic Year: April 2026

Institution: SVKM's NMIMS – Mukesh Patel School of Technology Management & Engineering

Subject: Object-Oriented Programming (Java)

Developed for the IT 2nd Year Mini-Project.
