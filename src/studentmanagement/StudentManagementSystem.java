package studentmanagement;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentManagementSystem extends JFrame {

    // Labels
    private JLabel lblStudentID, lblName, lblAge, lblCourse, lblGrade;

    // Text Fields
    private JTextField txtStudentID, txtName, txtAge;

    // Combo Boxes
    private JComboBox<String> cmbCourse, cmbGrade;

    // Search Components
    private JTextField txtSearch;
    private JButton btnSearch;

    // Buttonsp
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnEnroll, btnAssignGrade;

    // Table
    private JTable studentTable;
    private DefaultTableModel tableModel;

    // Panels
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JPanel topPanel;

    public StudentManagementSystem() {

        setTitle("Student Management System");
        setSize(950,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setOpaque(true);
        title.setBackground(new Color(30,144,255));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        add(title, BorderLayout.NORTH);

        // =========================
        // FORM PANEL
        // =========================

        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Student Information"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblStudentID = new JLabel("Student ID:");
        lblName = new JLabel("Student Name:");
        lblAge = new JLabel("Age:");
        lblCourse = new JLabel("Course:");
        lblGrade = new JLabel("Grade:");

        txtStudentID = new JTextField(18);
        txtName = new JTextField(18);
        txtAge = new JTextField(18);

        cmbCourse = new JComboBox<>(new String[]{
                "Java Programming",
                "Database Systems",
                "Web Development",
                "Computer Networks"
        });

        cmbGrade = new JComboBox<>(new String[]{
                "A","B","C","D","F"
        });
        txtSearch = new JTextField(12);
        btnSearch = new JButton("Search");
        // =========================
// SEARCH
// =========================

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Search ID:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtSearch, gbc);

        gbc.gridx = 2;
        formPanel.add(btnSearch, gbc);

        // Row 1 - Student ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblStudentID, gbc);

        gbc.gridx = 1;
        formPanel.add(txtStudentID, gbc);

// Row 2 - Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblName, gbc);

        gbc.gridx = 1;
        formPanel.add(txtName, gbc);

// Row 3 - Age
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblAge, gbc);

        gbc.gridx = 1;
        formPanel.add(txtAge, gbc);

// Row 4 - Course
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblCourse, gbc);

        gbc.gridx = 1;
        formPanel.add(cmbCourse, gbc);

// Row 5 - Grade
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblGrade, gbc);

        gbc.gridx = 1;
        formPanel.add(cmbGrade, gbc);

        // =========================
        // BUTTON PANEL
        // =========================

        buttonPanel = new JPanel(new FlowLayout());

        btnAdd = new JButton("Add Student");
        btnUpdate = new JButton("Update Student");
        btnDelete = new JButton("Delete Student");
        btnClear = new JButton("Clear");
        btnEnroll = new JButton("Enroll Course");
        btnAssignGrade = new JButton("Assign Grade");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEnroll);
        buttonPanel.add(btnAssignGrade);
        buttonPanel.add(btnClear);
        topPanel = new JPanel(new BorderLayout());

        topPanel.add(formPanel,BorderLayout.CENTER);
        topPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(topPanel,BorderLayout.CENTER);

        // =========================
        // TABLE
        // =========================

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Student ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Course");
        tableModel.addColumn("Grade");

        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setPreferredSize(new Dimension(900,250));

        add(scrollPane,BorderLayout.SOUTH);

        // ========= EVENT HANDLERS =========
        // =========================
        // ADD STUDENT
        // =========================

        btnAdd.addActionListener(e -> {

            String studentID = txtStudentID.getText().trim();
            String name = txtName.getText().trim();
            String age = txtAge.getText().trim();

            if (studentID.isEmpty() || name.isEmpty() || age.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            try {
                Integer.parseInt(age);
            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be a number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Check duplicate Student ID
            for (int i = 0; i < tableModel.getRowCount(); i++) {

                if (tableModel.getValueAt(i,0).toString().equalsIgnoreCase(studentID)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student ID already exists.",
                            "Duplicate Student",
                            JOptionPane.ERROR_MESSAGE);

                    return;
                }
            }

            tableModel.addRow(new Object[]{
                    studentID,
                    name,
                    age,
                    cmbCourse.getSelectedItem(),
                    cmbGrade.getSelectedItem()
            });

            JOptionPane.showMessageDialog(this,
                    "Student added successfully!");

            clearFields();

        });

        // =========================
        // TABLE SELECTION
        // =========================

        studentTable.getSelectionModel().addListSelectionListener(e -> {

            int row = studentTable.getSelectedRow();

            if(row >=0){

                txtStudentID.setText(tableModel.getValueAt(row,0).toString());
                txtName.setText(tableModel.getValueAt(row,1).toString());
                txtAge.setText(tableModel.getValueAt(row,2).toString());

                cmbCourse.setSelectedItem(tableModel.getValueAt(row,3).toString());
                cmbGrade.setSelectedItem(tableModel.getValueAt(row,4).toString());

            }

        });

        // =========================
        // UPDATE STUDENT
        // =========================

        btnUpdate.addActionListener(e -> {

            int row = studentTable.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a student first.");

                return;
            }

            tableModel.setValueAt(txtStudentID.getText(),row,0);
            tableModel.setValueAt(txtName.getText(),row,1);
            tableModel.setValueAt(txtAge.getText(),row,2);
            tableModel.setValueAt(cmbCourse.getSelectedItem(),row,3);
            tableModel.setValueAt(cmbGrade.getSelectedItem(),row,4);

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully.");

            clearFields();

        });

        // =========================
        // DELETE STUDENT
        // =========================

        btnDelete.addActionListener(e -> {

            int row = studentTable.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a student first.");

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete selected student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if(option==JOptionPane.YES_OPTION){

                tableModel.removeRow(row);

                JOptionPane.showMessageDialog(
                        this,
                        "Student deleted successfully.");

                clearFields();

            }

        });

        // =========================
        // CLEAR BUTTON
        // =========================

        btnClear.addActionListener(e -> clearFields());

        btnSearch.addActionListener(e -> {

            String searchID = txtSearch.getText().trim();

            boolean found = false;

            for (int i = 0; i < tableModel.getRowCount(); i++) {

                if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(searchID)) {

                    studentTable.setRowSelectionInterval(i, i);

                    txtStudentID.setText(tableModel.getValueAt(i, 0).toString());
                    txtName.setText(tableModel.getValueAt(i, 1).toString());
                    txtAge.setText(tableModel.getValueAt(i, 2).toString());

                    cmbCourse.setSelectedItem(tableModel.getValueAt(i, 3).toString());
                    cmbGrade.setSelectedItem(tableModel.getValueAt(i, 4).toString());

                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this,
                        "Student not found.");
            }

        });
        btnEnroll.addActionListener(e -> {

            int row = studentTable.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a student to enroll.");

                return;
            }

            tableModel.setValueAt(cmbCourse.getSelectedItem(), row, 3);

            JOptionPane.showMessageDialog(
                    this,
                    "Student enrolled successfully in "
                            + cmbCourse.getSelectedItem());

        });
        btnAssignGrade.addActionListener(e -> {

            int row = studentTable.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a student first.");

                return;
            }

            tableModel.setValueAt(cmbGrade.getSelectedItem(), row, 4);

            JOptionPane.showMessageDialog(
                    this,
                    "Grade assigned successfully.");

        });

        setVisible(true);

    }

    // =========================
    // CLEAR METHOD
    // =========================

    private void clearFields(){

        txtStudentID.setText("");
        txtName.setText("");
        txtAge.setText("");

        cmbCourse.setSelectedIndex(0);
        cmbGrade.setSelectedIndex(0);

        studentTable.clearSelection();

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new StudentManagementSystem());

    }

}