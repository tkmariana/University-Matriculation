package universitymenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NonMatriculated implements ActionListener {

    Connection myConn = null;
    Statement myStmt = null;
    JFrame frame;
    JLabel ssnLabel = new JLabel("SSN");
    JLabel firstNameLabel = new JLabel("FIRST NAME");
    JLabel middleNameLabel = new JLabel("MIDDLE NAME");
    JLabel lastNameLabel = new JLabel("LAST NAME");
    JLabel streetAddressLabel = new JLabel("STREET ADDRESS");
    JLabel cityLabel = new JLabel("CITY");
    JLabel stateLabel = new JLabel("STATE");
    JLabel zipLabel = new JLabel("ZIPCODE");

    JTextField ssnTextField = new JTextField();
    JTextField firstNameTextField = new JTextField();
    JTextField middleNameTextField = new JTextField();
    JTextField lastNameTextField = new JTextField();
    JTextField streetAddressTextField = new JTextField();
    JTextField cityTextField = new JTextField();
    JTextField stateTextField = new JTextField();
    JTextField zipTextField = new JTextField();

    JCheckBox immunizationBox = new JCheckBox("Immunization Record");
    JButton saveButton = new JButton("SAVE");
    JButton backButton = new JButton("BACK");

    NonMatriculated() {
        //Calling methods from constructor
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle("Students");
        frame.setBounds(40, 40, 500, 600);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void setLocationAndSize() {
        //Setting Location and Size of Each Component
        ssnLabel.setBounds(20, 20, 40, 23);
        ssnTextField.setBounds(180, 20, 165, 23);
        firstNameLabel.setBounds(20, 50, 80, 23);
        firstNameTextField.setBounds(180, 50, 165, 23);
        middleNameLabel.setBounds(20, 80, 100, 23);
        middleNameTextField.setBounds(180, 80, 165, 23);
        lastNameLabel.setBounds(20, 110, 100, 23);
        lastNameTextField.setBounds(180, 110, 165, 23);
        streetAddressLabel.setBounds(20, 140, 140, 23);
        streetAddressTextField.setBounds(180, 140, 165, 23);
        cityLabel.setBounds(20, 170, 100, 23);
        cityTextField.setBounds(180, 170, 165, 23);
        stateLabel.setBounds(20, 200, 100, 23);
        stateTextField.setBounds(180, 200, 165, 23);
        zipLabel.setBounds(20, 230, 100, 23);
        zipTextField.setBounds(180, 230, 165, 23);

        immunizationBox.setBounds(20, 250, 200, 23);

        saveButton.setBounds(70, 280, 100, 35);
        backButton.setBounds(180, 280, 100, 35);

    }

    public void addComponentsToFrame() {
        //Adding components to Frame
        frame.add(ssnLabel);
        frame.add(ssnTextField);
        frame.add(firstNameLabel);
        frame.add(firstNameTextField);
        frame.add(middleNameLabel);
        frame.add(cityLabel);
        frame.add(middleNameTextField);
        frame.add(lastNameLabel);
        frame.add(lastNameTextField);
        frame.add(streetAddressLabel);
        frame.add(streetAddressTextField);
        frame.add(cityLabel);
        frame.add(cityTextField);
        frame.add(cityTextField);
        frame.add(stateLabel);
        frame.add(stateTextField);
        frame.add(zipLabel);

        frame.add(zipTextField);

        frame.add(saveButton);
        frame.add(backButton);
        frame.add(immunizationBox);
    }

    public void actionEvent() {
        //Adding Action Listener to buttons
        saveButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            if (immunizationBox.isSelected()) {
                immunizationBox.getModel().equals(1);
            } else {
                immunizationBox.getModel().equals(0);
            }

            try {
                //Creating Connection Object
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "luz", "hashysudo123");
                //Preapared Statement
                PreparedStatement Pstatement = connection.prepareStatement("insert into matriculated ( SSN, FirstName,MiddleName, LastName, StreetAddress, "
                        + "City, State, ZipCode, TodaysDate, YearMatriculation, degree, highSchoolDiploma, immunizationRecord) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                //Specifying the values of it's parameter
                Pstatement.setString(1, ssnTextField.getText());
                Pstatement.setString(2, firstNameTextField.getText());
                Pstatement.setString(3, middleNameTextField.getText());
                Pstatement.setString(4, lastNameTextField.getText());
                Pstatement.setString(5, streetAddressTextField.getText());
                Pstatement.setString(6, cityTextField.getText());
                Pstatement.setString(7, stateTextField.getText());
                Pstatement.setString(8, zipTextField.getText());
                Pstatement.setString(9, "N");
                Pstatement.setInt(10, 0);
                Pstatement.setString(11, "NN");
                Pstatement.setBoolean(12, false);
                Pstatement.setBoolean(13, true);
                Pstatement.executeUpdate();
                //Checking for the Password match

                JOptionPane.showMessageDialog(null, "Data saved Successfully");

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        if (e.getSource() == backButton) {
            frame.setVisible(false);
        }
    }

}
