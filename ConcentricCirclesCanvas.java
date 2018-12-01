import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import static java.lang.Math.sqrt;

public class ConcentricCirclesCanvas extends JComponent
{
    protected int[] RadiusList = new int[12];
    protected String[] colors = new String[3];

    // Constructor to initialize the Radii and the colors
    public ConcentricCirclesCanvas()
    {
        int initR = 10;
        for(int i = 0; i < 12; i++)
            RadiusList[i] = (initR * (i+1));
        colors[0] = "GREEN";
        colors[1] = "WHITE";
        colors[2] = "ORANGE";
    }

    // Helper function to get the color.
    protected Color getCircleColor(String c)
    {
        if(c == "GREEN")
            return Color.GREEN;
        if(c == "WHITE")
            return Color.BLUE;
        if(c == "ORANGE")
            return Color.ORANGE;
        return Color.BLUE;
    }

    protected void paintComponent(Graphics g)
    {
        // Customize after superclass paintComponent constructor
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        // Get the current height and width of the window
        JFrame presentWindow = (JFrame) SwingUtilities.getRoot(this);
        int height = presentWindow.getHeight();
        int width = presentWindow.getWidth();

        int numberOfCircles = 12;

        // Draw the Circles using Ellipse2D as circle is a special
        // case on an Ellipse.
        for(int i = 0; i < numberOfCircles; i++)
        {
            double radius = RadiusList[i];
            // Top left (x, y) of the rectangle (here, square) containing the Ellipse (here, circle).
            double x = (width/2) - radius;
            double y = (height/2) - radius;
            g2.setColor(getCircleColor(colors[(i%3)]));
            Shape l = new Ellipse2D.Double(x, y, radius*2, radius*2);
            g2.draw(l);
        }
    }
}

