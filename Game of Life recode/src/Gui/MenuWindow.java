package Gui;

import Control.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MenuWindow extends JFrame
{
    private GUI gui;

    //components
    private JButton buttonClearDB, buttonStartGame, buttonLoadFromDataBase;
    private JSlider sliderGridSizeX, sliderGridSizeY;
    private JComboBox<String> comboBoxStartingMethod;
    private JLabel textFieldGridSizeX, textFieldGridSizeY;

    public MenuWindow(String title, GUI gui)
    {
        this.gui = gui;

        setTitle(title);
        getContentPane().setPreferredSize(new Dimension(800, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addComponents();
        addListeners();
    }

    private void addComponents()
    {
        //Start game button
        buttonStartGame = new JButton();
        buttonStartGame.setText("Start");
        buttonStartGame.setContentAreaFilled(false);
        buttonStartGame.setBounds(325, 340, 150, 30);
        buttonStartGame.setFocusPainted(false);
        add(buttonStartGame);

        //Clear database button
        buttonClearDB = new JButton();
        buttonClearDB.setText("Clear DB");
        buttonClearDB.setContentAreaFilled(false);
        buttonClearDB.setBounds(645, 340, 150, 30);
        buttonClearDB.setFocusPainted(false);
        add(buttonClearDB);

        //Button Load From DataBase
        buttonLoadFromDataBase = new JButton();
        buttonLoadFromDataBase.setText("From DataBase");
        buttonLoadFromDataBase.setContentAreaFilled(false);
        buttonLoadFromDataBase.setBounds(165, 340, 150, 30);
        buttonLoadFromDataBase.setFocusPainted(false);
        add(buttonLoadFromDataBase);


        //Grid size x slider
        sliderGridSizeX = new JSlider(6, 150);
        sliderGridSizeX.setBounds(300, 40, 200, 20);
        sliderGridSizeX.setValue(6);
        add(sliderGridSizeX);

        //Grid size y slider
        sliderGridSizeY = new JSlider(6, 150);
        sliderGridSizeY.setBounds(300, 90, 200, 20);
        sliderGridSizeY.setValue(6);
        add(sliderGridSizeY);

        //Starting Method ComboBox
        comboBoxStartingMethod = new JComboBox<>();
        comboBoxStartingMethod.setBounds(350, 250, 100, 20);
        comboBoxStartingMethod.addItem("Randomized");
        comboBoxStartingMethod.addItem("Task1CT");
        comboBoxStartingMethod.addItem("Empty");
        add(comboBoxStartingMethod);

        //Grid size x text
        textFieldGridSizeX = new JLabel();
        textFieldGridSizeX.setText("6");
        textFieldGridSizeX.setBounds(300, 60, 100, 10);
        add(textFieldGridSizeX);

        //Grid size y text
        textFieldGridSizeY = new JLabel();
        textFieldGridSizeY.setText("6");
        textFieldGridSizeY.setBounds(300, 110, 100, 10);
        add(textFieldGridSizeY);
    }

    private void addListeners()
    {
        //Start game button
        buttonStartGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.startGame(new Vector2(Integer.parseInt(textFieldGridSizeX.getText()), Integer.parseInt(textFieldGridSizeY.getText())),
                        comboBoxStartingMethod.getSelectedItem().toString());
            }
        });

        //Clear DB button
        buttonClearDB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.clearDB();
            }
        });

        //Load from DataBase button
        buttonLoadFromDataBase.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.onOpenSelectionClick();
            }
        });

        //Grid size x slider
        sliderGridSizeX.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                String text = String.valueOf(sliderGridSizeX.getValue());
                textFieldGridSizeX.setText(text);
            }
        });

        //Grid size y slider
        sliderGridSizeY.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                String text = String.valueOf(sliderGridSizeY.getValue());
                textFieldGridSizeY.setText(text);
            }
        });
    }
}
