        
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentform;

/**
 *
 * @author brigh
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentForm extends JFrame {

    public StudentForm() {

        // ── 1. JFrame ──────────────────────────────────────────────
        // JFrame is the main window. Everything lives inside it.
        setTitle("Student Registration Form");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centres the window on screen
        setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns, gaps between cells

        // ── 2. JLabel ──────────────────────────────────────────────
        // JLabel is just a text sign. It does NOT accept input.
        // We use it to tell the user what each field is for.

        add(new JLabel("First Name:"));

        // ── 3. JTextField ──────────────────────────────────────────
        // JTextField is a single-line box where the user types text.
        JTextField firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Student Number:"));
        JTextField studentNumField = new JTextField();
        add(studentNumField);

        // ── 4. JTextArea ───────────────────────────────────────────
        // JTextArea is a MULTI-LINE text box — good for longer text
        // like an address. We wrap it in JScrollPane so it gets a
        // scrollbar if the text gets too long.
        add(new JLabel("Address:"));
        JTextArea addressArea = new JTextArea(3, 20);
        addressArea.setLineWrap(true);
        add(new JScrollPane(addressArea));

        // ── 5. JComboBox ───────────────────────────────────────────
        // JComboBox is a drop-down list. The user clicks it and
        // picks ONE option from the list.
        add(new JLabel("Course:"));
        String[] courses = {"Information Technology", "Business", "Engineering", "Law","Digital Marketing"};
        JComboBox<String> courseBox = new JComboBox<>(courses);
        add(courseBox);

        // ── 6. JRadioButton ────────────────────────────────────────
        // JRadioButton lets the user pick ONE option from a group.
        // We MUST add them to a ButtonGroup so only one can be
        // selected at a time.
        add(new JLabel("Gender:"));

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleBtn   = new JRadioButton("Male");
        JRadioButton femaleBtn = new JRadioButton("Female");
        JRadioButton prefernottosayBtn = new JRadioButton("Prefer not to say");

        ButtonGroup genderGroup = new ButtonGroup(); // links the four buttons
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        genderGroup.add(prefernottosayBtn);
        
        genderPanel.add(maleBtn);
        genderPanel.add(femaleBtn);
        genderPanel.add(prefernottosayBtn);
        add(genderPanel);

        // ── 7. JCheckBox ───────────────────────────────────────────
        // JCheckBox lets the user tick/untick options.
        // Unlike JRadioButton, the user CAN tick MORE than one.
        add(new JLabel("Subjects:"));

        JPanel subjectPanel = new JPanel(new GridLayout(0, 1));
        JCheckBox javaCB  = new JCheckBox("Java Programming");
        JCheckBox webCB   = new JCheckBox("Web Design");
        JCheckBox iqttCB = new JCheckBox("IQTT");
        JCheckBox buisCB = new JCheckBox("BUIS");
        subjectPanel.add(javaCB);
        subjectPanel.add(webCB);
        subjectPanel.add(iqttCB);
        subjectPanel.add(buisCB);
        add(subjectPanel);

        // ── 8. JButton ─────────────────────────────────────────────
        // JButton is a clickable button. We use ActionListener to
        // run code when the user clicks it.
        JButton submitBtn = new JButton("Submit");
        JButton clearBtn  = new JButton("Clear");

        // What happens when Submit is clicked:
            submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Collect all the values the user entered
                String firstName = firstNameField.getText();
                String lastName  = lastNameField.getText();
                String studentNo = studentNumField.getText();
                String address   = addressArea.getText();
                String course    = (String) courseBox.getSelectedItem();

                // Find which gender radio button is selected
                String gender = "Not selected";
                if (maleBtn.isSelected())   gender = "Male";
                if (femaleBtn.isSelected()) gender = "Female";
                if (prefernottosayBtn.isSelected()) gender = "Prefer not to say";

                // Build a list of ticked subjects
                String subjects = "";
                if (javaCB.isSelected())  subjects += "Java  ";
                if (webCB.isSelected())   subjects += "Web Design  ";
                if (iqttCB.isSelected()) subjects += "IQTT";
                if (buisCB.isSelected ()) subjects += "BUIS";
                if (subjects.isEmpty())   subjects  = "None selected";

                // Show a summary popup (JOptionPane)
                String summary =
                    "Name:     " + firstName + " " + lastName + "\n" +
                    "Stud No:  " + studentNo + "\n" +
                    "Address:  " + address   + "\n" +
                    "Course:   " + course    + "\n" +
                    "Gender:   " + gender    + "\n" +
                    "Subjects: " + subjects;

                JOptionPane.showMessageDialog(null, summary,
                    "Registration Summary", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // What happens when Clear is clicked:
        clearBtn.addActionListener(e -> {
            firstNameField.setText("");
            lastNameField.setText("");
            studentNumField.setText("");
            addressArea.setText("");
            courseBox.setSelectedIndex(0);
            genderGroup.clearSelection();
            javaCB.setSelected(false);
            webCB.setSelected(false);
            iqttCB.setSelected(false);
            buisCB.setSelected(false);
        });

        add(submitBtn);
        add(clearBtn);

        setVisible(true); // makes the window appear
    }

    // ── main method ────────────────────────────────────────────────
    // This is the entry point. It creates our form on the
    // Event Dispatch Thread (EDT) — the safe way to start Swing UIs.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentForm());
    }
}