package org.example;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Settings class, user can adjust settings here
 */
public class Settings extends JFrame implements ActionListener {
    /**
     * Main panel
     */
    Panel Panel;
    /**
     * Declare label1
     */
    JLabel label1;
    /**
     * Declare label2
     */
    JLabel label2;
    /**
     * Declare label3
     */
    JLabel label3;
    /**
     * Declare label4
     */
    JLabel label4;
    /**
     * Declare days amount label
     */
    JLabel daysAmountLabel;
    /**
     * Declare main panel
     */
    JPanel panel;
    /**
     * Declare panel used for user and bots
     */
    JPanel userAndBotsPanel;
    /**
     * Declare button1
     */
    JButton button1;
    /**
     * Declare slider1
     */
    JSlider slider1;
    /**
     * Declare slider2
     */
    JSlider slider2;
    /**
     * Declare slider3
     */
    JSlider slider3;
    /**
     * Declare slider4
     */
    JSlider slider4;
    /**
     * Declare bot icon for bot Peter
     */
    ImageIcon botPeterIcon;
    /**
     * Declare bot icon for bot Bruno
     */
    ImageIcon botBrunoIcon;
    /**
     * Declare user radio icon
     */
    ImageIcon userRadioIcon;
    /**
     * Declare user radio Button
     */
    JRadioButton userRadioButton;
    /**
     * Declare bot Peter radio button
     */
    JRadioButton botPeterButton;
    /**
     * Declare bot Bruno radio button
     */
    JRadioButton botBrunoButton;
    /**
     * Declare checkbox for random event generator
     */
    JCheckBox randomEventGeneratorCheckBox;
    /**
     * Start bee integer
     */
    public static int startBee;
    /**
     * Day time long
     */
    public static long dayTime;
    /**
     * Night time long
     */
    public static long nightTime;
    /**
     * Check if settings is already opened or not. Prevents from opening multiple settings
     */
    private static boolean isOpened;

    /**
     * Constructor settings. Creates window of settings and all visual things and settings that are in.
     */
    Settings() {
        // Creating window of settings start
        this.setSize(350, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Bee Simulator - Settings");
        this.setResizable(false);

        setIsOpenedToTrue();
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                setIsOpenedToFalse();
            }
        };
        this.addWindowListener(listener);

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

        daysAmountLabel = new JLabel();
        daysAmountLabel.setSize(350, 30);
        daysAmountLabel.setText("Ilosc dni trwania symulacji");
        daysAmountLabel.setFont(new Font("Comic Sans",Font.BOLD,20));

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

        slider4 = new JSlider(1, 31);
        slider4.setPaintTicks(true);
        slider4.setMinorTickSpacing(1);
        slider4.setPaintLabels(true);
        slider4.setMajorTickSpacing(10);
        slider4.setValue(15);
        slider4.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Panel.maximumDaysAmount = slider4.getValue();
            }
        });

        randomEventGeneratorCheckBox = new JCheckBox("Wydarzenia losowe");
        randomEventGeneratorCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1){
                    Panel.randomEventsActived = true;
                }
                else{
                    Panel.randomEventsActived = false;
                }
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
        panel.add(daysAmountLabel);
        panel.add(slider4);
        panel.add(randomEventGeneratorCheckBox);
        panel.add(userAndBotsPanel);
        panel.add(button1);

        this.setVisible(true);
    }

    /**
     * Sets isopened to true
     */
    private void setIsOpenedToTrue(){
        isOpened = true;
    }

    /**
     *  Sets isopened to false
     */
    private void setIsOpenedToFalse(){
        isOpened = false;
    }

    /**
     * Returns boolean isopened
     */
    public static boolean getIsOpened(){
        return isOpened;
    }

    /**
     * method from ActionListener interface
     */
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
            botPeterIcon = new ImageIcon("src/main/resources/botPeter.png");
            botPeterButton.setIcon(botPeterIcon);
            botBrunoIcon = new ImageIcon("src/main/resources/botBrunoSepia.png");
            botBrunoButton.setIcon(botBrunoIcon);
            userRadioIcon = new ImageIcon("src/main/resources/userIconSepia.png");
            userRadioButton.setIcon(userRadioIcon);
        }
        if(e.getSource() == botBrunoButton){
            Panel.botPlay = true;
            Panel.botName = "Bruno";
            botPeterIcon = new ImageIcon("src/main/resources/botPeterSepia.png");
            botPeterButton.setIcon(botPeterIcon);
            botBrunoIcon = new ImageIcon("src/main/resources/botBruno.png");
            botBrunoButton.setIcon(botBrunoIcon);
            userRadioIcon = new ImageIcon("src/main/resources/userIconSepia.png");
            userRadioButton.setIcon(userRadioIcon);
        }
    }

}