package org.example;

import javax.swing.*;
import java.awt.*;
/**
 * Frame class, simulation frame
 */
public class Frame extends JFrame{
    /**
     * Frame Class constructor
     */
    Frame(){
        Hive hive = new Hive();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Bee Simulator");
        this.setLayout(new FlowLayout());
        this.add(new Panel(hive,this), BorderLayout.CENTER);
        this.add(new InfoPanel(hive),BorderLayout.EAST);
        this.pack();

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

}
