/**
 * Created by ros_rcsoyama on 5/11/2017.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.io.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.*;
import javax.swing.JTextField;


public class SolarUI extends JFrame implements ActionListener {

    Solar solar = new Solar();
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();

    public static void main(String[] args) {
        new SolarUI().setVisible(true);
    }

    private SolarUI() {
        //name of the screen
        super("Start Screen");

        //makes the size of the screen width by height
        setSize(1000, 500);
        setResizable(false);

        //When you close the frame, end code )
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 10));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 200);


        JPanel panel =
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setColor(Color.black);
                        g.fillRect(0, 0, 1000, 200);
                        g.setColor(Color.yellow);
                        g.fillOval(100, 50, 80, 80);
                        for (int A = 0; A < solar.getPlanets().size(); A++) {
                            g.setColor(Color.blue);
                            int size = solar.getPlanets().get(A).getSize();
                            int distance = solar.getPlanets().get(A).getDistance();
                            g.fillOval(distance, 100 + 10 - size, size, size);
                        }
                    }
                };
        frame.add(panel);
        frame.validate();
        frame.repaint();

      /*
      (this is for above)
       FlowLayout
       GridBagLayout
       GridLayout (row,column)
       BorderLayout ( add(button, BorderLayout.WEST)CENTER)
       */

        //makes a new button
        JButton loadButton = new JButton("Edit Planet");
        JButton CreateButton = new JButton("Add Planet");

        //menu bar

        JMenuBar bar = new JMenuBar();
        //name of the tab on bar
        JMenu file = new JMenu("STUFF");
        //shit inside of menu in the tab
        JMenuItem newP = new JMenuItem("New");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");

        //closes shit
        load.addActionListener(
                actionEvent -> {
                    JFrame f = new JFrame("Please enter System Name");
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.getContentPane().setLayout(new FlowLayout());
                    JTextField field = new JTextField(20);
                    f.add(field);
                    f.pack();
                    f.setVisible(true);


                    try {
                        solar.load("SolarSystem");
                    } catch (FileNotFoundException ex) {
                    }
                    System.out.print(solar.getPlanets().size());
                    frame.repaint();
                });

        exit.addActionListener(
                actionEvent -> {
                    System.out.println("Closed");
                    System.exit(0);
                });

        JMenuItem extra = new JMenu("Extra");
        JMenuItem hello = new JMenuItem("hey");
        JMenuItem hello2 = new JMenuItem("hey yo");

        //make the shit inside the tab
        file.add(newP);
        file.add(load);
        file.add(extra);
        file.addSeparator();
        file.add(exit);
        //makes the thing inside of the "extra" tab inside of the "stuff" tab
        extra.add(hello);
        extra.add(hello2);
        //makes the tab inside the bar
        bar.add(file);
        setJMenuBar(bar);

        //makes the action listener parameters this
        loadButton.setActionCommand("edit");
        CreateButton.setActionCommand("add");

        //makes the button work
        loadButton.addActionListener(this);
        CreateButton.addActionListener(this);

        //Creates the button onto the UI
        add(loadButton);
        add(CreateButton);

        //add(drawing);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String name = actionEvent.getActionCommand();
        //you can set .equals to .equalsIgnoreCase if want
        if (name.equals("edit")) {
            System.out.println("Good shit");
        } else if (name.equalsIgnoreCase("exit")) {
            System.out.println("Closed");
            System.exit(0);
        } else if (name.equals("add")) {
            try {
                solar.addPlanet();
            } catch (FileNotFoundException ex) {
            }
            frame.repaint();
        }
    }
}