package org.example;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Settings extends JFrame implements ActionListener {
    Panel Panel;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JPanel panel;

    JButton button1;
    JSlider slider1;
    JSlider slider2;
    JSlider slider3;
    public static int startBee;
    public static long dayTime;
    public static long nightTime;

    Settings() {
        // Creating window of settings start
        this.setSize(350, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Bee Simulator Settings");
        this.setResizable(false);

        // Creating Label
        label1 = new JLabel();
        label1.setSize(350, 50);
        label1.setText("USTAWIENIA");
        label1.setFont(new Font("Comic Sans",Font.BOLD,25));

        label2 = new JLabel();
        label2.setSize(350, 30);
        label2.setText("Ilosc pszczol na start");
        label2.setFont(new Font("Comic Sans",Font.BOLD,20));

        label3 = new JLabel();
        label3.setSize(350, 30);
        label3.setText("Dlugosc dnia (sekundy)");
        label3.setFont(new Font("Comic Sans",Font.BOLD,20));

        label4 = new JLabel();
        label4.setSize(350, 30);
        label4.setText("Dlugosc nocy (sekundy)");
        label4.setFont(new Font("Comic Sans",Font.BOLD,20));



        // Creating panel
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350,500));
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.ORANGE);



        // Creating buttons
        button1 = new JButton();
        button1.setFocusable(false);
        button1.setPreferredSize(new Dimension(300, 50));
        button1.setText("Zatwierdz ustawienia symulacji");
        button1.addActionListener(this);

        // Creating sliders
        slider1 = new JSlider(0, 10);
        slider1.setPaintTicks(true);
        slider1.setMinorTickSpacing(1);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(2);
        slider1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                startBee = slider1.getValue(); // Aktualizacja zmiennej przy zmianie wartości suwaka
            }
        });


        slider2 = new JSlider(5, 15);
        slider2.setPaintTicks(true);
        slider2.setMinorTickSpacing(1);
        slider2.setPaintLabels(true);
        slider2.setMajorTickSpacing(5);
        slider2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                dayTime = slider2.getValue(); // Aktualizacja zmiennej przy zmianie wartości suwaka
                dayTime = dayTime * 1000; // zmiana na milisekundy
            }
        });

        slider3 = new JSlider(5, 15);
        slider3.setPaintTicks(true);
        slider3.setMinorTickSpacing(1);
        slider3.setPaintLabels(true);
        slider3.setMajorTickSpacing(5);
        slider3.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                nightTime = slider3.getValue(); // Aktualizacja zmiennej przy zmianie wartości suwaka
                nightTime = nightTime * 1000; // zmiana na milisekundy
            }
        });


        this.add(panel);

        panel.add(label1);
        panel.add(label2);
        panel.add(slider1);
        panel.add(label3);
        panel.add(slider2);
        panel.add(label4);
        panel.add(slider3);
        panel.add(button1);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            Panel.beessAmount = startBee;
            Panel.timeOfDay = dayTime;
            Panel.timeOfNight = nightTime;
        }
    }
}
