package fr.paulduval30.gameoflife.world.graphics;

import fr.paulduval30.gameoflife.world.life.Cell;
import fr.paulduval30.gameoflife.world.life.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WorldGraphics extends JComponent implements Runnable
{
    private Game game;

    public WorldGraphics(int width, int height, Game game)
    {
        this.setPreferredSize(new Dimension(width / 3, height));
        this.setSize(width / 3, height);
        this.game = game;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        new Thread(this).start();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int cellDim = this.getWidth() <= this.getHeight() ?
                this.getWidth() / game.getWorld().getNbColonne() : this.getHeight() / game.getWorld().getNbLigne();
        System.out.println(this.getWidth() + " " + this.getHeight() + " " + cellDim);
        g.drawString(game.getDay() + "", 0,0);
        for(int i = 0; i < game.getWorld().getNbLigne(); i++)
        {
            for(int j = 0; j < game.getWorld().getNbColonne(); j++)
            {
                Cell c = game.getCell(i, j);
                super.paintComponent(g);
                if(c.isAlive() && game.nextState(c))
                    g.setColor(Color.blue);
                else if(c.isAlive() && !game.nextState(c))
                    g.setColor(Color.red);
                else if(!c.isAlive() && game.nextState(c))
                    g.setColor(Color.green);
                else
                {
                    g.setColor(Color.white);
                }
                g.fillRect(c.getColonne() * cellDim, c.getLigne() * cellDim, cellDim, cellDim);
            }
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            game.update();
            repaint();
        }
    }
}
