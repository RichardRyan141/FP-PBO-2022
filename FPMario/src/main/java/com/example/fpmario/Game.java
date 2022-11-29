package com.example.fpmario;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 200;
    public static final int HEIGHT = WIDTH*8/10;
    public static final int SCALE = 4;
    public static final String TITLE = "Mario";
    private Thread thread;
    private boolean running = true;
    public static Handler handler;

    private synchronized void start()
    {
        if (running) return;
        running = true;
        thread = new Thread(this,"Thread");
        thread.start();
    }
    private synchronized void stop()
    {
        if (!running) return;
        running = false;
        try
        {
            thread.join();
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }
    public void run()
    {
        initialize();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                ticks++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println(frames + " frames per second " + ticks + " updates per second");
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        handler.render(g);
        g.dispose();
        bs.show();
    }
    public void tick()
    {
        handler.tick();
    }
    public Game()
    {
        Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public void initialize()
    {
        handler = new Handler();
        handler.addEntitiy(new Player(300,500,64,64,true,Id.player,handler));
        addKeyListener(new KeyInput());
    }

}
