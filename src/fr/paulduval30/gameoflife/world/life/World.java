package fr.paulduval30.gameoflife.world.life;

import java.util.ArrayList;

public class World
{
    private Cell[][] map;
    private int nbLigne;
    private int nbColonne;
    private boolean stable;

    public World(int nbLigne, int nbColonne)
    {
        this.map = new Cell[nbLigne][nbColonne];
        for(int i = 0; i < nbLigne; i++)
        {
            for(int j = 0; j < nbColonne; j++)
            {
                int alive = (int)(Math.random() * 100);
                map[i][j] = new Cell(alive < 20, i, j);
            }
        }

        map[1][3].setAlive(true);
        map[1][4].setAlive(true);
        map[1][5].setAlive(true);
        map[2][5].setAlive(true);
        map[3][5].setAlive(true);
        map[2][2].setAlive(true);
        map[3][1].setAlive(true);
        map[4][1].setAlive(true);
        map[5][1].setAlive(true);
        map[5][2].setAlive(true);
        map[5][3].setAlive(true);
        map[4][4].setAlive(true);

        map[31][33].setAlive(true);
        map[31][34].setAlive(true);
        map[31][35].setAlive(true);
        map[32][35].setAlive(true);
        map[33][35].setAlive(true);
        map[32][32].setAlive(true);
        map[33][31].setAlive(true);
        map[34][31].setAlive(true);
        map[35][31].setAlive(true);
        map[35][32].setAlive(true);
        map[35][33].setAlive(true);
        map[34][34].setAlive(true);

        stable = false;
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;
    }

    public void setState(int ligne, int colonne, boolean alive)
    {
        map[ligne][colonne].setAlive(alive);
    }

    public ArrayList<Cell> getVoisine(Cell c)
    {
        ArrayList<Cell> cellRet = new ArrayList<>();
        int i = c.getLigne();
        int j = c.getColonne();
        if(j + 1 < nbColonne)
            cellRet.add(map[i][j + 1]);
        if(j - 1 >= 0)
            cellRet.add(map[i][j - 1]);

        if(i - 1 >= 0)
        {
            cellRet.add(map[i - 1][j]);
            if(j + 1 < nbColonne)
                cellRet.add(map[i - 1][j + 1]);
            if(j - 1 >= 0)
                cellRet.add(map[i - 1][j - 1]);
        }
        if(i + 1 < nbLigne)
        {
            cellRet.add(map[i + 1][j]);
            if (j + 1 < nbColonne)
                cellRet.add(map[i + 1][j + 1]);
            if (j - 1 >= 0)
                cellRet.add(map[i + 1][j - 1]);
        }
        return cellRet;
    }

    public Cell[][] getMap()
    {
        return map;
    }

    public void setMap(Cell[][] map)
    {
        this.map = map;
    }

    public int getNbLigne()
    {
        return nbLigne;
    }

    public void setNbLigne(int nbLigne)
    {
        this.nbLigne = nbLigne;
    }

    public int getNbColonne()
    {
        return nbColonne;
    }

    public void setNbColonne(int nbColonne)
    {
        this.nbColonne = nbColonne;
    }

    public void testStable(boolean nextState, boolean alive)
    {
        this.stable = stable && (nextState == alive);
    }

    public boolean isStable()
    {
        return stable;
    }
}
