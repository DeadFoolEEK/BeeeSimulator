package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame implements ActionListener {
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JLabel mainTitle;
    JButton button1;
    JButton button2;
    JButton button3;

    StartMenu() {

        ImageIcon icon = new ImageIcon("src/main/resources/bee.gif");
        // Creating window of menu start
        this.setSize(850, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Bee Simulator");
        this.setResizable(true);

        // Creating panels
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(250,800));
        panel1.setBackground(Color.ORANGE);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(330, 800));
        panel2.setBackground(Color.ORANGE);

        panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(250, 800));
        panel3.setBackground(Color.ORANGE);

        // Creating main JLabel
        mainTitle = new JLabel();
        mainTitle.setSize(300, 400);
        mainTitle.setText("Bee Simulator");
        mainTitle.setVerticalTextPosition(JLabel.TOP);
        mainTitle.setHorizontalTextPosition(JLabel.CENTER);
        mainTitle.setFont(new Font("Comic Sans",Font.BOLD,30));
        mainTitle.setIcon(icon);

        // Creating Buttons
        button1 = new JButton();
        button1.setPreferredSize(new Dimension(300,60));
        button1.setFocusable(false);
        button1.setText("Rozpocznij symulacje");
        button1.addActionListener(this);
        
        button2 = new JButton();
        button2.setPreferredSize(new Dimension(300,60));
        button2.setFocusable(false);
        button2.setText("Ustawienia symulacji");
        button2.addActionListener(this);

        button3 = new JButton();
        button3.setPreferredSize(new Dimension(300,60));
        button3.setFocusable(false);
        button3.setText("Wyjscie");
        button3.addActionListener(this);

        // --> adds
        this.add(panel1, BorderLayout.WEST);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.EAST);

        panel2.add(mainTitle);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            this.setVisible(false);
            new Frame();
        }
        if(e.getSource()==button2) {
            new Settings();
        }
        if(e.getSource()==button3) {
            System.exit(0);
        }
    }
}

