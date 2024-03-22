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
    JPanel userAndBotsPanel;
    JButton button1;
    JSlider slider1;
    JSlider slider2;
    JSlider slider3;
    ImageIcon botPeterIcon;
    ImageIcon botBrunoIcon;
    ImageIcon userRadioIcon;
    JRadioButton userRadioButton;
    JRadioButton botPeterButton;
    JRadioButton botBrunoButton;
    public static int startBee;
    public static long dayTime;
    public static long nightTime;

    Settings() {
        // Creating window of settings start
        this.setSize(350, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Bee Simulator - Settings");
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
        slider1.setValue(5);
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
        slider2.setValue(10);
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
        slider3.setValue(10);
        slider3.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                nightTime = slider3.getValue(); // Aktualizacja zmiennej przy zmianie wartości suwaka
                nightTime = nightTime * 1000; // zmiana na milisekundy
            }
        });

        userAndBotsPanel = new JPanel();
        userAndBotsPanel.setPreferredSize(new Dimension(350,100));
        userAndBotsPanel.setLayout(new FlowLayout());
        userAndBotsPanel.setBackground(Color.ORANGE);

        userRadioIcon = new ImageIcon("src/main/resources/userIcon.png");
        botPeterIcon = new ImageIcon("src/main/resources/botPeterSepia.png");
        botBrunoIcon = new ImageIcon("src/main/resources/botBrunoSepia.png");

        userRadioButton = new JRadioButton();
        botPeterButton = new JRadioButton();
        botBrunoButton = new JRadioButton();

        userRadioButton.setIcon(userRadioIcon);
        botPeterButton.setIcon(botPeterIcon);
        botBrunoButton.setIcon(botBrunoIcon);

        userRadioButton.addActionListener(this);
        botPeterButton.addActionListener(this);
        botBrunoButton.addActionListener(this);

        userRadioButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        userRadioButton.setHorizontalTextPosition(SwingConstants.CENTER);
        botBrunoButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        botBrunoButton.setHorizontalTextPosition(SwingConstants.CENTER);
        botPeterButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        botPeterButton.setHorizontalTextPosition(SwingConstants.CENTER);

        userRadioButton.setText("Uzytkownik");
        botPeterButton.setText("Bot Piotrek");
        botBrunoButton.setText("Bot Bruno");

        ButtonGroup chosePlayerButtonsGroup = new ButtonGroup();
        chosePlayerButtonsGroup.add(userRadioButton);
        chosePlayerButtonsGroup.add(botPeterButton);
        chosePlayerButtonsGroup.add(botBrunoButton);

        userAndBotsPanel.add(userRadioButton);
        userAndBotsPanel.add(botPeterButton);
        userAndBotsPanel.add(botBrunoButton);

        this.add(panel);

        panel.add(label1);
        panel.add(label2);
        panel.add(slider1);
        panel.add(label3);
        panel.add(slider2);
        panel.add(label4);
        panel.add(slider3);
        panel.add(userAndBotsPanel);
        panel.add(button1);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            if(startBee != 0 ){
                Panel.beessAmount = startBee;
            }
            if(dayTime != 0){
                Panel.timeOfDay = dayTime;
            }
            if(nightTime != 0){
                Panel.timeOfNight = nightTime;
            }
        }
        if(e.getSource() == userRadioButton){
            Panel.botPlay = false;
            botPeterIcon.setImage(new ImageIcon("src/main/resources/botPeterSepia.png").getImage());
            botBrunoIcon.setImage(new ImageIcon("src/main/resources/botBrunoSepia.png").getImage());
            userRadioIcon.setImage(new ImageIcon("src/main/resources/userIcon.png").getImage());

        }
        if(e.getSource() == botPeterButton){
            Panel.botPlay = true;
            Panel.botName = "Peter";
            botPeterIcon.setImage(new ImageIcon("src/main/resources/botPeter.png").getImage());
            botBrunoIcon.setImage(new ImageIcon("src/main/resources/botBrunoSepia.png").getImage());
            userRadioIcon.setImage(new ImageIcon("src/main/resources/userIconSepia.png").getImage());
        }
        if(e.getSource() == botBrunoButton){
            Panel.botPlay = true;
            Panel.botName = "Bruno";
            botPeterIcon.setImage(new ImageIcon("src/main/resources/botPeterSepia.png").getImage());
            botBrunoIcon.setImage(new ImageIcon("src/main/resources/botBruno.png").getImage());
            userRadioIcon.setImage(new ImageIcon("src/main/resources/userIconSepia.png").getImage());
        }
    }
}
