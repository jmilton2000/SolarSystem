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

public class SolarUI extends JFrame implements ActionListener {

    public static void main(String[] args) {

        SolarUI frame = new SolarUI();
        frame.setVisible(true);
    }

    private SolarUI() {
        //name of the screen
        super("Start Screen");

        //makes the size of the screen width by height
        setSize(1000, 300);
        setResizable(false);

        //When you close the frame, end code
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        /*
        (this is for above)
         FlowLayout
         GridBagLayout
         GridLayout (row,column)
         BorderLayout ( add(button, BorderLayout.WEST)CENTER)
         */

        //makes a new button
        JButton loadButton = new JButton("blank1");
        JButton CreateButton = new JButton("blank2");

        //menu bar

        JMenuBar bar = new JMenuBar();
        //name of the tab on bar
        JMenu file = new JMenu("STUFF");
        //shit inside of menu in the tab
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");

        //closes shit
        exit.addActionListener(actionEvent -> {
            System.out.println("Closed");
            System.exit(0);
        });

        JMenuItem extra = new JMenu("Extra");
        JMenuItem hello = new JMenuItem("hey");
        JMenuItem hello2 = new JMenuItem("hey yo");

        //make the shit inside the tab
        file.add(save);
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
        loadButton.setActionCommand("Test");
        CreateButton.setActionCommand("Teeest");

        //makes the button work
        loadButton.addActionListener(this);
        CreateButton.addActionListener(this);

        //Creates the button onto the UI
        add(loadButton);
        add(CreateButton);

        Drawing drawing = new Drawing();
        add(drawing);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String name = actionEvent.getActionCommand();
        //you can set .equals to .equalsIgnoreCase if want
        if (name.equals("Test")) {
            System.out.println("Good shit");

        } else if (name.equals("Teeest")) {
            System.out.println("Good job");
        } else if (name.equalsIgnoreCase("exit")) {
            System.out.println("Closed");
            System.exit(0);
        }
    }
}