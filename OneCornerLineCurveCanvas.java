import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import static java.lang.Math.sqrt;

public class OneCornerLineCurveCanvas extends JComponent
{
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

        // Set the number of lines to be drawn. Calculate the step value
        // to be incremented / decremented in the two dimensions to provide
        // the required effect.
        int numberOfLines = 35;
        double wStep = (width / numberOfLines);
        double hStep = (height / numberOfLines);

        // Make the bottom left part of the Ellipse Arc 
        for(int i = 0; i < numberOfLines; i++)
        {
            // Vertical Lines
            double drag = (6 * i);                      // To give a good effect of step difference
            double wStart = wStep * i;
            double wEnd = wStep * (i+1);
            double wShifted = wStart - width;
            double temp = (wShifted*wShifted) / (width*width);
            double hStart = height * sqrt(1 - temp);   // Derived from the equation of Ellipse y=b*sqrt(1-(x^2/a^2))
            double hEnd = height;
            Shape l = new Line2D.Double(wStart, hStart, wEnd + drag, hEnd);
            g2.draw(l);
        }
        for(int i = 0; i < numberOfLines; i++)
        {
            // Horizontal Lines
            double drag = (6 * i);
            double hStart = height - (hStep * i);
            double hEnd = height - (hStep * (i+1));
            double hShifted = -hStart;
            double temp = (hShifted*hShifted) / (height*height);
            double wStart = width - (width * sqrt(1 - temp));
            double wEnd = 0;
            Shape l = new Line2D.Double(wStart, hStart, wEnd, hEnd - drag);
            g2.draw(l);
        }
    }
}

