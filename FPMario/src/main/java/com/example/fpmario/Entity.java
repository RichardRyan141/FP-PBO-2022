package com.example.fpmario;

import java.awt.Graphics;

public abstract class Entity {
    public int x;
    public int y;
    public int width;
    public int height;
    private boolean solid;
    public int veloX;
    public int veloY;
    public Id id;
    public Handler handler;

    public Entity(int x, int y, int width, int height, boolean solid, Id id, Handler handler)
    {
        this.setX(x);
        this.setY(y);
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.handler = handler;
    }

    public abstract void render(Graphics g);
    public abstract void tick();

    public void death()
    {
        handler.removeEntity(this);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setVeloX(int veloX) {
        this.veloX = veloX;
    }

    public void setVeloY(int veloY) {
        this.veloY = veloY;
    }

    public Id getID() {
        return id;
    }
}
