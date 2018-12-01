import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AllCornerLinesCanvas extends JComponent
{
    protected void paintComponent(Graphics g)
    {
        // Customize after superclass paintComponent constructor
        super.paintComponent(g);

        // Get the current height and width of the window
        JFrame presentWindow = (JFrame) SwingUtilities.getRoot(this);
        int height = presentWindow.getHeight();
        int width = presentWindow.getWidth();

        // Set the number of lines to be drawn. Calculate the step value
        // to be incremented / decremented in the two dimensions to provide
        // the Fan Out effect.
        int numberOfLines = 35;
        int hStep = (height / numberOfLines);
        int wStep = (width / numberOfLines);

        // Symmetry can be seen in the endpoint function for top left and
        // bottom right, as well as top right and bottom left corner.

        // Fan Out from top left corner
        g.setColor(Color.BLACK);
        for(int i = 0; i < numberOfLines; i++)
        {
            int hEnd = hStep * i;
            int wEnd = wStep * i;
            g.drawLine(0, 0, wEnd, height - hEnd);
        }

        // Fan Out from top right corner
        for(int i = 0; i < numberOfLines; i++)
        {
            int hEnd = hStep * i;
            int wEnd = wStep * i;
            g.drawLine(width, 0, width - wEnd, height - hEnd);
        }

        // Fan Out from bottom left corner
        for(int i = 0; i < numberOfLines; i++)
        {
            int hEnd = hStep * i;
            int wEnd = wStep * i;
            g.drawLine(0, height, width - wEnd, height - hEnd);
        }

        // Fan Out from bottom right corner
        for(int i = 0; i < numberOfLines; i++)
        {
            int hEnd = hStep * i;
            int wEnd = wStep * i;
            g.drawLine(width, height, wEnd, height - hEnd);
        }
    }
}

