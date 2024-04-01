package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EndOfSimulationFrame extends JFrame implements ActionListener {

    JButton submitButton;
    ArrayList<String> simulationResults;

    public EndOfSimulationFrame(ArrayList<String> simulationResults){
        //trzeba dodac jakies GUI, bo te wyglada jak zolnierek

        this.simulationResults = simulationResults;

        this.setSize(850, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Bee Simulator - Summary");
        this.setResizable(true);

        submitButton = new JButton();
        submitButton.setPreferredSize(new Dimension(300,60));
        submitButton.setFocusable(false);
        submitButton.setText("Zapisz");
        submitButton.addActionListener(this);

        this.add(submitButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
            SaveToFile save = new SaveToFile();
            save.saveResultsToFile(simulationResults);
            System.exit(0);
        }
    }

}
