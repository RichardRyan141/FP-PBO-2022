package com.example.fpmario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.example.fpmario.Entity;

public class KeyInput implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : Game.handler.entityList) {
            switch (key) {
                case KeyEvent.VK_W:
                    en.setVeloY(-5);
                    break;
                case KeyEvent.VK_A:
                    en.setVeloX(-5);
                    break;
                case KeyEvent.VK_S:
                    en.setVeloY(5);
                    break;
                case KeyEvent.VK_D:
                    en.setVeloX(5);
                    break;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : Game.handler.entityList) {
            switch (key) {
                case KeyEvent.VK_W:
                    en.setVeloY(0);
                    break;
                case KeyEvent.VK_A:
                    en.setVeloX(0);
                    break;
                case KeyEvent.VK_S:
                    en.setVeloY(0);
                    break;
                case KeyEvent.VK_D:
                    en.setVeloX(0);
                    break;

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
