package fr.paulduval30.gameoflife.world.life;

public class Cell
{
    private boolean alive;
    private boolean nextState;
    private int ligne;
    private int colonne;
    private int age;
    private boolean miracle;

    public Cell(boolean alive, int ligne, int colonne)
    {
        this.alive = alive;
        this.ligne = ligne;
        this.colonne = colonne;
        this.age = 0;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public int getLigne()
    {
        return ligne;
    }

    public int getColonne()
    {
        return colonne;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public void setLigne(int ligne)
    {
        this.ligne = ligne;
    }

    public void setColonne(int colonne)
    {
        this.colonne = colonne;
    }

    public void nextGen(boolean nextState)
    {
        if(nextState)
            age ++;
        this.nextState = nextState;
    }

    public void evolve()
    {
        boolean miracle = (int)(Math.random() * 1000) == 1;
        if(miracle)
            nextState = !nextState;
        this.alive = nextState;
    }

    public boolean getMiracle()
    {
        return miracle;
    }
}
