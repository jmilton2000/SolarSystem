import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class SolarUI extends JFrame implements ActionListener {

    Solar solar = new Solar();
    JFrame frame = new JFrame();
    JFrame f = new JFrame();

    public static void main(String[] args) {
        new SolarUI().setVisible(true);
    }

    private SolarUI() {
        //name of the screen
        setTitle(solar.getName());

        //makes the size of the screen width by height
        setSize(400, 200);
        setResizable(false);

        //When you close the frame, end code )
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 0));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 200);

        //sets up drawing panel
        JPanel panel =
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        setTitle(solar.getName());
                        super.paintComponent(g);
                        g.setColor(Color.black);
                        g.fillRect(0, 0, 1000, 200);
                        g.setColor(Color.yellow);
                        g.fillOval(100, solar.getSunSize()/3, solar.getSunSize(), solar.getSunSize());
                        for (int A = 0; A < solar.getPlanets().size(); A++) {
                            Planet curr = solar.getPlanets().get(A);
                            g.setColor(Color.red);
                            String color = curr.getColor();
                            color.toUpperCase();
                            if (color.equals("R")) {
                                g.setColor(Color.red);
                            }
                            if (color.equals("G")) {
                                g.setColor(Color.green);
                            }
                            if (color.equals("B")) {
                                g.setColor(Color.blue);
                            }
                            int size = curr.getSizeReal();
                            int distance = curr.getDistance();
                            g.fillOval(distance, 100 + 10 - size, size, size);
                            g.setColor(Color.white);
                            g.drawString(curr.getName(), curr.getDistance(), 30);
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
        JMenu file = new JMenu("Menu");
        //shit inside of menu in the tab
        JMenuItem newP = new JMenuItem("New");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");

        //loads sol file
        load.addActionListener(
                actionEvent -> {
                    if (!f.isVisible()) {
                        load();
                    }
                });

        newP.addActionListener(
                actionEvent -> {
                    if (!f.isVisible()) {
                        New();
                    }
                });

        //closes program
        exit.addActionListener(
                actionEvent -> {
                    System.out.println("Closed");
                    System.exit(0);
                });

        //JMenuItem extra = new JMenu("Extra");
        //JMenuItem hello = new JMenuItem("hey");
        //JMenuItem hello2 = new JMenuItem("hey yo");

        //make the shit inside the tab
        file.add(newP);
        file.add(load);
        //file.add(extra);
        file.addSeparator();
        file.add(exit);
        //makes the thing inside of the "extra" tab inside of the "stuff" tab
        //extra.add(hello);
        //extra.add(hello2);
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

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String name = actionEvent.getActionCommand();
        //you can set .equals to .equalsIgnoreCase if want
        if (name.equals("edit")) {
            if (solar.getPlanets().size() != 0 && !f.isVisible()) {
                edit();
            }
        } else if (name.equalsIgnoreCase("exit")) {
            System.out.println("Closed");
            System.exit(0);
        } else if (name.equals("add")) {
            try {
                solar.addPlanet();
            } catch (FileNotFoundException ex) {}
            frame.repaint();
        }
    }

    //for loading
    public void load() {
        f = new JFrame("Please enter System Name");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        JTextField field = new JTextField(20);
        f.add(field);
        f.pack();
        f.setVisible(true);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = field.getText();
                    solar.setName(name);
                    try {
                        solar.load(name);
                    } catch (FileNotFoundException ex) {}
                    frame.repaint();
                    f.dispose();
                    setTitle(solar.getName());
                }
            }
        });
    }

    public void New() {
        f = new JFrame("Please enter System Name");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        JTextField field = new JTextField(20);
        f.add(field);
        f.pack();
        f.setVisible(true);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = field.getText();
                    solar = new Solar();
                    solar.setName(name);
                    File file = new File("src/save/" + solar.getName() + ".sol");
                    if (!file.exists()) {
                        try {
                            solar.export();
                        } catch (FileNotFoundException ex) {
                        }
                        frame.repaint();
                        f.dispose();
                        setTitle(solar.getName());
                    }
                }
            }
        });
    }

    public void edit() {
        f = new JFrame("Please enter Planet Name");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        JTextField field = new JTextField(20);
        f.add(field);
        f.pack();
        f.setVisible(true);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = field.getText();
                    Planet planet = solar.findPlanet(name);
                    f.dispose();
                    edit2(planet);
                }
            }
        });

    }

    public void edit2(Planet planet) {
        f = new JFrame("Please enter Planet Info");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new GridLayout(4, 2));

        JButton remove = new JButton("Remove Planet");
        remove.setActionCommand("remove");

        JTextField f1 = new JTextField(planet.getName());
        JTextField f2 = new JTextField(Integer.toString(planet.getSize()));
        JTextField f3 = new JTextField(planet.getColor());

        JLabel L1 = new JLabel();
        JLabel L2 = new JLabel();
        JLabel L3 = new JLabel();

        L1.setText("Name:");
        L2.setText("Size (1,2,3,4):");
        L3.setText("Color (RGB):");

        f.add(L1);
        f.add(f1);
        f.add(L2);
        f.add(f2);
        f.add(L3);
        f.add(f3);
        f.add(remove);

        f.pack();
        f.setVisible(true);

        KeyAdapter enter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (f1.getText().equals("")) {
                        f1.setText(planet.getName());
                    }
                    if (f2.getText().equals("")) {
                        f2.setText(Integer.toString(planet.getSize()));
                    }
                    if (f3.getText().equals("")) {
                        f3.setText(planet.getColor());
                    }
                    if (solar.allready(f1.getText())) {
                    } else {
                        planet.setName(f1.getText());
                    }
                    double temp = Double.parseDouble(f2.getText());
                    int size = (int) temp;
                    String color = "R";
                    if (size > 4) {
                        size = 4;
                    } else if (size < 1) {
                        size = 1;
                    }
                    if (f3.getText().charAt(0) == 18 || f3.getText().charAt(0) == 114) {
                        color = "R";
                    } else if (f3.getText().charAt(0) == 7 || f3.getText().charAt(0) == 103) {
                        color = "G";
                    } else if (f3.getText().charAt(0) == 2 || f3.getText().charAt(0) == 98) {
                        color = "B";
                    }
                    planet.setSize(size);
                    planet.setColor(color);
                    solar.resetDis();
                    frame.repaint();
                    try {
                        solar.export();
                    } catch (FileNotFoundException ex) {}
                }
            }
        };

        f1.addKeyListener(enter);
        f2.addKeyListener(enter);
        f3.addKeyListener(enter);
        remove.addActionListener(
                actionEvent -> {
                    solar.getPlanets().remove(planet);
                    solar.resetDis();
                    frame.repaint();
                    f.dispose();
                    try {
                        solar.export();
                    } catch (FileNotFoundException ex) {
                    }
                    solar.rename();
                });
    }
}