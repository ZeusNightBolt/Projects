import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class PaintCanvasObjects
{
    public static void main(String[] args)
    {
        JFrame aWindow1 = new JFrame("Question 1");
        aWindow1.setSize(320, 320);
        aWindow1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        RGBLinesCanvas CanvasObj1 = new RGBLinesCanvas();
        aWindow1.add(CanvasObj1);
        aWindow1.setVisible(true);

        JFrame aWindow2 = new JFrame("Question 2");
        aWindow2.setSize(320, 320);
        aWindow2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        OneCornerLinesCanvas CanvasObj2 = new OneCornerLinesCanvas();
        aWindow2.add(CanvasObj2);
        aWindow2.setVisible(true);

        JFrame aWindow3 = new JFrame("Question 3");
        aWindow3.setSize(320, 320);
        aWindow3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AllCornerLinesCanvas CanvasObj3 = new AllCornerLinesCanvas();
        aWindow3.add(CanvasObj3);
        aWindow3.setVisible(true);

        JFrame aWindow4 = new JFrame("Question 4");
        aWindow4.setSize(320, 320);
        aWindow4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        OneCornerLineCurveCanvas CanvasObj4 = new OneCornerLineCurveCanvas();
        aWindow4.add(CanvasObj4);
        aWindow4.setVisible(true);
        
        JFrame aWindow5 = new JFrame("Question 5");
        aWindow5.setSize(320, 320);
        aWindow5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AllCornerLineCurveCanvas CanvasObj5 = new AllCornerLineCurveCanvas();
        aWindow5.add(CanvasObj5);
        aWindow5.setVisible(true);
        
        JFrame aWindow6 = new JFrame("Question 6");
        aWindow6.setSize(320, 320);
        aWindow6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ConcentricCirclesCanvas CanvasObj6 = new ConcentricCirclesCanvas();
        aWindow6.add(CanvasObj6);
        aWindow6.setVisible(true);
    }
}
