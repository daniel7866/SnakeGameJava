import javax.swing.*;

public class Tester {

    public static void main(String[] args) {
//        final int WIDTH = 500, HEIGHT = 500;
        final int WIDTH = 1000, HEIGHT = 800;
        JFrame frame = new JFrame();
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gamePanel = new GamePanel(WIDTH,HEIGHT);
        frame.add(gamePanel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
