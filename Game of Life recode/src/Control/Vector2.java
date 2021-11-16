package Control;

public class Vector2
{
    public int x, y;

    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return x + " " + y;
    }

    public boolean equals(Vector2 other)
    {
        if(x == other.x && y == other.y)
        {
            return true;
        }

        return false;
    }

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

    public static Vector2 mul(Vector2 v, int scalar)
    {
        return new Vector2(v.x * scalar, v.y * scalar);
    }

    public static Vector2 add(Vector2 v1, Vector2 v2)
    {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2 zero()
    {
        return new Vector2(0, 0);
    }
}
