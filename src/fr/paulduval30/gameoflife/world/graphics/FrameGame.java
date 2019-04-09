package fr.paulduval30.gameoflife.world.graphics;

import fr.paulduval30.gameoflife.world.life.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class FrameGame extends JFrame implements Runnable, KeyListener
{
    private static final long MS_PER_SECOND = 16;
    private Game g;
    private  WorldGraphics worldGraphics3;
    private JLabel days;
    private Camera camera;
    private JTabbedPane panel;
    public FrameGame()
    {
        this.camera = new Camera();
        this.setSize(1000, 1000);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.g = new Game(1000, 1000, 0, 10);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,1));
        this.panel = new JTabbedPane();
        panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        worldGraphics3 = new WorldGraphics(this.getWidth(), this.getHeight(), g, camera);
        panel.add(worldGraphics3, "World");
        days = new JLabel("Day : " + g.getDay() + " Nombre de cellule en vie : " + g.getNbVivante(), JLabel.CENTER);
        panel.add(days, "Stats");
        p.add(panel);
        this.setContentPane(p);
        panel.addKeyListener(this);
        this.add(panel, BorderLayout.CENTER);
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
        DecimalFormat formatter = new DecimalFormat("#0.00");
        double percent = 0.0;
        while(true)
        {
            // try
            // {
            //     Thread.sleep(200);
            // }
            // catch(Exception ignored){}
            this.g.update();
            percent = (double)g.getNbVivante() / (double)(g.getWorld().getNbLigne() * g.getWorld().getNbColonne()) * 100;
            days.setText("Day : " + g.getDay() + " Nombre de cellule en vie : " + formatter.format(percent)) ;
            repaint();
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
