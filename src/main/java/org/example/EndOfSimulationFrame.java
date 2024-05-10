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
    JButton submitAndSaveButton;
    /**
     * ArrayList with simulation results
     */
    JButton leaveWithoutSavingButton;
    ArrayList<String> simulationResults;
    /**
     * EndOfSimulationFrame Class constructor
     */
    JPanel buttonsPanel;
    JPanel simulationResultsPanel;
    EndOfSimulationFrame(ArrayList<String> simulationResults){

        this.simulationResults = simulationResults;

        this.setSize(850, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Bee Simulator - Summary");
        this.setResizable(true);

        simulationResultsPanel = new JPanel();



        buttonsPanel = new JPanel();

        buttonsPanel.setPreferredSize(new Dimension(350,100));
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setBackground(Color.ORANGE);

        submitAndSaveButton = new JButton();
        submitAndSaveButton.setPreferredSize(new Dimension(300,60));
        submitAndSaveButton.setFocusable(false);
        submitAndSaveButton.setText("Zapisz i wyjdz");
        submitAndSaveButton.addActionListener(this);

        leaveWithoutSavingButton = new JButton();
        leaveWithoutSavingButton.setPreferredSize(new Dimension(300,60));
        leaveWithoutSavingButton.setFocusable(false);
        leaveWithoutSavingButton.setText("Wyjdz bez zapisywania");
        leaveWithoutSavingButton.addActionListener(this);

        buttonsPanel.add(submitAndSaveButton);
        buttonsPanel.add(leaveWithoutSavingButton);

        //this.add(simulationResultsPanel);
        this.add(buttonsPanel);

        this.setVisible(true);
    }

    /**
     * method from ActionListener interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitAndSaveButton){
            SaveToFile save = new SaveToFile();
            save.saveResultsToFile(simulationResults);
            System.exit(0);
        }
        else if(e.getSource()==leaveWithoutSavingButton){
            System.exit(0);
        }
    }
}
