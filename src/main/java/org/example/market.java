package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class market extends JPanel implements ActionListener {
    JFrame frame;
    JLabel amountOfHoney;
    JLabel moneyLabel;
    JLabel beePriceLabel;
    JButton button1;
    JButton button2; 

    JButton button3;
    Hive hive;
    Timer timer;
    Timer timeron;
    double value = 3.50;
    JTextField honeyAmountField;
    JTextField beeAmountField;
    static Random random = new Random();
    int beePrice;
    // int beessAmount;
    Panel Panel;
    Bee Bee;
    private boolean isNightChanged = false;
    

   public market(Hive hive){
        
        this.hive = hive;
        
        // Frame of marketplace
        frame = new JFrame();
        frame.setSize(400, 550);
        frame.setTitle("Market");
       

        // --> panels
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(600,250));

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel2.setPreferredSize(new Dimension(600,250));
        
        // --> Sets amountOfHoney live
        amountOfHoney = new JLabel();
        amountOfHoney.setFont(new Font("Comic Sans",Font.BOLD,30));
        amountOfHoney.setBounds(0,40,350,100);
        amountOfHoney.setText("Posiadany miod: " + hive.storedNectar);

        // --> money label
        moneyLabel = new JLabel();
        moneyLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        moneyLabel.setBounds(0,0,350,100);
        moneyLabel.setText("Stan konta: " + Hive.money + "$");

        // --> price of bee label
        beePriceLabel = new JLabel();
        beePriceLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
        beePriceLabel.setBounds(0,80,350,100);
        beePriceLabel.setText("Cena za pszczole: " + beePrice + "$");


        // --> set amount of honey to sell || bees to buy 
        honeyAmountField = new JTextField();
        honeyAmountField.setPreferredSize(new Dimension(70, 30));
        honeyAmountField.setToolTipText("Podaj ilosc miodu do sprzedazy");

        beeAmountField = new JTextField();
        beeAmountField.setPreferredSize(new Dimension(70,30));
        beeAmountField.setToolTipText("Podaj ilosc pszczol do zakupu");

        // --> Buttons
        button1 = new JButton();
        button1.setFocusable(false);
        button1.setText("Sprzedaj");
        button1.setPreferredSize(new Dimension(300, 50));
        button1.addActionListener(this);

        button2 = new JButton();
        button2.setPreferredSize(new Dimension(300, 50));
        button2.addActionListener(this);
        button2.setText("Zakup pszczole, aktualna cena: " + beePrice);
        button2.setFocusable(false);

        button3 = new JButton();
        button3.setPreferredSize(new Dimension(300, 50));
        button3.addActionListener(this);
        button3.setText("Podaj amfetamine");

        

        // --> add
        frame.add(this, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);

        panel2.add(honeyAmountField);
        panel2.add(button1);
        panel2.add(beeAmountField);
        panel2.add(button2);
        panel2.add(button3);

        this.add(moneyLabel);
        this.add(amountOfHoney);
        this.add(beePriceLabel);

    
        frame.setVisible(true);

    // --> timer
        timer = new Timer(1000, this); 
        
        timer.start();
        
        


        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        amountOfHoney.setText("Posiadany miod: " + hive.storedNectar);
        
        if (e.getSource() ==button1) {
            String honeyAmountText = honeyAmountField.getText();
            try {
                int amountToSell = Integer.parseInt(honeyAmountText);
                
                if (amountToSell > 0 && amountToSell <= hive.storedNectar) {
                    hive.storedNectar -= amountToSell;
                    Hive.money += value * amountToSell; // Wartość * ilość, ilosc sobie mozna zmienic albo zrobimy to jakos randomowo
                    amountOfHoney.setText("Posiadany miod: " + hive.storedNectar);
                    moneyLabel.setText("Stan konta: " + Hive.money + "$");
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Nie masz tyle miodu!", "Blad", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Podaj liczbe calkowita", "Blad", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        if (e.getSource() ==button2) {
            String beeAmounText = beeAmountField.getText();
            if (Panel.isNight) {
                try {
                    int amountToBuy = Integer.parseInt(beeAmounText);
                    if (amountToBuy > 0) {
                        if (Hive.money >= beePrice * amountToBuy) { // Sprawdzenie, czy użytkownik ma wystarczająco dużo pieniędzy
                            Hive.money -= beePrice * amountToBuy; // Odejmowanie ceny pszczółek od stanu konta
                            moneyLabel.setText("Stan konta: " + Hive.money + "$");
                            Panel.beessAmount += amountToBuy;
                            hive.amountOfBess += amountToBuy;
                        } else {
                            JOptionPane.showMessageDialog(frame, "Nie masz wystarczajacej ilosci pieniedzy!", "Blad", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Podaj dodatnia liczbe pszczol do zakupu!", "Blad", JOptionPane.ERROR_MESSAGE);
                    }
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Podaj liczbe calkowita", "Blad", JOptionPane.ERROR_MESSAGE);
                }

                
            }
    
                }
                if(e.getSource()==button3){
                    // Bee.xVelocity++;
                    // Bee.yVelocity++;
                    if(Panel.isNight) {
                    Panel.flowersAmount++;
                    // System.out.println(Panel.flowersAmount); // rise amount of flowers - to do
                }
                }
                if(Panel.isNight && !isNightChanged) {
                    updateBeePrice(random.nextInt(30) + 20);
                    isNightChanged = true; 
                } else if (!Panel.isNight) {
                    isNightChanged = false; 
                }
            }
            public void updateBeePrice(int newPrice) {
               
                    beePrice = newPrice;
                    beePriceLabel.setText("Cena za pszczole: " + beePrice + "$");
                    button2.setText("Zakup pszczole, aktualna cena: " + beePrice + "$");
                
            }
        }

            
            
        
        
    
    

    
