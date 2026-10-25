package studentmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentGUI_Old extends JFrame {

    // Labels
    private JLabel lblStudentID, lblName, lblAge, lblCourse, lblGrade;

    // Text Fields
    private JTextField txtStudentID, txtName, txtAge;

    // Combo Boxes
    private JComboBox<String> cmbCourse, cmbGrade;

    // Buttons
    private JButton btnAdd, btnUpdate, btnClear;

    // Table
    private JTable studentTable;
    private DefaultTableModel tableModel;

    // Panel
    private JPanel panel;
    public StudentGUI_Old() {

        setTitle("Student Management System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();


        panel.setLayout(new GridLayout(8, 2, 10, 10));
        // Create Labels
        lblStudentID = new JLabel("Student ID:");
        lblName = new JLabel("Student Name:");
        lblAge = new JLabel("Age:");
        lblCourse = new JLabel("Course:");
        lblGrade = new JLabel("Grade:");
        // Create Text Fields
        txtStudentID = new JTextField();
        txtName = new JTextField();
        txtAge = new JTextField();
        // Create Combo Boxes
        cmbCourse = new JComboBox<>(new String[]{
                "Java Programming",
                "Database Systems",
                "Web Development",
                "Computer Networks"
        });

        cmbGrade = new JComboBox<>(new String[]{
                "A","B","C","D","F"
        });
        // Create Buttons
        btnAdd = new JButton("Add Student");
        btnUpdate = new JButton("Update Student");
        btnClear = new JButton("Clear");
        panel.add(lblStudentID);
        panel.add(txtStudentID);

        panel.add(lblName);
        panel.add(txtName);

        panel.add(lblAge);
        panel.add(txtAge);

        panel.add(lblCourse);
        panel.add(cmbCourse);

        panel.add(lblGrade);
        panel.add(cmbGrade);
        panel.add(btnAdd);
        panel.add(btnUpdate);

        panel.add(new JLabel(""));
        panel.add(btnClear);

// Create Table
        tableModel = new DefaultTableModel();

        tableModel.addColumn("Student ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Course");
        tableModel.addColumn("Grade");

        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        add(panel);
        add(scrollPane, BorderLayout.SOUTH);


// Add Student Button
        btnAdd.addActionListener(e -> {

            String studentID = txtStudentID.getText().trim();
            String name = txtName.getText().trim();
            String age = txtAge.getText().trim();

            String course = cmbCourse.getSelectedItem().toString();
            String grade = cmbGrade.getSelectedItem().toString();

            // Check for empty fields
            if (studentID.isEmpty() || name.isEmpty() || age.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Check if age is a number
            try {
                Integer.parseInt(age);
            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be a number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
            // Check for duplicate Student ID
            for (int i = 0; i < tableModel.getRowCount(); i++) {

                if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(studentID)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student ID already exists.",
                            "Duplicate Student",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }
            }

            // Add student to table
            tableModel.addRow(new Object[]{
                    studentID,
                    name,
                    age,
                    course,
                    grade
            });

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
            );

        });
// Clear Button
        btnClear.addActionListener(e -> {

            txtStudentID.setText("");
            txtName.setText("");
            txtAge.setText("");

            cmbCourse.setSelectedIndex(0);
            cmbGrade.setSelectedIndex(0);

        });   // <-- The Clear button ends here

// Table Selection
        studentTable.getSelectionModel().addListSelectionListener(e -> {

            int selectedRow = studentTable.getSelectedRow();

            if (selectedRow >= 0) {

                txtStudentID.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtAge.setText(tableModel.getValueAt(selectedRow, 2).toString());

                cmbCourse.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
                cmbGrade.setSelectedItem(tableModel.getValueAt(selectedRow, 4).toString());

            }

        });
        // Update Student Button
        btnUpdate.addActionListener(e -> {

            int selectedRow = studentTable.getSelectedRow();

            if (selectedRow >= 0) {

                tableModel.setValueAt(txtStudentID.getText(), selectedRow, 0);
                tableModel.setValueAt(txtName.getText(), selectedRow, 1);
                tableModel.setValueAt(txtAge.getText(), selectedRow, 2);
                tableModel.setValueAt(cmbCourse.getSelectedItem(), selectedRow, 3);
                tableModel.setValueAt(cmbGrade.getSelectedItem(), selectedRow, 4);

                JOptionPane.showMessageDialog(this,
                        "Student information updated successfully!");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Please select a student to update.");

            }

        });


        setVisible(true);    }

    public static void main(String[] args) {

        new StudentGUI_Old();

    }

}