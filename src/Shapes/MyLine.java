package Shapes;

import java.awt.*;

public class MyLine extends MyShape {
    public MyLine(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    public void paint(Graphics g) {
        g.setColor(getColor());
        g.drawLine(getX1(),getY1(),getX2(),getY2());
    }

    private double length(){
        return Math.sqrt(Math.pow(getX1()-getX2(),2)+Math.pow(getY1()-getY2(),2));
    }
}
