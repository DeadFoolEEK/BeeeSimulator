package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * EndOfSimulationFrame class, used to show simulation results to user
 */
public class EndOfSimulationFrame extends JFrame implements ActionListener {
    /**
     * Button to submit saving results to file
     */
    JButton submitButton;
    /**
     * ArrayList with simulation results
     */
    ArrayList<String> simulationResults;
    /**
     * Class constructor
     */
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
    /**
     * method from ActionListener interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
            SaveToFile save = new SaveToFile();
            save.saveResultsToFile(simulationResults);
            System.exit(0);
        }
    }

}
