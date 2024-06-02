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
    /**
     * ArrayList that contains simulations results saved as strings
     */
    ArrayList<String> simulationResults;
    /**
     * EndOfSimulationFrame Class constructor
     */
    JPanel mainPanel;
    JPanel buttonsPanel;
    JLabel titleLabel;
    JLabel beePriceLabel;
    JPanel simulationResultsPanel;
    JLabel simulationDaysAmountInfo;
    JLabel beesAmountInfo;
    JLabel nectarPriceInfo;
    JLabel beesAtBeginningCostInfo;
    JLabel beesAtTheEndEarningsInfo;
    JLabel moneyFromSoldNectar;
    JLabel moneyLeftInfo;
    JLabel moneyCollectedInfo;
    private final int startingBeesAmount;
    private final int endBeesAmount;
    private final int beePrice = 30;
    private final double nectarPrice = 3.5;
    private int costsOfBuyingBeesAtBeginning;
    private int moneyFromSellingBessAtTheEnd;
    private double moneyLeftFromNectar;
    private double finalIncome;

    EndOfSimulationFrame(ArrayList<String> simulationResults, int startingBeesAmount, int endBeesAmount){

        this.simulationResults = simulationResults;
        this.startingBeesAmount = startingBeesAmount;
        this.endBeesAmount = endBeesAmount;

        this.setSize(850, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Bee Simulator - Summary");
        this.setResizable(true);

        computeFinalIncome();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.ORANGE);

        simulationResultsPanel = new JPanel();
        simulationResultsPanel.setPreferredSize(new Dimension(850,500));
        simulationResultsPanel.setLayout(new BoxLayout(simulationResultsPanel, BoxLayout.Y_AXIS));
        simulationResultsPanel.setBackground(Color.ORANGE);

        titleLabel = new JLabel();
        beePriceLabel = new JLabel();
        simulationDaysAmountInfo = new JLabel();
        beesAmountInfo = new JLabel();
        beesAtBeginningCostInfo = new JLabel();
        beesAtTheEndEarningsInfo = new JLabel();
        moneyCollectedInfo = new JLabel();
        moneyFromSoldNectar = new JLabel();
        moneyLeftInfo = new JLabel();
        nectarPriceInfo = new JLabel();

        titleLabel.setText("Podsumowanie");
        titleLabel.setFont(new Font("Comic Sans",Font.BOLD,40));

        beePriceLabel.setText("Cena za pszczole: " + beePrice + "$");
        beePriceLabel.setFont(new Font("Comic Sans",Font.BOLD,25));

        nectarPriceInfo.setText("Cena za nektar: " + nectarPrice + "$");
        nectarPriceInfo.setFont(new Font("Comic Sans",Font.BOLD,25));

        simulationDaysAmountInfo.setText(simulationResults.get(0));
        simulationDaysAmountInfo.setFont(new Font("Comic Sans",Font.BOLD,25));

        beesAmountInfo.setText(simulationResults.get(1) + ", sprzedano je za: " + moneyFromSellingBessAtTheEnd + "$");
        beesAmountInfo.setFont(new Font("Comic Sans",Font.BOLD,25));

        beesAtBeginningCostInfo.setText("Koszt kupna " + startingBeesAmount + " pszczol na poczatku: " + costsOfBuyingBeesAtBeginning + "$");
        beesAtBeginningCostInfo.setFont(new Font("Comic Sans",Font.BOLD,25));

        if(endBeesAmount > startingBeesAmount){
            beesAtTheEndEarningsInfo.setText("Na sprzedazy pszczol zarobiono: " + (moneyFromSellingBessAtTheEnd - costsOfBuyingBeesAtBeginning + "$"));
        }
        else{
            beesAtTheEndEarningsInfo.setText("Na sprzedazy pszczol stracono: " + (-(moneyFromSellingBessAtTheEnd - costsOfBuyingBeesAtBeginning) + "$"));
        }
        beesAtTheEndEarningsInfo.setFont(new Font("Comic Sans",Font.BOLD,25));

        moneyFromSoldNectar.setText("Za sprzedaz zaleglego (" + Hive.storedNectar + ") nektaru zyskano: " + moneyLeftFromNectar + "$");
        moneyFromSoldNectar.setFont(new Font("Comic Sans",Font.BOLD,25));

        moneyLeftInfo.setText("Zostalo Ci " + Hive.money + "$");
        moneyLeftInfo.setFont(new Font("Comic Sans",Font.BOLD,25));

        if(finalIncome > 0){
            moneyCollectedInfo.setText("Calkowicie zarobiono: " + finalIncome + "$");
        }
        else if(finalIncome < 0){
            moneyCollectedInfo.setText("Calkowicie stracono: " + (-finalIncome) + "$");
        }
        else{
            moneyCollectedInfo.setText("Wyszedles/as na 0");
        }
        moneyCollectedInfo.setFont(new Font("Comic Sans",Font.BOLD,25));

        simulationResultsPanel.add(titleLabel);
        simulationResultsPanel.add(beePriceLabel);
        simulationResultsPanel.add(nectarPriceInfo);
        simulationResultsPanel.add(simulationDaysAmountInfo);
        simulationResultsPanel.add(beesAmountInfo);
        simulationResultsPanel.add(beesAtBeginningCostInfo);
        simulationResultsPanel.add(beesAtTheEndEarningsInfo);
        simulationResultsPanel.add(moneyFromSoldNectar);
        simulationResultsPanel.add(moneyLeftInfo);
        simulationResultsPanel.add(moneyCollectedInfo);

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

        mainPanel.add(simulationResultsPanel);
        mainPanel.add(buttonsPanel);

        this.add(mainPanel);

        this.setVisible(true);

        addToSimulationResults();
    }

    private void computeFinalIncome(){
        costsOfBuyingBeesAtBeginning = beePrice * startingBeesAmount;
        moneyFromSellingBessAtTheEnd = beePrice * endBeesAmount;
        moneyLeftFromNectar = nectarPrice * Hive.storedNectar;
        finalIncome = Hive.money + moneyFromSellingBessAtTheEnd - costsOfBuyingBeesAtBeginning + moneyLeftFromNectar;
    }

    private void addToSimulationResults(){
        simulationResults.add("Ilosc pszczol na poczatku: " + startingBeesAmount);
        if(endBeesAmount > startingBeesAmount){
            simulationResults.add("Zysk za pszczoly: " + (moneyFromSellingBessAtTheEnd - costsOfBuyingBeesAtBeginning + "$"));
        }
        else{
            simulationResults.add("Strata za pszczoly: " + (-(moneyFromSellingBessAtTheEnd - costsOfBuyingBeesAtBeginning) + "$"));
        }
        simulationResults.add("Zysk ze sprzedazy nektaru na koncu: " + moneyLeftFromNectar + "$");
        if(finalIncome > 0){
            simulationResults.add("Zarobiles/as " + finalIncome + "$");
        }
        else if(finalIncome < 0){
            simulationResults.add("Straciles/as " + (-finalIncome) + "$");
        }
        else{
            simulationResults.add("Wyszedles/as na 0");
        }
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
