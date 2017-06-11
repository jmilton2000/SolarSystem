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
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SolarUI extends JFrame implements ActionListener {

    private Solar solar = new Solar();
    private JFrame frame = new JFrame();
    private JFrame f = new JFrame();
    //private Color orange = new Color(255, 128, 0);
    private Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.decode("#F39C12"), Color.CYAN, Color.WHITE, Color.decode("#8E44AD"), Color.decode("#000000")};
    private String[] help = new String[]{"Red", "Green", "Blue", "Orange", "Cyan", "White", "Purple", "#000000"};

    public static void main(String[] args) {
        new SolarUI().setVisible(true);
    }

    private SolarUI() {
        setTitle(solar.getName());
        setSize(300, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 0));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 200);
        frame.setResizable(false);
        setUndecorated(true);
        setLocation(frame.getX(), 200);

        //sets up drawing panel
        JPanel panel = new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setColor(Color.black);
                        g.fillRect(0, 0, getWidth(), getHeight());
                        g.setColor(Color.yellow);
                        g.fillOval(100, solar.getSunSize()/3, solar.getSunSize(), solar.getSunSize());
                        for (int A = 0; A < solar.getPlanets().size(); A++) {
                            Planet curr = solar.getPlanets().get(A);
                            int color = curr.getColor();
                            g.setColor(colors[color]);
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
        JButton SwapButton = new JButton("Swap Planets");
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

        //adds planet
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
        SwapButton.setActionCommand("Swap");

        //makes the button work
        loadButton.addActionListener(this);
        CreateButton.addActionListener(this);
        SwapButton.addActionListener(this);

        //Creates the button onto the UI
        add(loadButton);
        add(CreateButton);
        add(SwapButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String name = actionEvent.getActionCommand();
        if (name.equals("edit")) { //runs edit method
            if (solar.getPlanets().size() != 0 && !f.isVisible()) {
                edit();
            }
        } else if (name.equalsIgnoreCase("exit")) { //exits program
            System.out.println("Closed");
            System.exit(0);
        } else if (name.equals("add")) { //adds planet
            try {
                solar.addPlanet();
            } catch (FileNotFoundException ex) {
                System.out.println("file not found");
            }
            frame.repaint();
        } else if (name.equals("over")) { //override existing file
            try {
                solar.export();
            } catch (FileNotFoundException ex) {
                System.out.println("file not found");
            }
            frame.repaint();
            f.dispose();
            setTitle(solar.getName());
        } else if (name.equals("Swap")) {
            if (solar.getPlanets().size() >= 2 && !f.isVisible()) {
                swap();
            }
        }
    }

    //for loading
    private void load() {
        f = new JFrame("Please enter System Name");
        f.setLocation(300, 200);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ArrayList<String> saves = solar.getSaves();
        f.getContentPane().setLayout(new GridLayout(2 + saves.size(), 0));
        JTextField field = new JTextField(20);
        f.add(field);
        //f.add(new JLabel("All found saves:"));
        for (int A = 0; A < saves.size(); A++) {
            f.add(new JLabel(saves.get(A)));
        }
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
                    } catch (FileNotFoundException ex) {
                        System.out.println("file not found");
                    }
                    frame.repaint();
                    f.dispose();
                    setTitle(solar.getName());
                }
            }
        });
    }

    //allows user make a new system
    private void New() {
        f = new JFrame("Please enter System Name");
        f.setLocation(300, 200);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        JTextField field = new JTextField(20);
        f.add(field);
        f.pack();
        f.setVisible(true);
        JButton over = new JButton("Replace existing file?");
        over.setActionCommand("over");
        over.addActionListener(this);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = field.getText();
                    solar = new Solar();
                    solar.setName(name);
                    File file = new File("Solar Saves/" + solar.getName() + ".sol");
                    if (!file.exists()) {
                        try {
                            solar.export();
                        } catch (FileNotFoundException ex) {
                            System.out.println("ERROR: file not found");
                        }
                        frame.repaint();
                        f.dispose();
                        setTitle(solar.getName());
                    } else {
                        f.add(over);
                        f.pack();
                    }
                }
            }
        });
    }

    //gets the planet the user wants to edit
    private void edit() {
        f = new JFrame("Please enter Planet Name");
        f.setLocation(300, 200);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        JTextField field = new JTextField(20);
        f.add(field);
        f.pack();
        f.setVisible(true);

        //if the enter key is pressed
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

    //creates and shows frame to edit planets with
    private void edit2(Planet planet) {
        f = new JFrame("Please enter Planet Info");
        f.setLocation(300, 200);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new GridLayout(4, 2));

        JButton remove = new JButton("Remove Planet");
        remove.setActionCommand("remove");

        //sets up
        JTextField f1 = new JTextField(planet.getName());
        JTextField f2 = new JTextField(Integer.toString(planet.getSize()));
        JTextField f3 = new JTextField(help[planet.getColor()]);
        JLabel L1 = new JLabel("Name:");
        JLabel L2 = new JLabel("Size (1,2,3,4):");
        JLabel L3 = new JLabel("Color:");

        //adds labels and text boxes
        f.add(L1);
        f.add(f1);
        f.add(L2);
        f.add(f2);
        f.add(L3);
        f.add(f3);
        f.add(remove);
        f.pack();
        f.setVisible(true);

        //reads text in text boxes and changes planets accordingly
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
                        f3.setText(help[planet.getColor()]);
                    }
                    if (!solar.allReady(f1.getText())) {
                        planet.setName(f1.getText());
                    }
                    if(f3.getText().startsWith("#") && f3.getText().length() < 7) {
                        f3.setText(help[7]);
                    }
                    double temp = Double.parseDouble(f2.getText());
                    int size = (int) temp;
                    int color = 0;
                    if (size > 4) {
                        size = 4;
                    } else if (size < 1) {
                        size = 1;
                    }
                    if (f3.getText().startsWith("r") || f3.getText().startsWith("R")) {
                        color = 0;
                    } else if (f3.getText().startsWith("g") || f3.getText().startsWith("G")) {
                        color = 1;
                    } else if (f3.getText().startsWith("b") || f3.getText().startsWith("B")) {
                        color = 2;
                    } else if (f3.getText().startsWith("o") || f3.getText().startsWith("O")) {
                        color = 3;
                    } else if (f3.getText().startsWith("c") || f3.getText().startsWith("C")) {
                        color = 4;
                    } else if (f3.getText().startsWith("w") || f3.getText().startsWith("W")) {
                        color = 5;
                    } else if (f3.getText().startsWith("p") || f3.getText().startsWith("P")) {
                        color = 6;
                    } else if (f3.getText().startsWith("#") && f3.getText().length() == 7) {
                        color = 7;
                        colors[7] = Color.decode(f3.getText());
                        help[7] = f3.getText();
                    }
                    if (!f3.getText().equals(help[color])) {
                        f3.setText(help[color]);
                    }
                    planet.setSize(size);
                    planet.setColor(color);
                    solar.resetDis();
                    frame.repaint();
                    try {
                        solar.export();
                    } catch (FileNotFoundException ex) {
                        System.out.println("ERROR: file not found");
                    }
                }
            }
        };

        f1.addKeyListener(enter);
        f2.addKeyListener(enter);
        f3.addKeyListener(enter);
        //what runs when the remove button is pressed
        remove.addActionListener(
                actionEvent -> {
                    solar.getPlanets().remove(planet);
                    try {
                        solar.export();
                    } catch (FileNotFoundException ex) {
                        System.out.println("ERROR: file not found");
                    }
                    solar.rename();
                    frame.repaint();
                    f.dispose();
                });
    }

    //swaps two planets
    private void swap() {
        f = new JFrame("Please enter two planets");
        f.setLocation(300, 200);
        f.setSize(250, 70);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.getContentPane().setLayout(new GridLayout(2, 0));

        JTextField f1 = new JTextField();
        JTextField f2 = new JTextField();
        JLabel l1 = new JLabel("Planet 1:");
        JLabel l2 = new JLabel("Planet 2:");
        f.add(l1);
        f.add(f1);
        f.add(l2);
        f.add(f2);
        f.setVisible(true);

        KeyAdapter enter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Planet p1 = solar.findPlanet(f1.getText());
                    Planet p2 = solar.findPlanet(f2.getText());
                    int i1 = solar.getPlanets().indexOf(p1);
                    int i2 = solar.getPlanets().indexOf(p2);
                    solar.getPlanets().set(i1, p2);
                    solar.getPlanets().set(i2, p1);
                    frame.repaint();
                    try {
                        solar.export();
                    } catch (FileNotFoundException ex) {
                        System.out.println("ERROR: file not found");
                    }
                    solar.rename();
                    f.dispose();
                }

            }
        };
        f1.addKeyListener(enter);
        f2.addKeyListener(enter);
    }
}