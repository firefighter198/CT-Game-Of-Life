package Gui;

import Control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame
{
    private GUI gui;
    private Canvas canvas;
    private boolean mouseLeft, mouseRight;

    //components
    private JButton buttonRun, buttonStep, buttonSaveGrid;
    private JTextField textFieldSaveGridName, textFieldJumpToGeneration;

    public GameWindow(String title, GUI gui)
    {
        this.gui = gui;

        getContentPane().setPreferredSize(new Dimension(1000, 800));
        setTitle(title);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setLayout(null);

        addComponents();
        addListeners();
    }

    public void drawGeneration(boolean[][] grid)
    {
        canvas.drawGeneration(grid);
    }

    private void addComponents()
    {
        //step over/run button
        buttonRun = new JButton();
        buttonRun.setContentAreaFilled(false);
        buttonRun.setText("Run");
        buttonRun.setBounds(825, 50, 150, 50);
        buttonRun.setFocusPainted(false);
        add(buttonRun);

        //step button
        buttonStep = new JButton();
        buttonStep.setContentAreaFilled(false);
        buttonStep.setText("Step");
        buttonStep.setBounds(825, 120, 150, 50);
        buttonStep.setFocusPainted(false);
        add(buttonStep);

        //save grid button
        buttonSaveGrid = new JButton();
        buttonSaveGrid.setContentAreaFilled(false);
        buttonSaveGrid.setText("Save Grid");
        buttonSaveGrid.setBounds(825, 260, 150, 50);
        buttonSaveGrid.setFocusPainted(false);
        add(buttonSaveGrid);

        //save grid name textField
        textFieldSaveGridName = new JTextField();
        textFieldSaveGridName.setText("Enter Name...");
        textFieldSaveGridName.setBounds(825, 330, 150, 50);
        add(textFieldSaveGridName);

        //jump to generation textField
        textFieldJumpToGeneration = new JTextField();
        textFieldJumpToGeneration.setText("Enter Generation...");
        textFieldJumpToGeneration.setBounds(825, 190, 150,50);
        add(textFieldJumpToGeneration);

        //drawing canvas
        canvas = new Canvas();
        canvas.setBounds(0, 0, 800, 800);
        add(canvas);
    }

    private void addListeners()
    {
        //step over/run button
        buttonRun.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.onClickRun();
            }
        });

        //step button
        buttonStep.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int generation = -1;

                try
                {
                    generation = Integer.parseInt(textFieldJumpToGeneration.getText());
                }
                catch(Exception exc)
                {

                }

                textFieldJumpToGeneration.setText("Enter Generation...");
                gui.onClickStep(generation);
            }
        });

        //save grid button
        buttonSaveGrid.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.saveGrid(textFieldSaveGridName.getText());
                textFieldSaveGridName.setText("Enter Name...");
            }
        });

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                gui.openMenu();
            }
        });

        canvas.addMouseMotionListener(new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if(mouseLeft)
                {
                    gui.setCell(e.getX(), e.getY(), true);
                }
                else if(mouseRight)
                {
                    gui.setCell(e.getX(), e.getY(), false);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e)
            {

            }
        });

        canvas.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                switch(e.getButton())
                {
                    case 1:
                        mouseLeft = true;
                        gui.setCell(e.getX(), e.getY(), true);
                        break;
                    case 3:
                        mouseRight = true;
                        gui.setCell(e.getX(), e.getY(), false);
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                switch(e.getButton())
                {
                    case 1:
                        mouseLeft = false;
                        break;
                    case 3:
                        mouseRight = false;
                        break;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
    }
}
