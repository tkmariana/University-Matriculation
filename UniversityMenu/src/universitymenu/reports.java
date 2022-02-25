package universitymenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class reports {

    final JList cList = new JList();
    JScrollPane scrollPane = new JScrollPane(cList);
    JPanel panel = new JPanel();
    JLabel header = new JLabel("REPORTS");
    JTextField ssnStudent = new JTextField();
    JButton searchButton = new JButton("Search");
    JButton clearButton = new JButton("Clear");
    JButton backButton = new JButton("<- BACK");
    Border Border = LineBorder.createBlackLineBorder();
    JLabel ssnLabel = new JLabel("SSN:");
    JLabel student = new JLabel("Student:");
    JLabel searchedStudent = new JLabel();
    JLabel yearLabel = new JLabel("Class:");
    JLabel yearStudent = new JLabel();
    JLabel credits = new JLabel("Total Credits:");
    JLabel cost = new JLabel("Total Cost:");
    JLabel totalcost = new JLabel("$0");
    JLabel totalcredits = new JLabel("0");
    DefaultListModel data = new DefaultListModel();

    int RECEIVABLE_OR_CLASSCHEDULE;
    String[] NCcourses = new String[50];
    JFrame frame;

    reports() {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle("Reports");
        frame.setBounds(40, 40, 500, 600);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void setLocationAndSize() {

        panel.add(scrollPane);
        header.setBounds(150, 30, 230, 20);
        header.setFont(new java.awt.Font("Arial", Font.BOLD, 22));
        ssnLabel.setBounds(25, 90, 50, 20);
        ssnStudent.setBounds(100, 90, 130, 20);
        searchButton.setBounds(250, 90, 95, 20);
        student.setBounds(25, 120, 50, 20);
        searchedStudent.setBorder(Border);
        searchedStudent.setBounds(100, 120, 130, 20);
        yearLabel.setBounds(25, 150, 50, 20);
        yearStudent.setBorder(Border);
        yearStudent.setBounds(100, 150, 130, 20);
        scrollPane.setBounds(25, 190, 390, 180);
        credits.setBounds(25, 380, 100, 20);
        totalcredits.setBorder(Border);
        totalcredits.setBounds(110, 380, 50, 20);
        cost.setBounds(25, 410, 100, 20);
        totalcost.setBorder(Border);
        totalcost.setBounds(110, 410, 50, 20);
        clearButton.setBounds(130, 450, 100, 20);
        backButton.setBounds(25, 480, 100, 20);
    }

    public void addComponentsToFrame() {
        //Adding components to Frame
        frame.add(scrollPane);
        frame.add(yearStudent);
        frame.add(yearLabel);
        frame.add(ssnLabel);
        frame.add(ssnStudent);
        frame.add(searchButton);
        frame.add(student);
        frame.add(searchedStudent);
        frame.add(clearButton);
        frame.add(cost);
        frame.add(credits);
        frame.add(totalcost);
        frame.add(totalcredits);
        frame.add(header);
        frame.add(backButton);
    }

    public void actionEvent() {

        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int iLine = 0, iLinecourses = 0, iTotalCost = 0,
                        costCourse = 0, credits = 0, priceNCredit = 0;

                String[] Courses = new String[100];
                String[] nonCreditCourses = new String[100];
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "luz", "hashysudo123");
                    Statement statement = connection.createStatement();
                    ResultSet resultset = statement.executeQuery("SELECT SSN, courseNumber,date, time from info ORDER BY courseNumber");
                    ResultSetMetaData metaData = resultset.getMetaData();
                    // int nColumns = metaData.getColumnCount();
                    data.clear();
                    String line = "";

                    resultset.beforeFirst();

                    while (resultset.next()) {

                        line = (String) resultset.getObject(1);

                        if (ssnStudent.getText().equals(line)) {

                            if (resultset.getObject(2).toString().charAt(0) != 'N') {
                                credits++;
                            } else {
                                priceNCredit += 150;
                            }
                            line = "";
                            for (int y = 2; y <= 4; y++) {
                                line += (String) resultset.getObject(y) + "    ";
                            }
                            if (line.charAt(0) != 'N') {
                                Courses[iLinecourses++] = line;
                            } else {
                                if (RECEIVABLE_OR_CLASSCHEDULE == 1) {
                                    line += "= $150";
                                }
                                nonCreditCourses[iLine++] = line;
                            }
                            line = "";
                        }
                    }
                    costCourse = (credits > 3) ? 265 : 285;

                    data.addElement("                    Credit Courses                      ");
                    for (int h = 0; h < Courses.length; h++) {
                        if (Courses[h] == null) {
                            break;
                        }
                        if (RECEIVABLE_OR_CLASSCHEDULE == 1) {
                            Courses[h] += "= $" + costCourse;
                        }
                        data.addElement(Courses[h]);
                    }

                    data.addElement("                Non-Credit Courses                  ");
                    for (int h = 0; h < nonCreditCourses.length; h++) {
                        if (nonCreditCourses[h] != null) {
                            data.addElement(nonCreditCourses[h]);
                        }
                    }
                    cList.setModel(data);
                    iTotalCost = costCourse * credits + 5 + priceNCredit;
                    credits *= 3;
                    totalcost.setText(Integer.toString(iTotalCost));
                    totalcredits.setText(Integer.toString(credits));

                    try {

                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "luz", "hashysudo123");

                        statement = connection.createStatement();
                        resultset = statement.executeQuery("SELECT SSN, FirstName, YearMatriculation,degree FROM matriculated");
                        metaData = resultset.getMetaData();
                        //nColumns = metaData.getColumnCount();
                        boolean found = false;
                        line = "";
                        while (resultset.next()) {
                            line += resultset.getString(1);
                            if (line.equals(ssnStudent.getText())) {
                                searchedStudent.setText(resultset.getString(2));
                                return;

                            }
                            line = "";

                        }
                        line = "";
                        while (resultset.next()) {
                            line += resultset.getString(1);
                            if (RECEIVABLE_OR_CLASSCHEDULE == 2) {
                                yearStudent.setText(resultset.getString(3));
                                return;
                            }
                            line = "";
                        }

                        if (!found) {
                            JOptionPane.showOptionDialog(null, "The student was not found\nPlease try it again",
                                    "Boola Boola University", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                            reSet();
                            return;
                        }
                        statement.close();
                        connection.close();
                        resultset.close();
                    } catch (SQLException te) {
                        System.out.println("SQL Exception: " + te.toString());
                    }
                    connection.close();
                    resultset.close();
                    statement.close();
                } catch (SQLException te) {
                    System.out.println("SQL Exception: " + te.toString());

                }
            }
        });
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reSet();
            }
        });
    }

    public void setVisible(boolean visibility) {
        scrollPane.setVisible(visibility);
        yearLabel.setVisible(visibility);
        yearStudent.setVisible(visibility);
        ssnStudent.setVisible(visibility);
        ssnLabel.setVisible(visibility);
        searchButton.setVisible(visibility);
        student.setVisible(visibility);
        searchedStudent.setVisible(visibility);
        clearButton.setVisible(visibility);
        credits.setVisible(visibility);
        cost.setVisible(visibility);

        totalcredits.setVisible(visibility);
        totalcost.setVisible(visibility);
        header.setVisible(visibility);
    }

    public void reSet() {
        totalcost.setText(null);
        totalcredits.setText(null);
        searchedStudent.setText(null);
        ssnStudent.setText(null);
        cList.setModel(new DefaultListModel());
        searchButton.setVisible(true);
        yearStudent.setText(null);

    }

    public void setOption(int x) {
        RECEIVABLE_OR_CLASSCHEDULE = x;

    }

}
