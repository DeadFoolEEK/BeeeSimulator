package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * InfoPanel class is visible in right the side of simulation screen
 */
public class InfoPanel extends JPanel implements ActionListener {
    /**
     * InfoPanel width
     */
    public final static int PANEL_WIDTH = 300;
    /**
     * InfoPanel height
     */
    public final static int PANEL_HEIGHT = 800;
    /**
     * Hive class
     */
    Hive hive;
    /**
     * Timer that refreshes visible variables values
     */
    Timer timer;
    /**
     * JLabel with "Informacje word (eng. "Information")
     */
    JLabel infoLabel;
    /**
     * JLabel that informs about nectar amount
     */
    JLabel amountOfNectarLabel;
    /**
     * JLabel that informs about bees amount
     */
    JLabel amountOfBeesLabel;
    /**
     * JLabel that shows current day number
     */
    JLabel dayInfoLabel;
    /**
     * JLabel that shows chosen bot name
     */
    JLabel botNameInfoLabel;
    /**
     * JButton that opens market (Market class)
     */
    JButton marketButton;
    /**
     * JLabel that informs about flowers amount
     */
    JLabel amountOfFlowersLabel;
    /**
     * JLabel that informs about money that bot has
     */
    JLabel botMoneyLabel;
    /**
     * JLabel that informs about amount of days to simulation end
     */
    JLabel daysLeftToSimulationLabel;
    /**
     * Panel class
     */
    Panel Panel;
    /**
     * InfoPanel class constructor
     */
    InfoPanel(Hive hive){
        timer = new Timer(100,this);
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

        daysLeftToSimulationLabel = new JLabel();
        daysLeftToSimulationLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        daysLeftToSimulationLabel.setBounds(10,200,300,100);

        amountOfNectarLabel = new JLabel();
        amountOfNectarLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        amountOfNectarLabel.setBounds(10,300,300,100);

        amountOfBeesLabel = new JLabel();
        amountOfBeesLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        amountOfBeesLabel.setBounds(10,350,300,100);

        amountOfFlowersLabel = new JLabel();
        amountOfFlowersLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        amountOfFlowersLabel.setBounds(10,400,300,100);

        marketButton = new JButton();
        marketButton.setBounds(50, 700, 200, 70);
        marketButton.setText("MARKET");
        marketButton.setFocusable(false);
        marketButton.addActionListener(this);

        botNameInfoLabel = new JLabel();
        botNameInfoLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        botNameInfoLabel.setBounds(10,700,300,100);
        botNameInfoLabel.setText("Bot " + Panel.botName + " steruje");

        botMoneyLabel = new JLabel();
        botMoneyLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        botMoneyLabel.setBounds(10,650,300,100);

        this.add(amountOfFlowersLabel);
        this.add(amountOfBeesLabel);
        this.add(infoLabel);
        this.add(dayInfoLabel);
        this.add(amountOfNectarLabel);
        this.add(amountOfBeesLabel);
        this.add(infoLabel);
        this.add(dayInfoLabel);
        this.add(daysLeftToSimulationLabel);
        this.add(amountOfNectarLabel);

        if(!Panel.botPlay){
            this.add(marketButton);
        }
        else{
            this.add(botMoneyLabel);
            this.add(botNameInfoLabel);
        }
    }
    /**
     * method from ActionListener interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        amountOfNectarLabel.setText("Nektar w ulu: " + hive.storedNectar);
        amountOfBeesLabel.setText("Ilosc pszczol: " + hive.amountOfBess);
        amountOfFlowersLabel.setText("Ilosc kwiatow: " + Panel.flowersAmount);
        daysLeftToSimulationLabel.setText("Dni do konca: " + Panel.daysLeftToSimulationEnd);
        dayInfoLabel.setText("Dzien: " + hive.getDay());
        if(e.getSource()==marketButton) {
            Market market = new Market(hive);
            market.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
        if(Panel.botPlay){
            botMoneyLabel.setText("Bot ma " + Hive.money + "$");
        }
    }

}
