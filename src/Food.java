import Shapes.MyRectangle;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Food extends MyRectangle {
    private static Random random = new Random();
    private static final int SOUTH_GAP = 40;
    private static final int WIDHT = 10,HEIGHT = 10;
    private static final int GAP = WIDHT;
    public Food(int width, int height){
        super(ThreadLocalRandom.current().nextInt(0, width),ThreadLocalRandom.current().nextInt(0, height),WIDHT,HEIGHT, Color.RED,true);
        //super(ThreadLocalRandom.current().nextInt(GAP, width - GAP),ThreadLocalRandom.current().nextInt(GAP, height - 2*SOUTH_GAP),WIDHT,HEIGHT, Color.RED,true);
        //System.out.println(getX1());
        //System.out.println(getY1());
    }
}
