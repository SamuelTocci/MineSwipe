package bruh;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Circle extends Form
{
    private int diameter;
    
    /**
     * Create a new circle at default position with default color.
     */
    public Circle() {
        super(230,90,"Blue");
        diameter = 68;
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newDiameter)
    {
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Draw the circle with current specifications on screen.
     */
    protected void draw()
    {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(), new Ellipse2D.Double(getxPosition(), getyPosition(),diameter, diameter));
            canvas.wait(10);
        }
    }

}
