package bruh;

import java.awt.*;

/**
 * A person that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Person extends Form
{
    private int height;
    private int width;

    /**
     * Create a new person at default position with default color.
     */
    public Person()
    {
        super(230,90,"Blue");
        this.height = 60;
        this.width = 30;
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newHeight, int newWidth)
    {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /**
     * Draw the person with current specifications on screen.
     */
    protected void draw()
    {
        int bh = (int)(height * 0.7);  // body height
        int hh = (height - bh) / 2;  // half head height
        int hw = width / 2;  // half width
        int x = getxPosition();
        int y = getyPosition() ;
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { x-3, x-hw, x-hw, x-(int)(hw*0.2)-1, x-(int)(hw*0.2)-1, x-hw, 
                              x-hw+(int)(hw*0.4)+1, x, x+hw-(int)(hw*0.4)-1, x+hw, x+(int)(hw*0.2)+1, 
                              x+(int)(hw*0.2)+1, x+hw, x+hw, x+3, x+(int)(hw*0.6), 
                              x+(int)(hw*0.6), x+3, x-3, x-(int)(hw*0.6), x-(int)(hw*0.6) };
            int[] ypoints = { y, y+(int)(bh*0.2), y+(int)(bh*0.4), y+(int)(bh*0.2), 
                              y+(int)(bh*0.5), y+bh, y+bh, y+(int)(bh*0.65), y+bh, y+bh, 
                              y+(int)(bh*0.5), y+(int)(bh*0.2), y+(int)(bh*0.4), y+(int)(bh*0.2), 
                              y, y-hh+3, y-hh-3, y-hh-hh, y-hh-hh, y-hh-3, y-hh+3 };
            canvas.draw(this, getColor(), new Polygon(xpoints, ypoints, 21));
            canvas.wait(10);
        }
    }

}
