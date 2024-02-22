package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class infopanel extends JPanel implements ActionListener {
    public final static int PANEL_WIDTH = 300;
    public final static int PANEL_HEIGHT = 800;
    Hive hive;
    Timer timer;
    JLabel infoLabel;
    JLabel amountOfNectarLabel;
    JLabel amountOfBeesLabel;
    JLabel dayInfoLabel;
    infopanel(Hive hive){
        timer = new Timer(10,this);
        timer.start();
        this.hive = hive;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(new Color(197, 143, 0));

        infoLabel = new JLabel();
        infoLabel.setText("Informacje");
        infoLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        infoLabel.setBounds(80,0,300,100);

        dayInfoLabel = new JLabel();
        dayInfoLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        dayInfoLabel.setBounds(10,250,300,100);

        amountOfNectarLabel = new JLabel();
        amountOfNectarLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        amountOfNectarLabel.setBounds(10,300,300,100);

        amountOfBeesLabel = new JLabel();
        amountOfBeesLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        amountOfBeesLabel.setBounds(10,350,300,100);

        this.add(amountOfBeesLabel);
        this.add(infoLabel);
        this.add(dayInfoLabel);
        this.add(amountOfNectarLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        amountOfNectarLabel.setText("Nektar w ulu: " + hive.storedNectar);
        amountOfBeesLabel.setText("Ilosc pszczol: " + hive.amountOfBess);
        dayInfoLabel.setText("Dzien " + Hive.day);
    }
}
