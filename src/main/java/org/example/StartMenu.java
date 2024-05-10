package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * StartMenu class, main menu
 */
public class StartMenu extends JFrame implements ActionListener {
    /**
     * Left panel, empty yellow panel
     */
    JPanel panel1;
    /**
     * Main panel that contains title  (JLabel mainTitle), start simulation button (button1 JButton), simulation setting button (button2 JButton), exit button (button3 JButton)
     */
    JPanel panel2;
    /**
     * Right panel, empty yellow panel
     */
    JPanel panel3;
    /**
     * Title label
     */
    JLabel mainTitle;
    /**
     * Button to start simulation
     */
    JButton button1;
    /**
     * Button to open settings
     */
    JButton button2;
    /**
     * Button to close simulation
     */
    JButton button3;
    /**
     * Button to open readme (manual)
     */
    JButton readMeButton;
    /**
     * Class constructor
     */
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

        readMeButton = new JButton();
        readMeButton.setPreferredSize(new Dimension(300,60));
        readMeButton.setFocusable(false);
        readMeButton.setText("Instrukcja");
        readMeButton.addActionListener(this);

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
        panel2.add(readMeButton);
        panel2.add(button3);

        //center window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getSize().width;
        int frameHeight = this.getSize().height;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        this.setLocation(x, y);

        this.setVisible(true);
    }
    /**
     * method from ActionListener interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            this.setVisible(false);
            new Frame();
        }
        if(e.getSource()==button2) {
            if(!Settings.getIsOpened()){
                new Settings();
            }
        }
        if(e.getSource()==button3) {
            System.exit(0);
        }
        if(e.getSource()==readMeButton){
            new ReadMeLauncher();
        }
    }
}

