

# Hospital Management System (HMS)

Developed during my **Java Programming Internship** at **SoftGrowTech**, this project is a robust desktop application designed to automate healthcare operations, including patient registration, doctor scheduling, and appointment management.

## 📌 Project Overview
The primary goal of this system is to enhance healthcare delivery by digitizing manual processes. It effectively reduces administrative overhead, eliminates paper-based errors, and ensures data security through an authorized login system.

## 🛠️ Technical Stack
*   **Programming Language:** Java (JDK 8+).
*   **GUI Framework:** Java Swing & AWT.
*   **Data Storage:** In-memory ArrayList (Object-Oriented).
*   **Design Pattern:** Model-View-Controller (MVC).
*   **Development Tools:** VS Code / IntelliJ / Eclipse.

## ✨ Key Features

*   **Live Dashboard:** Real-time counters displaying the total count of patients, doctors, and active appointments.
*   **CRUD Management:** Complete functionality to Create, Read, Update, and Delete patient and doctor records.
*   **Smart Scheduling:** An intelligent booking system that utilizes `DoctorArrayList` and `AppointmentArrayList` to prevent overlapping or double-booked appointments.
*   **Professional Visualization:** Uses `JTable` components for clear and organized data display.

## 🏗️ System Architecture
The application is built on four primary object-oriented pillars.
1.  **Patient Class:** Manages personal details and medical history.
2.  **Doctor Class:** Tracks professional credentials and shift timings.
3.  **Appointment Class:** Functions as a bridge linking Patient and Doctor objects.
4.  **System Engine:** Manages the GUI event-dispatching thread and ensures data persistence.

## 🚀 Execution Instructions
1.  Ensure **Java JDK 8+** is installed on your machine.
2.  Clone this repository or download the source code as `HospitalManagementSystem.java`.
3.  Open your terminal or command prompt in the project directory.
4.  **Compile** the application:
    ```bash
    javac HospitalManagementSystem.java
   
5.  **Run** the application:
    ```bash
    java HospitalManagementSystem
  

