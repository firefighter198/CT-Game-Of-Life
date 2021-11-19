package Gui;

import Control.*;

import javax.swing.*;
import java.util.List;

public class GUI
{
    private Control control;
    private MenuWindow menuWindow;
    private GameWindow gameWindow;
    private SelectionWindow selectionWindow;

    private String title = "Conway game of life";

    //create the basic windows
    public GUI(Control control)
    {
        this.control = control;
        menuWindow = new MenuWindow(title, this);
        gameWindow = new GameWindow(title, this);
    }

    //update the generation index
    public void showGenerationIndex(int generation)
    {
        gameWindow.setTitle(title + " Generation: " + generation);
    }

    public void onClickStep(int generation)
    {
        control.onClickStep(generation);
    }

    public void onClickRun()
    {
        control.onClickRun();
    }

    //draw a new generation to window
    public void drawGeneration(boolean[][] grid)
    {
        gameWindow.drawGeneration(grid);
    }

    //just open menu window
    public void openMenuWindowExclusive()
    {
        gameWindow.setVisible(false);
        menuWindow.setVisible(true);
        if(selectionWindow != null)
        {
            selectionWindow.setVisible(false);
            selectionWindow = null;
        }
    }

    //just open game window
    public void openGameWindowExclusive()
    {
        menuWindow.setVisible(false);
        gameWindow.setVisible(true);
        if(selectionWindow != null)
        {
            selectionWindow.setVisible(false);
            selectionWindow = null;
        }
    }

    //just open selection window
    public void openSelectionWindowExclusive()
    {
        menuWindow.setVisible(false);
        gameWindow.setVisible(false);
        selectionWindow = new SelectionWindow(this, readOptionsFromDataBase());
        selectionWindow.setVisible(true);
    }

    //start the game
    public void startGame(Vector2 gridSize, String startArgs)
    {
        control.startGame(gridSize, startArgs);
    }


    //open the menu
    public void openMenu()
    {
        control.openMenu();
    }


    public void setCell(float x, float y, boolean value)
    {
        control.setCell(x, y, value);
    }

    public void saveGrid(String name)
    {
        control.saveGrid(name);
    }

    public void clearDB()
    {
        control.clearDB();
    }

    //button function
    public void onOpenSelectionClick()
    {
        control.onOpenSelectionClick();
    }


    //loop through function
    private List<String> readOptionsFromDataBase()
    {
        return control.readOptionsFromDataBase();
    }
}
