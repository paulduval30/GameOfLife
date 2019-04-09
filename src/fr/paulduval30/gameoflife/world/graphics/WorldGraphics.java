package fr.paulduval30.gameoflife.world.graphics;

import fr.paulduval30.gameoflife.world.life.Cell;
import fr.paulduval30.gameoflife.world.life.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WorldGraphics extends JComponent implements Runnable
{
    private Game game;
    private Camera camera;

    public WorldGraphics(int width, int height, Game game, Camera camera)
    {
        this.camera = camera;
        this.setPreferredSize(new Dimension(width - 100, height - 100));
        this.setSize(width - 100, height - 100);
        this.game = game;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        new Thread(this).start();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int cellDim =(int) Math.round(this.getWidth() <= this.getHeight() ?
                (double)this.getWidth() / (double)game.getWorld().getNbColonne() :
                (double)this.getHeight() / (double)game.getWorld().getNbLigne())
                + camera.getZoom();
        for(int i = 0; i < game.getWorld().getNbLigne(); i++)
        {
            for(int j = 0; j < game.getWorld().getNbColonne(); j++)
            {
                Cell c = game.getCell(i, j);
                super.paintComponent(g);
                if(c.isAlive() && game.nextState(c))
                    g.setColor(Color.blue);
                else if(c.isAlive() && !game.nextState(c))
                    g.setColor(Color.BLACK);
                else if(!c.isAlive() && game.nextState(c))
                    g.setColor(Color.green);
                else
                {
                        g.setColor(Color.white);
                }
                g.drawRect(c.getColonne() * cellDim + camera.getOffsetX(),
                        c.getLigne() * cellDim + camera.getOffSetY(), cellDim, cellDim);
            }
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            game.update();
        }
    }
}
