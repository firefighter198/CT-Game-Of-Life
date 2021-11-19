package Data;

import Control.Vector2;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase
{
    private Connection connection;
    private Statement st;

    //connect to the database
    public void connect()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:h2:./lib/ConwayGameOfLife", "sa", "");
            st = connection.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //disconnect from the database
    public void disconnect()
    {
        try
        {
            st.close();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //execute simple sql statement
    public void sql(String statement)
    {
        try
        {
            st.executeUpdate(statement);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    //execute sql statement with resultset return
    public ResultSet sqlRS(String statement)
    {
        try
        {
            return st.executeQuery(statement);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return null;
    }

    //create all database tables
    public void createDataBase()
    {
        connect();
        sql("create table generationInfo(id int not null auto_increment, name varchar, width int, height int)");
        sql("create table generationData(id int, x int, y int)");
        disconnect();

    }

    //delete all tables
    public void deleteDataBase()
    {
        connect();
        sql("drop table generationInfo");
        sql("drop table generationData");
        disconnect();
    }

    //save the grid in the database with a name
    public void saveGrid(boolean[][] grid, String name) throws SQLException
    {
        connect();

        sql("insert into generationInfo(name, width, height) values ('"+name+"', "+grid.length+", "+grid[0].length+")");
        ResultSet rs = sqlRS("select * from generationInfo order by id desc limit 1");
        rs.next();
        int id = rs.getInt("id");
        for(int x = 0; x < grid.length; x++)
        {
            for(int y = 0; y < grid[0].length; y++)
            {
                //for each "black cell" create an entry in generationData
                if(grid[x][y])
                {
                    sql("insert into generationData (id, x, y) values ("+id+", "+x+", "+y+")");
                }
            }
        }
        disconnect();
    }


    //load a grid by its name
    public boolean[][] loadGrid(String name) throws SQLException
    {
        boolean[][] grid;

        connect();

        try
        {
            //get the resultset
            ResultSet info = sqlRS("select id, width, height, from generationInfo where name = '" + name + "'");
            info.next();
            int width = info.getInt("width");
            int height = info.getInt("height");
            int id = info.getInt("id");
            info.close();

            //create the new grid
            grid = new boolean[width][height];

            for(int x = 0; x < width; x++)
            {
                for(int y = 0; y < height; y++)
                {
                    //if there is an entry for x and y set true, else false
                    ResultSet value = sqlRS("select * from generationData where id = " + id + " and x = " + x + " and y = " + y);
                    if(value.next())
                    {
                        grid[x][y] = true;
                    }
                    else
                    {
                        grid[x][y] = false;
                    }
                    value.close();
                }
            }

            disconnect();
        }
        catch(Exception e)
        {
            grid = new boolean[50][50];
            for(int x = 0; x < 50; x++)
            {
                for(int y = 0; y < 50; y++)
                {
                    grid[x][y] = false;
                }
            }
        }

        return grid;
    }

    //get all possible options from the database
    public List<String> getOptions() throws SQLException
    {
        connect();

        List<String> out = new ArrayList<>();

        ResultSet res = sqlRS("select name from generationInfo");


        while(res.next())
        {
            out.add(res.getString(1));
        }

        disconnect();

        return out;
    }
}
