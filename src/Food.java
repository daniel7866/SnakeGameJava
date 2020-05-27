import Shapes.MyRectangle;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Food extends MyRectangle {
    private static final int WIDHT = 10,HEIGHT = 10;
    public Food(int width, int height){
        super(ThreadLocalRandom.current().nextInt(0, width),ThreadLocalRandom.current().nextInt(0, height),WIDHT,HEIGHT, Color.RED,true);
    }
}
