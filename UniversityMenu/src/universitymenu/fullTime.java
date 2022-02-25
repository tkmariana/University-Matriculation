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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class fullTime implements ListSelectionListener {

    final JList cList = new JList();
    JScrollPane scrollPane = new JScrollPane(cList);
    JPanel panel = new JPanel();
    JLabel header = new JLabel("REGISTATION");
    JTextField ssnStudent = new JTextField();
    JButton searchButton = new JButton("Search");
    JButton registerButton = new JButton("Register");
    JButton clearButton = new JButton("Clear");
    JButton backButton = new JButton("<- BACK");
    Border Border = LineBorder.createBlackLineBorder();
    JLabel courses = new JLabel();
    JLabel ssnLabel = new JLabel("SSN:");
    JLabel student = new JLabel("Student:");
    JLabel searchedStudent = new JLabel();
    JLabel credits = new JLabel("Total Credits:");
    JLabel cost = new JLabel("Total Cost:");
    JLabel totalcost = new JLabel("$0");
    JLabel totalcredits = new JLabel("0");

    int FULLPART_TIME;

    DefaultListModel data = new DefaultListModel();
    final String[] Day = new String[10];
    final String[] Time = new String[10];
    JFrame frame;

    fullTime() {
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
        courses.setText("Number         Name          Day         Time         Room");
        panel.add(scrollPane);
        cList.addListSelectionListener(this);
        header.setBounds(150, 30, 230, 20);
        header.setFont(new java.awt.Font("Arial", Font.BOLD, 22));
        ssnLabel.setBounds(25, 90, 50, 20);
        ssnStudent.setBounds(100, 90, 130, 20);
        searchButton.setBounds(250, 90, 95, 20);
        student.setBounds(25, 120, 50, 20);
        searchedStudent.setBorder(Border);
        searchedStudent.setBounds(100, 120, 130, 20);
        courses.setBounds(25, 170, 350, 10);
        scrollPane.setBounds(25, 190, 390, 180);
        credits.setBounds(25, 380, 100, 20);
        totalcredits.setBorder(Border);
        totalcredits.setBounds(110, 380, 50, 20);
        cost.setBounds(25, 410, 100, 20);
        totalcost.setBorder(Border);
        totalcost.setBounds(110, 410, 50, 20);
        registerButton.setBounds(25, 450, 100, 20);
        clearButton.setBounds(130, 450, 100, 20);
        backButton.setBounds(25, 480, 100, 20);

    }

    public void addComponentsToFrame() {
        //Adding components to Frame
        frame.add(scrollPane);
        frame.add(courses);
        frame.add(ssnLabel);
        frame.add(ssnStudent);
        frame.add(searchButton);
        frame.add(student);
        frame.add(searchedStudent);
        frame.add(registerButton);
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

                try {

                    Statement statement = null;
                    // Connection connection = null;
                    ResultSet resultset = null;
//add mysql username and password
                    String text2 = "";
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "luz", "hashysudo123");
                    statement = connection.createStatement();
                    resultset = statement.executeQuery("SELECT  SSN, FirstName,MiddleName, LastName, StreetAddress, "
                            + "City, State, ZipCode, immunizationRecord FROM matriculated");

                    text2 = "";

                    while (resultset.next()) {
                        text2 += resultset.getString(1);
                        if (text2.equals(ssnStudent.getText())) {
                            searchedStudent.setText(resultset.getString(2));

                            return;
                        }
                        text2 = "";

                    }

                    JOptionPane.showOptionDialog(null, "The student was not found\nPlease try it again",
                            "Boola Boola University", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                    ssnStudent.setText("");
                    connection.close();
                    statement.close();
                    resultset.close();

                } catch (SQLException ent) {
                    System.out.println("SQL Exception: " + ent.toString());
                }

            }
        });

        registerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int iCredit = Integer.parseInt(totalcredits.getText());
                String MSG = "";

                switch (FULLPART_TIME) {
                    case 1:

                        if (iCredit < 9) {
                            MSG += "- Full-Time students should register\nfor at least 9 credits\n";
                        }

                        break;
                    case 2:

                        if (iCredit > 6) {
                            MSG += "- Part-Time students should register\nfor 6 credits or less\n";
                        }
                        break;
                    case 3:

                        if (iCredit > 0) {
                            MSG += "- You would have to choose\nNon-credit courses\n";
                        }
                }
                if (cList.isSelectionEmpty()) {
                    MSG += "- You would have to choose\nat least one course\n";
                }
                if (ssnStudent.getText().equals("")) {
                    MSG += "- Look for a student";
                }
                if (!MSG.equals("")) {
                    JOptionPane.showOptionDialog(null, "You forgot to provide the following Information:\n\n" + MSG,
                            "Boola Boola University", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                    return;
                }
                try {
                    Statement statement = null;
                    //Connection connection = null;
                    ResultSet resultset = null;
                    //add mysql username and password
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "luz", "hashysudo123");

                    statement = connection.createStatement();
                    Object[] values = cList.getSelectedValues();

                    resultset = statement.executeQuery("SELECT SSN, courseNumber,date, time from info");
                    resultset.beforeFirst();

                    for (int i = 0; i < values.length; i++) {
                        int[] iselIndex = cList.getSelectedIndices();
                        statement.executeUpdate("INSERT INTO info(SSN, courseNumber,date,time)VALUES('" + ssnStudent.getText() + "','" + values[i].toString().subSequence(0, 7) + "','" + Day[iselIndex[i]] + "','" + Time[iselIndex[i]] + "')");
                    }
                    resultset.close();
                    connection.close();
                    statement.close();
                } catch (SQLException ti) {
                    System.out.println("SQL Exception: " + ti.toString());
                }
                reSet();
                JOptionPane.showOptionDialog(null, "The student was registered succesfully",
                        "Boola Boola University", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

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

    public void valueChanged(ListSelectionEvent e) {
        JList source = (JList) e.getSource();
        Object[] values = source.getSelectedValues();
        String text;
        int iCredit = 0, iCost = 0;

        if (FULLPART_TIME == 1) {
            for (int i = 0; i < values.length; i++) {
                text = (String) values[i];
                if (text.charAt(0) != 'N') {
                    iCredit += 3;
                }
                iCost = (iCredit <= 9) ? 285 : 265;
            }
            iCost *= iCredit / 3;
        } else if (FULLPART_TIME == 2) {
            for (int i = 0; i < values.length; i++) {
                text = (String) values[i];
                if (text.charAt(0) != 'N') {
                    iCredit += 3;
                }
                iCost += 300;
            }
        } else if (FULLPART_TIME == 3) {
            for (int i = 0; i < values.length; i++) {
                text = (String) values[i];
                if (text.charAt(0) == 'N') {
                    iCost += 150;
                }
            }
        }
        totalcost.setText("$" + Integer.toString(iCost));
        totalcredits.setText(Integer.toString(iCredit));
    }

    public void setVisible(boolean visibility) {
        scrollPane.setVisible(visibility);
        courses.setVisible(visibility);
        ssnStudent.setVisible(visibility);
        ssnLabel.setVisible(visibility);
        searchButton.setVisible(visibility);
        student.setVisible(visibility);
        searchedStudent.setVisible(visibility);
        clearButton.setVisible(visibility);
        registerButton.setVisible(visibility);
        credits.setVisible(visibility);
        cost.setVisible(visibility);
        // total.setVisible(visibility);
        totalcredits.setVisible(visibility);
        totalcost.setVisible(visibility);
        header.setVisible(visibility);
    }

    public void chooseTime(int x) {
        FULLPART_TIME = x;
    }

    public void reSet() {

        totalcredits.setText("0");
        totalcost.setText("$0");
        ssnStudent.setText(null);
        searchedStudent.setText(null);
    }

    public void readFile() {
        try {

            reSet();
            data.clear();
            Statement statement = null;
            Statement stmnt = null;
            ResultSet resultset = null;
            ResultSet rsltSet = null;
// add username and password
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "luz", "hashysudo123");
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT coursenumber,coursename,day,time,room FROM courses");
            stmnt = connection.createStatement();
            rsltSet = stmnt.executeQuery("SELECT coursenumber,coursename,day,time,room FROM coursesnoncredit");

            ResultSetMetaData metaData = resultset.getMetaData();
            ResultSetMetaData mtData = rsltSet.getMetaData();

            int nColumns = metaData.getColumnCount();
            int nncColumns = mtData.getColumnCount();

            String line = "";
            int x = 1, lnth = 0;

            if (FULLPART_TIME != 3) {
                while (resultset.next()) {
                    for (int i = 1; i <= nColumns; i++) {
                        line += resultset.getObject(i) + "   ";
                        if (i == 1) {
                            Day[lnth] = (String) resultset.getObject(i + 2);
                            Time[lnth] = (String) resultset.getObject(i + 3);
                        }
                        if (x == 5) {
                            data.addElement(line);
                            line = "";
                            x = 1;
                            continue;
                        }
                        x++;
                    }
                    lnth++;
                }
            }
            x = 1;
            line = "";

            if (FULLPART_TIME == 3) {
                while (rsltSet.next()) {
                    for (int i = 1; i <= nncColumns; i++) {
                        line += rsltSet.getObject(i) + "   ";
                        if (i == 1) {

                            Day[lnth] = (String) rsltSet.getObject(i + 2);
                            Time[lnth] = (String) rsltSet.getObject(i + 3);
                        }
                        if (x == 5) {
                            data.addElement(line);
                            line = "";
                            x = 1;
                            continue;
                        }
                        x++;
                    }
                    lnth++;
                }
            }
            cList.setModel(data);
            connection.close();
            resultset.close();
            statement.close();
            stmnt.close();
            rsltSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        }
    }
}
