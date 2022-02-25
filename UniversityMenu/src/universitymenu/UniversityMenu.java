package universitymenu;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Luz M Agudelo
 */
public class UniversityMenu {

    JFrame frame;

    Matriculated matriculate;

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("BOOLA BOOLA University");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        BufferedImage img = ImageIO.read(new File("..\\UniversityMenu\\src\\universitymenu\\image.jpg"));

        JLabel ba = new JLabel(new ImageIcon(img), JLabel.CENTER);
        ba.setBounds(0, 0, 300, 300);
//frame.add(ba);
        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu admiMenu = new JMenu("Admissions");
        JMenu regiMenu = new JMenu("Registation");
        JMenu repoMenu = new JMenu("Reports");

        //add to menu bar
        menuBar.add(admiMenu);
        menuBar.add(regiMenu);
        menuBar.add(repoMenu);

        //add items to the menu admissions
        JMenuItem matriculatedItem = new JMenuItem("matriculated");
        JMenuItem nonMatriculatedItem = new JMenuItem("non-matriculated");
        JMenuItem quitItem = new JMenuItem("Quit");
        admiMenu.add(matriculatedItem);
        admiMenu.add(nonMatriculatedItem);
        admiMenu.add(quitItem);
        //add items to the menu registation
        JMenuItem fullTimeItem = new JMenuItem("Full Time");
        JMenuItem partTimeItem = new JMenuItem("Part Time");
        JMenuItem nonCreditItem = new JMenuItem("Non-Credit");
        regiMenu.add(fullTimeItem);
        regiMenu.add(partTimeItem);
        regiMenu.add(nonCreditItem);
        //add items to the menu report
        JMenuItem receivablesItem = new JMenuItem("Receivables");
        JMenuItem scheduleItem = new JMenuItem("Class schedule");
        repoMenu.add(receivablesItem);
        repoMenu.add(scheduleItem);

        matriculatedItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Matriculated m = new Matriculated();

            }
        });
        nonMatriculatedItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                NonMatriculated nonMatriculated = new NonMatriculated();

            }
        });
        fullTimeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fullTime fulltime = new fullTime();
                fulltime.chooseTime(1);
                fulltime.readFile();

            }
        });
        partTimeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fullTime partTime = new fullTime();
                partTime.chooseTime(2);
                partTime.readFile();

            }
        });
        nonCreditItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fullTime nonCredit = new fullTime();

                nonCredit.chooseTime(3);
                nonCredit.readFile();
            }
        });
        receivablesItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reports report = new reports();

                report.setOption(1);
                report.reSet();
            }
        });
        scheduleItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reports schedule = new reports();

                schedule.setOption(2);
                schedule.reSet();
            }
        });
        quitItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int answer = JOptionPane.showOptionDialog(frame, "Are you sure you want to quit?",
                        "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (answer == 0) {
                    System.exit(0);
                }
            }
        });
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.add(ba);
        frame.setVisible(true);

    }
}
