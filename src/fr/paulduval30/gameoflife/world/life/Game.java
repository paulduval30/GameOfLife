package fr.paulduval30.gameoflife.world.life;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private List<Cell> cells;
    private World world;
    private int day;
    private long dayLenght;
    private int nbVivante;
    private boolean updated;

    public Game(int ligne, int colonne, int day, long dayLenght)
    {
        this.cells = cells;
        this.world = new World(ligne, colonne);
        this.day = day;
        this.dayLenght = dayLenght;
    }

    public void update()
    {
        this.updated = false;
        this.nbVivante = 0;
        Cell[][] map = world.getMap();
        int ligne = map.length;
        int colonne = map[0].length;
        for(int i = 0; i < ligne; i++)
        {
            for(int j = 0; j <colonne; j++)
            {
                boolean nextState = nextState(map[i][j]);
                if(nextState)
                    nbVivante++;
                world.getMap()[i][j].nextGen(nextState);
            }
        }

        for(int i = 0; i <ligne; i++)
        {
            for(int j = 0; j < colonne; j++)
            {
                world.getMap()[i][j].evolve();
            }
        }
        day++;
        this.updated = true;
        System.out.println(day + " : " + nbVivante);
    }

    public boolean nextState(Cell c)
    {
        ArrayList<Cell> voisine = world.getVoisine(c);
        int nbVivante = 0;
        for(Cell cellule : voisine)
        {
            if(cellule.isAlive())
                nbVivante ++;
        }

        if(nbVivante == 3)
        {
            return true;
        }
        else if(nbVivante == 2)
        {
            return c.isAlive();
        }
        else
            return false;
    }

    private void addCell(Cell c)
    {
        this.cells.add(c);
    }

    private void removeCell(Cell c)
    {
        this.cells.remove(c);
    }

    public List<Cell> getCells()
    {
        return cells;
    }

    public void setCells(List<Cell> cells)
    {
        this.cells = cells;
    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public long getDayLenght()
    {
        return dayLenght;
    }

    public void setDayLenght(long dayLenght)
    {
        this.dayLenght = dayLenght;
    }

    public Cell getCell(int ligne, int colonne)
    {
        return world.getMap()[ligne][colonne];
    }

    public int getNbVivante()
    {
        return this.nbVivante;
    }

    public boolean isUpdated()
    {
        return this.updated;
    }
}
