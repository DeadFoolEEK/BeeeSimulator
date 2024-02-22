package org.example;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    Frame(){
        Hive hive = new Hive();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Bee Simulator");
        this.setLayout(new FlowLayout());
        this.add(new Panel(hive), BorderLayout.CENTER);
        this.add(new infopanel(hive),BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
    }

}
