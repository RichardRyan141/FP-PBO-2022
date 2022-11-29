package com.example.fpmario;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    public LinkedList<Entity> entityList = new LinkedList<Entity>();
    public LinkedList<Tile> tileList = new LinkedList<Tile>();

    public void render(Graphics g)
    {
        for (Entity en : entityList)
        {
            en.render(g);
        }
        for (Tile ti : tileList)
        {
            ti.render(g);
        }
    }

    public void tick()
    {
        for (Entity en : entityList)
        {
            en.tick();
        }
        for (Tile ti : tileList)
        {
            ti.tick();
        }
    }

    public void addEntitiy(Entity en)
    {
        entityList.add(en);
    }

    public void removeEntity(Entity en)
    {
        entityList.remove(en);
    }

    public void addTile(Tile ti)
    {
        tileList.add(ti);
    }

    public void removeTile(Tile ti)
    {
        tileList.remove(ti);
    }
}
