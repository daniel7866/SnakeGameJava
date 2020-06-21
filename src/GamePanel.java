import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GamePanel extends JPanel implements ActionListener{
    private static final int SPEED = 10;
    private Snake snake;
    private Food food;
    private Timer timer;
    private boolean moving;
    private MenuPanel menuPanel;
    private ScorePanel scorePanel;

    private int width, height;
    public static int DELAY = 50;

    public GamePanel(int width, int height) {
        this.setLayout(new BorderLayout());
        this.addMouseListener(new ML());
        this.addKeyListener(new KL());
        this.width = width;
        this.height = height;
        moving = true;
        timer = new Timer(DELAY,this);
        menuPanel = new MenuPanel(width,height);
        this.add(menuPanel,BorderLayout.NORTH);
        scorePanel = new ScorePanel();
        this.add(scorePanel,BorderLayout.SOUTH);
        menuPanel.setVisible(true);
        snake = new Snake(width, height);
        food = new Food(width/2, height/2);
        timer.start();
        this.requestFocusInWindow();
        this.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (menuPanel.isVisible()) {//game is paused
            snake.speed = 0;
        }
        else
            snake.speed = SPEED;
        if(snake.intersectsWith(food)){
            snake.eat(food);
            food = new Food(this.getWidth(),this.getHeight() - scorePanel.getHeight());
            scorePanel.setScore(scorePanel.getScore() + 100);
        }

        if((snake.getX() < 0) || (snake.getX()> this.getWidth()) || ((snake.getY() < 0) || (snake.getY() > this.getHeight()))){
            gameOver();
        }

        if(scorePanel.getScore()%200 != 0){
            changeWallpaper(Color.black);
        }
        else
            changeWallpaper(Color.white);

        snake.cut(snake.selfIntersects());//if the snake bites itself, it will shrink
        repaint();
    }

    private void changeWallpaper(Color color){
        this.setBackground(color);
    }

    public static Object ReadObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void gameOver(){
        try {
            String fpath = "highscore";
            Score highscore = (Score) ReadObjectFromFile(fpath);
            if (highscore.score < scorePanel.score.score)
                saveScore(new File(fpath));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Could not find score file, scores will not be saved.");
        }
        JOptionPane.showMessageDialog(null,"Game Over!\nYour Score: " + scorePanel.getScore());
        menuPanel.setVisible(true);
        snake = new Snake(width,height);
        scorePanel.setScore(0);
    }

    private void saveScore(File f) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream
                (new FileOutputStream(f));
        scorePanel.score.setPlayerName(JOptionPane.showInputDialog(null, "What is your name?"));
        out.writeObject(scorePanel.score);
        out.close();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.requestFocusInWindow();
        this.requestFocus();
        snake.paint(g);
        food.paint(g);
        snake.move();
    }

    public void play(){
        timer.start();
    }

    public void pause(){
        timer.stop();
    }

    private class ML extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {

        }
    }

    private class KL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if(e.getKeyChar() == 'w' && snake.direction != Snake.DOWN){
                snake.direction = Snake.UP;
            }
            else if(e.getKeyChar() == 's' && snake.direction != Snake.UP){
                snake.direction = Snake.DOWN;
            }
            else if(e.getKeyChar() == 'd' && snake.direction != Snake.LEFT){
                snake.direction = Snake.RIGHT;
            }
            else if(e.getKeyChar() == 'a' && snake.direction != Snake.RIGHT){
                snake.direction = Snake.LEFT;
            }
            else if(e.getKeyChar() == 'p'){
                pause();
                if(JOptionPane.showConfirmDialog(null,"Game Paused","Game Paused",JOptionPane.DEFAULT_OPTION) == JOptionPane.OK_OPTION)
                    play();
            }
        }
    }
}