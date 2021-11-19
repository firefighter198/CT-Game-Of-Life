package Gui;

import Control.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectionWindow extends JFrame
{
    private GUI gui;
    private List<String> optionStrings;

    public SelectionWindow(GUI gui, List<String> optionButtons)
    {
        this.gui = gui;
        this.optionStrings = optionButtons;

        setTitle("select from database");
        getContentPane().setPreferredSize(new Dimension(800, 400));
        pack();
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        addComponents();

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                gui.openMenu();
            }
        });
    }

    private void addComponents()
    {
        for(String s : optionStrings)
        {
            JButton b = new JButton();
            b.setText(s);
            b.setContentAreaFilled(false);
            b.setBounds(325, 340, 150, 30);
            b.setFocusPainted(false);
            b.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    gui.startGame(Vector2.zero(), b.getText());
                }
            });
            add(b);
        }
    }
}
