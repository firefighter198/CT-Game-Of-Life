package Game;

import Control.Control;
import Data.DataBase;
import Gui.MenuWindow;

import java.util.Timer;
import java.util.TimerTask;

public class ConwayGameOfLife
{
    public static void main(String[] args)
    {
        Control control = new Control();
        control.openMenu();

        Timer t = new Timer();
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {
                control.update();
            }
        };
        t.scheduleAtFixedRate(tt, 1, 150);
    }
}
