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

    public GUI(Control control)
    {
        this.control = control;
        menuWindow = new MenuWindow(title, this);
        gameWindow = new GameWindow(title, this);
    }

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

    public void drawGeneration(boolean[][] grid)
    {
        gameWindow.drawGeneration(grid);
    }

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

    public void openSelectionWindowExclusive()
    {
        menuWindow.setVisible(false);
        gameWindow.setVisible(false);
        selectionWindow = new SelectionWindow(this, readOptionsFromDataBase());
        selectionWindow.setVisible(true);
    }

    public void startGame(Vector2 gridSize, String startArgs)
    {
        control.startGame(gridSize, startArgs);
    }

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

    public void onOpenSelectionClick()
    {
        control.onOpenSelectionClick();
    }

    private List<String> readOptionsFromDataBase()
    {
        return control.readOptionsFromDataBase();
    }
}
