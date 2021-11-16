package Gui;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel
{
    private Graphics2D g2d;
    private boolean[][] grid;

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D)g;

        float div = Math.max(grid.length, grid[0].length);
        div = 800f / div;

        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, 0, (int)(div * grid.length), (int)(div * grid[0].length));

        g2d.setColor(Color.black);

        for(int x = 0; x < grid.length; x++)
        {
            for(int y = 0; y < grid[0].length; y++)
            {
                if(grid[x][y])
                {
                    g2d.fillRect((int)(x * div), (int)(y * div), (int)div +1, (int)div +1);
                }
            }
        }

        repaint();
    }

    public void drawGeneration(boolean[][] grid)
    {
        this.grid = grid;
    }
}
