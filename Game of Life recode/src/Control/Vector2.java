package Control;

public class Vector2
{
    //x and y value of the vector
    public int x, y;

    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //convert the vector to a string
    @Override
    public String toString()
    {
        return x + " " + y;
    }

    //check if two vectors are identical
    public boolean equals(Vector2 other)
    {
        if(x == other.x && y == other.y)
        {
            return true;
        }

        return false;
    }

    //rotate the vector by the given angle in degree
    public void rotate(int deg)
    {
        double theta = Math.toRadians(deg);

        double cs = Math.cos(theta);
        double sn = Math.sin(theta);

        double _x = x * cs - y * sn;
        double _y = x * sn + y * cs;

        x = (int)Math.round(_x);
        y = (int)Math.round(_y);
    }

    //multiply two vectors
    public static Vector2 mul(Vector2 v, int scalar)
    {
        return new Vector2(v.x * scalar, v.y * scalar);
    }

    //add two vectors
    public static Vector2 add(Vector2 v1, Vector2 v2)
    {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    //get a vector pointing to 0, 0
    public static Vector2 zero()
    {
        return new Vector2(0, 0);
    }
}
