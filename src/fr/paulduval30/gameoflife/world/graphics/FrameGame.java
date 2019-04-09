package fr.paulduval30.gameoflife.world.graphics;

import fr.paulduval30.gameoflife.world.life.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameGame extends JFrame implements Runnable, KeyListener
{
    private static final long MS_PER_SECOND = 16;
    private Game g;
    private  WorldGraphics worldGraphics3;
    private JLabel days;
    private Camera camera;
    public FrameGame()
    {
        this.camera = new Camera();
        this.setSize(1000, 1000);
        this.getContentPane().setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.g = new Game(500, 500, 0, 10);
        worldGraphics3 = new WorldGraphics(this.getWidth(), this.getHeight(), g, camera);
        this.addKeyListener(this);
        days = new JLabel(g.getDay() + "", JLabel.CENTER);
        this.add(worldGraphics3, BorderLayout.CENTER);
        this.add(days, BorderLayout.NORTH);
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
        while(true)
        {
            long start = 0;
            long fps;
            double delta = 0.0;

            start = System.currentTimeMillis();
            days.setText(g.getDay() + "" );
            days.repaint();
            repaint();

            delta =  System.currentTimeMillis() - start  == 0 ? 1 :
                    1 - (double)(System.currentTimeMillis() - start) / (double)MS_PER_SECOND;
            fps = MS_PER_SECOND - (System.currentTimeMillis() - start);
            try
            {
                Thread.sleep(fps);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 107)
        {
            this.camera.setZoom(this.camera.getZoom() + 1);
        }

        if(e.getKeyCode() == 109)
        {
            this.camera.setZoom(this.camera.getZoom() - 1);
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            this.camera.setOffsetX(this.camera.getOffsetX() + 10);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            this.camera.setOffsetX(this.camera.getOffsetX() - 10);
        if(e.getKeyCode() == KeyEvent.VK_UP)
            this.camera.setOffSetY(this.camera.getOffSetY() + 10);
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            this.camera.setOffSetY(this.camera.getOffSetY() - 10);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
