package Control;

import Data.DataBase;

import java.sql.SQLException;

public class StartGenerator
{
    private DataBase db = new DataBase();

    public boolean[][] generateStartGeneration(String name, boolean[][] grid)
    {
        switch(name)
        {
            case "Randomized":
                generateRandomized(grid);
                break;
            case "Task1CT":
                generateCTTask1(grid);
                break;
            case "Empty":
                break;
            default:
                return loadFromDataBase(name);
        }

        return grid;
    }

    private boolean[][] loadFromDataBase(String name)
    {

        try
        {
            return db.loadGrid(name);
        } catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }

        return null;
    }

    private void generateCTTask1(boolean[][] grid)
    {
        grid[1][2] = true;
        grid[2][2] = true;
        grid[2][4] = true;
        grid[3][1] = true;
        grid[3][3] = true;
        grid[4][3] = true;
    }

    private void generateRandomized(boolean[][] grid)
    {
        for (int x = 0; x < grid.length; x++)
        {
            for (int y = 0; y < grid[0].length; y++)
            {
                if((int) (Math.random() * 2) == 0)
                {
                    grid[x][y] = true;
                }
            }
        }
    }
}
