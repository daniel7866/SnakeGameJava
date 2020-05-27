package Shapes;

import java.awt.*;

public class MyOval extends MyBoundedShape {
    public MyOval(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        if(isFilled())
            g.fillOval(getX(),getY(),getWidth(),getHeight());
        else
            g.drawOval(getX(),getY(),getWidth(),getHeight());
    }
}
