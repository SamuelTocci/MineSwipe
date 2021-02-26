package bruh;
import java.awt.*;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Square extends Form
{
    private int size;

    /**
     * Create a new square at default position with default color.
     */
    public Square(){
        super(230,90,"Blue");
        size = 60;
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newSize)
    {
        erase();
        size = newSize;
        draw();
    }

    protected void draw()
    {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(), new Rectangle(getxPosition(), getyPosition(), size, size));
            canvas.wait(10);
        }
    }

}
