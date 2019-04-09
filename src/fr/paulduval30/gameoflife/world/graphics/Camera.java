package fr.paulduval30.gameoflife.world.graphics;

public class Camera
{
    private int zoom;
    private int offsetX;
    private int offSetY;

    public Camera()
    {
    }

    public int getZoom()
    {
        return zoom;
    }

    public void setZoom(int zoom)
    {
        this.zoom = zoom;
        if(this.zoom < 0)
            this.zoom = 0;
    }

    public int getOffsetX()
    {
        return offsetX;
    }

    public void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }

    public int getOffSetY()
    {
        return offSetY;
    }

    public void setOffSetY(int offSetY)
    {
        this.offSetY = offSetY;
    }
}
