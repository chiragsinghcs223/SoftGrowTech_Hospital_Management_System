import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// --- Data Models ---

class Patient {
    String id, name, gender, contact, disease, doctor;
    int age;

    public Patient(String id, String name, int age, String gender, String contact, String disease, String doctor) {
        this.id = id; this.name = name; this.age = age; this.gender = gender;
        this.contact = contact; this.disease = disease; this.doctor = doctor;
    }
}

class Doctor {
    String id, name, specialization, contact, timing;
    int experience;

    public Doctor(String id, String name, String specialization, int experience, String contact, String timing) {
        this.id = id; this.name = name; this.specialization = specialization;
        this.experience = experience; this.contact = contact; this.timing = timing;
    }
}

class Appointment {
    String id, patientName, doctorName, date, time, status;

    public Appointment(String id, String patientName, String doctorName, String date, String time, String status) {
        this.id = id; this.patientName = patientName; this.doctorName = doctorName;
        this.date = date; this.time = time; this.status = status;
    }
}

// --- Main Application GUI Class ---

public class HospitalManagementSystem extends JFrame {

    // Data Storage
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    // UI Colors
    Color primaryBlue = new Color(0, 102, 204);
    Color backgroundWhite = new Color(245, 245, 250);

    // Dashboard Counters
    JLabel lblTotalPatients, lblTotalDoctors, lblTotalAppointments;

    public HospitalManagementSystem() {
        // GUI Window Setup
        setTitle("Hospital Management System - Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the GUI on screen
        setLayout(new BorderLayout());

        initDummyData();
        createMenuBar();

        // GUI Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryBlue);
        headerPanel.setPreferredSize(new Dimension(1000, 60));
        JLabel title = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        headerPanel.add(title);
        add(headerPanel, BorderLayout.NORTH);

        // GUI Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setFocusable(false);

        tabbedPane.addTab("  Dashboard  ", createDashboardTab());
        tabbedPane.addTab("  Patients  ", createPatientTab());
        tabbedPane.addTab("  Doctors  ", createDoctorTab());
        tabbedPane.addTab("  Appointments  ", createAppointmentTab());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem exitItem = new JMenuItem("Exit");

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Hospital GUI Application v2.0\nDeveloped with Java Swing.", "About", JOptionPane.INFORMATION_MESSAGE));
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(aboutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void initDummyData() {
        doctors.add(new Doctor("D01", "Dr. Amit Sharma", "Cardiologist", 10, "9876543210", "10:00 AM - 02:00 PM"));
        doctors.add(new Doctor("D02", "Dr. Neha Verma", "Dermatologist", 8, "9876543211", "11:00 AM - 03:00 PM"));
        doctors.add(new Doctor("D03", "Dr. Raj Mehta", "Orthopedic", 15, "9876543212", "09:00 AM - 01:00 PM"));
        
        patients.add(new Patient("P01", "Rahul Kumar", 35, "Male", "9123456780", "Fever", "Dr. Amit Sharma"));
        patients.add(new Patient("P02", "Sneha Patel", 28, "Female", "9123456781", "Skin Allergy", "Dr. Neha Verma"));
        
        appointments.add(new Appointment("A01", "Rahul Kumar", "Dr. Amit Sharma", "2026-05-10", "10:30 AM", "Confirmed"));
    }

    private void updateDashboardCounts() {
        lblTotalPatients.setText(String.valueOf(patients.size()));
        lblTotalDoctors.setText(String.valueOf(doctors.size()));
        lblTotalAppointments.setText(String.valueOf(appointments.size()));
    }

    private JPanel createDashboardTab() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 20));
        panel.setBackground(backgroundWhite);
        panel.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));

        lblTotalPatients = new JLabel(String.valueOf(patients.size()), SwingConstants.CENTER);
        lblTotalDoctors = new JLabel(String.valueOf(doctors.size()), SwingConstants.CENTER);
        lblTotalAppointments = new JLabel(String.valueOf(appointments.size()), SwingConstants.CENTER);

        panel.add(createCard("Total Patients", lblTotalPatients));
        panel.add(createCard("Total Doctors", lblTotalDoctors));
        panel.add(createCard("Total Appointments", lblTotalAppointments));

        return panel;
    }

    private JPanel createCard(String title, JLabel countLabel) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(primaryBlue, 3, true));

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(primaryBlue);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setPreferredSize(new Dimension(100, 50));

        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 60));
        countLabel.setForeground(Color.DARK_GRAY);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(countLabel, BorderLayout.CENTER);
        return card;
    }

    // --- PATIENT GUI TAB ---
    private JPanel createPatientTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Patient Entry Form"));
        
        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtAge = new JTextField();
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField txtContact = new JTextField();
        JTextField txtDisease = new JTextField();
        JComboBox<String> cbDoctor = new JComboBox<>();
        for(Doctor d : doctors) cbDoctor.addItem(d.name);

        formPanel.add(new JLabel("Patient ID:")); formPanel.add(txtId);
        formPanel.add(new JLabel("Full Name:")); formPanel.add(txtName);
        formPanel.add(new JLabel("Age:")); formPanel.add(txtAge);
        formPanel.add(new JLabel("Gender:")); formPanel.add(cbGender);
        formPanel.add(new JLabel("Contact No:")); formPanel.add(txtContact);
        formPanel.add(new JLabel("Diagnosis:")); formPanel.add(txtDisease);
        formPanel.add(new JLabel("Assign Doctor:")); formPanel.add(cbDoctor);

        JButton btnAdd = new JButton("Save Patient");
        btnAdd.setBackground(new Color(34, 139, 34));
        btnAdd.setForeground(Color.WHITE);
        
        JButton btnDelete = new JButton("Delete Record");
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);
        
        formPanel.add(btnAdd); formPanel.add(btnDelete);

        String[] columns = {"ID", "Name", "Age", "Gender", "Contact", "Disease", "Doctor"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(25);
        refreshPatientTable(model);

        btnAdd.addActionListener(e -> {
            if(txtId.getText().isEmpty() || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Name are required fields!");
                return;
            }
            try {
                int age = Integer.parseInt(txtAge.getText());
                patients.add(new Patient(txtId.getText(), txtName.getText(), age, cbGender.getSelectedItem().toString(), txtContact.getText(), txtDisease.getText(), cbDoctor.getSelectedItem().toString()));
                refreshPatientTable(model);
                updateDashboardCounts();
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
                txtId.setText(""); txtName.setText(""); txtAge.setText(""); txtContact.setText(""); txtDisease.setText("");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for Age.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                patients.remove(row);
                refreshPatientTable(model);
                updateDashboardCounts();
            } else {
                JOptionPane.showMessageDialog(this, "Please click on a patient record in the table to delete.");
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, new JScrollPane(table));
        splitPane.setDividerLocation(350);
        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }

    private void refreshPatientTable(DefaultTableModel model) {
        model.setRowCount(0);
        for(Patient p : patients) {
            model.addRow(new Object[]{p.id, p.name, p.age, p.gender, p.contact, p.disease, p.doctor});
        }
    }

    // --- DOCTOR GUI TAB ---
    private JPanel createDoctorTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Doctor Entry Form"));
        
        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtSpec = new JTextField();
        JTextField txtExp = new JTextField();
        JTextField txtContact = new JTextField();
        JTextField txtTiming = new JTextField();

        formPanel.add(new JLabel("Doctor ID:")); formPanel.add(txtId);
        formPanel.add(new JLabel("Name:")); formPanel.add(txtName);
        formPanel.add(new JLabel("Specialization:")); formPanel.add(txtSpec);
        formPanel.add(new JLabel("Experience (Yrs):")); formPanel.add(txtExp);
        formPanel.add(new JLabel("Contact:")); formPanel.add(txtContact);
        formPanel.add(new JLabel("Shift Timing:")); formPanel.add(txtTiming);

        JButton btnAdd = new JButton("Save Doctor");
        btnAdd.setBackground(new Color(34, 139, 34));
        btnAdd.setForeground(Color.WHITE);
        
        JButton btnDelete = new JButton("Delete Record");
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);
        
        formPanel.add(btnAdd); formPanel.add(btnDelete);

        String[] columns = {"ID", "Name", "Specialization", "Experience", "Contact", "Timing"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(25);
        refreshDoctorTable(model);

        btnAdd.addActionListener(e -> {
            try {
                int exp = Integer.parseInt(txtExp.getText());
                doctors.add(new Doctor(txtId.getText(), txtName.getText(), txtSpec.getText(), exp, txtContact.getText(), txtTiming.getText()));
                refreshDoctorTable(model);
                updateDashboardCounts();
                JOptionPane.showMessageDialog(this, "Doctor record saved!");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Experience format!");
            }
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                doctors.remove(row);
                refreshDoctorTable(model);
                updateDashboardCounts();
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, new JScrollPane(table));
        splitPane.setDividerLocation(350);
        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }

    private void refreshDoctorTable(DefaultTableModel model) {
        model.setRowCount(0);
        for(Doctor d : doctors) {
            model.addRow(new Object[]{d.id, d.name, d.specialization, d.experience, d.contact, d.timing});
        }
    }

    // --- APPOINTMENT GUI TAB ---
    private JPanel createAppointmentTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Appointment Booking"));
        
        JTextField txtId = new JTextField();
        JComboBox<String> cbPatient = new JComboBox<>();
        JComboBox<String> cbDoctor = new JComboBox<>();
        JTextField txtDate = new JTextField("YYYY-MM-DD");
        JTextField txtTime = new JTextField("10:00 AM");
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Confirmed", "Pending", "Cancelled"});

        for(Patient p : patients) cbPatient.addItem(p.name);
        for(Doctor d : doctors) cbDoctor.addItem(d.name);

        formPanel.add(new JLabel("Apt ID:")); formPanel.add(txtId);
        formPanel.add(new JLabel("Select Patient:")); formPanel.add(cbPatient);
        formPanel.add(new JLabel("Select Doctor:")); formPanel.add(cbDoctor);
        formPanel.add(new JLabel("Date:")); formPanel.add(txtDate);
        formPanel.add(new JLabel("Time:")); formPanel.add(txtTime);
        formPanel.add(new JLabel("Status:")); formPanel.add(cbStatus);

        JButton btnBook = new JButton("Book Appointment");
        btnBook.setBackground(new Color(34, 139, 34));
        btnBook.setForeground(Color.WHITE);
        
        JButton btnCancel = new JButton("Cancel Selected");
        btnCancel.setBackground(new Color(220, 20, 60));
        btnCancel.setForeground(Color.WHITE);
        
        formPanel.add(btnBook); formPanel.add(btnCancel);

        String[] columns = {"Apt ID", "Patient", "Doctor", "Date", "Time", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(25);
        refreshAppointmentTable(model);

        btnBook.addActionListener(e -> {
            appointments.add(new Appointment(txtId.getText(), cbPatient.getSelectedItem().toString(), cbDoctor.getSelectedItem().toString(), txtDate.getText(), txtTime.getText(), cbStatus.getSelectedItem().toString()));
            refreshAppointmentTable(model);
            updateDashboardCounts();
        });

        btnCancel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                appointments.get(row).status = "Cancelled";
                refreshAppointmentTable(model);
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, new JScrollPane(table));
        splitPane.setDividerLocation(350);
        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }

    private void refreshAppointmentTable(DefaultTableModel model) {
        model.setRowCount(0);
        for(Appointment a : appointments) {
            model.addRow(new Object[]{a.id, a.patientName, a.doctorName, a.date, a.time, a.status});
        }
    }

    // =========================================================
    //               LOGIN GUI WINDOW
    // =========================================================
    
    static class LoginScreen extends JFrame {
        public LoginScreen() {
            setTitle("Hospital System - Login");
            setSize(400, 250);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JPanel header = new JPanel();
            header.setBackground(new Color(0, 102, 204));
            JLabel title = new JLabel("Admin Login");
            title.setForeground(Color.WHITE);
            title.setFont(new Font("Segoe UI", Font.BOLD, 20));
            header.add(title);
            add(header, BorderLayout.NORTH);

            JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
            formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

            formPanel.add(new JLabel("Username:"));
            JTextField userField = new JTextField();
            formPanel.add(userField);

            formPanel.add(new JLabel("Password:"));
            JPasswordField passField = new JPasswordField();
            formPanel.add(passField);

            add(formPanel, BorderLayout.CENTER);

            JPanel btnPanel = new JPanel();
            JButton btnLogin = new JButton("Login");
            btnLogin.setBackground(new Color(0, 102, 204));
            btnLogin.setForeground(Color.WHITE);
            btnLogin.setFocusPainted(false);
            
            btnLogin.addActionListener(e -> {
                if (userField.getText().equals("admin") && new String(passField.getPassword()).equals("admin")) {
                    this.dispose(); // Close login GUI
                    new HospitalManagementSystem().setVisible(true); // Open Main Application GUI
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Credentials! Use admin/admin", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnPanel.add(btnLogin);
            add(btnPanel, BorderLayout.SOUTH);
        }
    }

    // --- APPLICATION ENTRY POINT ---
  public static void main(String[] args) {
        // Force the GUI to use a standard look
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            System.out.println("Attempting to open GUI..."); // This will show in terminal
            HospitalManagementSystem app = new HospitalManagementSystem();
            app.setVisible(true);
            app.toFront(); // Brings window to the very top
            System.out.println("GUI should be visible now.");
        });
      }
    }
  