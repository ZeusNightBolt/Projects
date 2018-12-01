import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class RGBLinesCanvas extends JComponent
{
    protected void paintComponent(Graphics g)
    {
        // Customize after superclass paintComponent constructor
        super.paintComponent(g);

        // Get the current height and width of the window
        JFrame presentWindow = (JFrame) SwingUtilities.getRoot(this);
        int height = presentWindow.getHeight();
        int width = presentWindow.getWidth();

        // Four Lines with RED color. The four boundaries, meeting at corners.
        g.setColor(Color.RED);
        g.drawLine(0, 0, 0, width);
        g.drawLine(0, height, width, height);
        g.drawLine(width, height, width, 0);
        g.drawLine(width, 0, 0, 0);
        
        // Two Lines with GREEN color. The two diagonals, meeting at center.
        g.setColor(Color.GREEN);
        g.drawLine(0, 0, width, height);
        g.drawLine(0, height, width, 0);

        // Two Lines with BLUE color. The two lines bisecting two opposite sides.
        g.setColor(Color.BLUE);
        g.drawLine(0, (height/2), width, (height/2));
        g.drawLine((width/2), 0, (width/2), height);
    }
}

