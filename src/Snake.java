import Shapes.MyRectangle;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    public static final int UP = 1,DOWN = 2, LEFT = 3, RIGHT = 4;
    public static int speed = 10;
    private final int RADIUS = WIDTH;
    private static final int WIDTH = 10,HEIGHT = 10;
    private ArrayList<MyRectangle> body;

    public int direction;

    public Snake(int width,int height){
        body = new ArrayList<>();
        body.add(new MyRectangle(width/2,height/2,WIDTH,HEIGHT, new Color(34,139,34),true));
        direction = UP;
    }

    public boolean intersectsWith(MyRectangle obj){
        return (((this.getX() >= obj.getX() - RADIUS) && (this.getX() <= obj.getX() + RADIUS))
                && ((this.getY() >= obj.getY() - RADIUS) && (this.getY() <= obj.getY() + RADIUS)));
    }

    public int selfIntersects(){
        for (int i = 2; i < body.size();i++) {
            int headX = body.get(0).getX();
            int headY = body.get(0).getY();
            int iX = body.get(i).getX();
            int iY = body.get(i).getY();
            if(headX == iX && headY == iY)
                return i;
        }
        return -1;
    }

    public void cut(int i) {//when the snake eats itself, it will shrink from where it bit itself
        if(i<0)
            return;
        for (int j = body.size()-1; j >= i; j--) {
            body.remove(j);
        }
    }

    public int getX(){
        return body.get(0).getX();
    }

    public int getY(){
        return body.get(0).getY();
    }

    public void eat(Food apple){
        if(direction == UP) {
            if(body.size()%2 == 0)
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1(), body.get(body.size() - 1).getY1() + HEIGHT, WIDTH, HEIGHT, new Color(34,139,34), true));
            else
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1(), body.get(body.size() - 1).getY1() + HEIGHT, WIDTH, HEIGHT, new Color(34+30,139+30,34+30), true));
        }
        else if(direction == DOWN) {
            if(body.size()%2 == 0)
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1(), body.get(body.size() - 1).getY1() - HEIGHT, WIDTH, HEIGHT, new Color(34,139,34), true));
            else
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1(), body.get(body.size() - 1).getY1() - HEIGHT, WIDTH, HEIGHT, new Color(34+30,139+30,34+30), true));
        }
        else if(direction == RIGHT) {
            if(body.size()%2 == 0)
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1() - WIDTH, body.get(body.size() - 1).getY1(), WIDTH, HEIGHT, new Color(34,139,34), true));
            else
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1() - WIDTH, body.get(body.size() - 1).getY1(), WIDTH, HEIGHT, new Color(34+30,139+30,34+30), true));
        }
        else if(direction == LEFT) {
            if(body.size()%2 == 0)
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1() + WIDTH, body.get(body.size() - 1).getY1(), WIDTH, HEIGHT, new Color(34,139,34), true));
            else
                this.body.add(new MyRectangle(body.get(body.size() - 1).getX1() + WIDTH, body.get(body.size() - 1).getY1(), WIDTH, HEIGHT, new Color(34+30,139+30,34+30), true));
        }
    }

    public void move(){
        if(direction == UP){
            createMovement();
            body.get(0).setY1(body.get(0).getY() - speed);
        }
        else if(direction == DOWN){
            createMovement();
            body.get(0).setY1(body.get(0).getY() + speed);
        }
        else if(direction == RIGHT){
            createMovement();
            body.get(0).setX1(body.get(0).getX() + speed);
        }
        else if(direction == LEFT){
            createMovement();
            body.get(0).setX1(body.get(0).getX() - speed);
        }
    }

    private void createMovement(){
        for (int i = body.size()-1; i > 0; i--) {
            body.get(i).setX1(body.get(i-1).getX());
            body.get(i).setY1(body.get(i-1).getY());
        }
    }

    public void paint(Graphics g){
        for (MyRectangle rect:body) {
            rect.paint(g);
        }
    }
}
