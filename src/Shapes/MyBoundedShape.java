package Shapes;

import java.awt.*;

public abstract class MyBoundedShape extends MyShape{
    private boolean filled;

    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color);
        this.filled = filled;
    }

    public int getX(){
        return this.getX1();
    }

    public int getY(){
        return this.getY1();
    }

    public int getWidth(){
        return this.getX2();
    }

    public int getHeight(){
        return this.getY2();
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean equals(Object obj) {
        if((this instanceof MyOval && obj instanceof MyOval) || (this instanceof MyRectangle && obj instanceof MyRectangle))
            return this.getWidth() == ((MyBoundedShape) obj).getWidth()
                && this.getHeight() == ((MyBoundedShape) obj).getHeight();//if they are the same type check whether they have same height and width
        else
            return false;
    }
}
