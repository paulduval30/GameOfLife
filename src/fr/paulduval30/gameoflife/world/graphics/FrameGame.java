package fr.paulduval30.gameoflife.world.graphics;

import fr.paulduval30.gameoflife.world.life.Game;

import javax.swing.*;
import java.awt.*;

public class FrameGame extends JFrame implements Runnable
{
    private Game g;
    private  WorldGraphics worldGraphics;
    private  WorldGraphics worldGraphics2;
    private  WorldGraphics worldGraphics3;
    public FrameGame()
    {
        this.setSize(1000, 1000);
        this.getContentPane().setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        worldGraphics = new WorldGraphics(this.getWidth(), this.getHeight(), this.g = new Game(100, 30, 0, 10));
        worldGraphics2 = new WorldGraphics(this.getWidth(), this.getHeight(), this.g = new Game(100, 30, 0, 10));
        worldGraphics3 = new WorldGraphics(this.getWidth(), this.getHeight(), this.g = new Game(100, 30, 0, 10));
        JPanel p = new JPanel(new GridLayout(1, 3));
        p.add(worldGraphics);
        p.add(worldGraphics2);
        p.add(worldGraphics3);
        this.add(p, BorderLayout.CENTER);
        this.setUndecorated(true);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new Thread(this).start();
    }

    public static void main(String[] argv)
    {
        new FrameGame();
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        repaint();
    }
}
