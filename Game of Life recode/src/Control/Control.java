package Control;

import Data.DataBase;
import Gui.GUI;
import Gui.GameWindow;
import Gui.MenuWindow;

import java.sql.SQLException;

public class Control
{
    private GUI gui;
    private StartGenerator startGenerator;
    private DataBase db;

    private boolean[][] grid;
    private boolean run = false;

    private boolean[][] originalGrid;
    private int generation = 0;

    public Control()
    {
        gui = new GUI(this);
        startGenerator = new StartGenerator();
        db = new DataBase();
    }

    public void openMenu()
    {
        run = false;
        gui.openMenuWindowExclusive();
    }

    public void startGame(Vector2 gridSize, String startArgs)
    {
        run = false;
        grid = new boolean[gridSize.x][gridSize.y];

        grid = startGenerator.generateStartGeneration(startArgs, grid);

        gui.openGameWindowExclusive();
        gui.drawGeneration(grid);
        gui.showGenerationIndex(generation);

        originalGrid = new boolean[grid.length][grid[0].length];
        for(int x = 0; x < grid.length; x++)
        {
            for(int y = 0; y < grid[0].length; y++)
            {
                originalGrid[x][y] = grid[x][y];
            }
        }
    }

    public void onClickStep(int generation)
    {
        if(generation == -1)
        {
            calculateNextGeneration();
        }
        else
        {
            this.generation = 0;
            for(int x = 0; x < grid.length; x++)
            {
                for(int y = 0; y < grid[0].length; y++)
                {
                    grid[x][y] = originalGrid[x][y];
                }
            }

            for(int i = 0; i < generation; i++)
            {
                calculateNextGeneration();
            }
        }

        gui.showGenerationIndex(this.generation);
        gui.drawGeneration(grid);
    }

    public void onClickRun()
    {
        run = !run;
    }

    public void update()
    {
        if(run)
        {
            calculateNextGeneration();
            gui.drawGeneration(grid);
        }
    }

    private void calculateNextGeneration()
    {
        generation++;
        gui.showGenerationIndex(generation);
        boolean[][] _grid = new boolean[grid.length][grid[0].length];

        for(int x = 0; x < grid.length; x++)
        {
            for(int y = 0; y < grid[0].length; y++)
            {
                _grid[x][y] = grid[x][y];

                int neighbours = countNeighbours(new Vector2(x, y));

                if(neighbours < 2)
                {
                    _grid[x][y] = false;
                }
                else if(neighbours > 3)
                {
                    _grid[x][y] = false;
                }
                else if(grid[x][y])
                {
                    _grid[x][y] = true;
                }
                else if(neighbours == 3)
                {
                    _grid[x][y] = true;
                }
            }
        }

        grid = _grid;
    }

    private int countNeighbours(Vector2 pos)
    {
        int count = 0;
        Vector2 offset = new Vector2(0, -1);

        for(int i = 0; i < 8; i++)
        {
            Vector2 v = Vector2.add(pos, offset);
            if(isInGrid(v))
            {
                if(grid[v.x][v.y])
                {
                    count++;
                }
            }

            offset.rotate(45);
        }

        return count;
    }

    private boolean isInGrid(Vector2 pos)
    {
        if(pos.x < 0 || pos.y < 0 || pos.x >= grid.length || pos.y >= grid[0].length)
        {
            return false;
        }

        return true;
    }

    public void setCell(float _x, float _y, boolean value)
    {
        int x = 0;
        int y = 0;

        float ratio = (float) (grid[0].length) / (float) (grid.length);

        if (grid[0].length > grid.length)
        {

            x = (int) ((float) _x / (800f / ((float) grid.length * ratio)));
            y = (int) ((float) _y / (800f / ((float) grid[0].length)));
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length)
            {
                grid[x][y] = value;
                if(this.generation == 0)
                {
                    originalGrid[x][y] = value;
                }
            }

        } else
        {
            x = (int) ((float) _x / (800f / ((float) grid.length)));
            y = (int) ((float) _y / (800f / ((float) grid[0].length / ratio)));
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length)
            {
                grid[x][y] = value;
                if(this.generation == 0)
                {
                    originalGrid[x][y] = value;
                }
            }

        }
    }

    public void saveGrid(String name)
    {
        try
        {
           db.saveGrid(grid, name);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void clearDB()
    {
        db.deleteDataBase();
        db.createDataBase();
    }
}
