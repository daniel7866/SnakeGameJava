package Shapes;

import java.awt.*;

public class MyRectangle extends MyBoundedShape {
    public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    public void paint(Graphics g) {
        g.setColor(getColor());
        if(isFilled())
            g.fillRect(getX(),getY(),getWidth(),getHeight());
        else
            g.drawRect(getX(),getY(),getWidth(),getHeight());
    }
}
