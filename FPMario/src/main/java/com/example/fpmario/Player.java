package com.example.fpmario;

import java.awt.*;

public class Player extends Entity{
    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler)
    {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(x,y,width,height);
    }

    public void tick()
    {
        x = x+veloX;
        y = y+veloY;
        if (x < 0)
        {
            x = 0;
        }
        if (y < 0)
        {
            y = 0;
        }
        if (x+width > 800)
        {
            x = 800-width;
        }
        if (y+height > 640)
        {
            y = 640-height;
        }
    }
}
