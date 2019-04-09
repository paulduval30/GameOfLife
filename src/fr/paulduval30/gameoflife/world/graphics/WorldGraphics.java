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
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
        this.game = game;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        int cellDim = (int)Math.ceil(this.getWidth() >= this.getHeight() ?
                (double)this.getWidth() / (double)game.getWorld().getNbColonne() :
                (double)this.getHeight() / (double)game.getWorld().getNbLigne())
                + camera.getZoom();
        System.out.println(cellDim + " " + this.getWidth() + " " + this.getHeight());
        for(int i = 0; i < game.getWorld().getNbLigne(); i++)
        {
            for(int j = 0; j < game.getWorld().getNbColonne(); j++)
            {
                Cell c = game.getCell(i, j);
                if(!c.getChange())
                    continue;
                //Cube cube = new Cube(c.getColonne() * cellDim + camera.getOffsetX(), c.getLigne() * cellDim + camera.getOffSetY(), cellDim, -cellDim);

                if(c.isAlive() && game.nextState(c))
                    g.setColor(Color.blue);
                // else if(c.isAlive() && !game.nextState(c))
                //     g.setColor(Color.RED);
                // else if(!c.isAlive() && game.nextState(c))
                //     g.setColor(Color.green);
                else
                {
                        g.setColor(Color.BLACK);
                }
                g.fillRect(c.getColonne() * cellDim + camera.getOffsetX(),
                        c.getLigne() * cellDim + camera.getOffSetY(), cellDim, cellDim);
                //cube.drawCube(g);
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
                Thread.sleep(10);
            }
            catch(Exception e)
            {

            }
            game.update();
        }
    }
}
