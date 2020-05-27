import jdk.net.SocketFlow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.Key;
import java.util.Scanner;

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
        if(menuPanel.isVisible()){
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

        snake.cut(snake.selfIntersects());
        repaint();
    }

    private void changeWallpaper(Color color){
        this.setBackground(color);
    }

    private void gameOver(){
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader("highscore.txt"));
            int score = 0;
            String line;
            while((line = bufferreader.readLine()) != null){
                score = Integer.parseInt(line);
            }
            if(scorePanel.getScore() > score) {
                FileWriter fw = new FileWriter("highscore.txt");
                fw.append(scorePanel.getScore() + "");
                fw.close();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Could not find score file, scores will not be saved.");
        }
        JOptionPane.showMessageDialog(null,"Game Over!\nYour Score: " + scorePanel.getScore());
        menuPanel.setVisible(true);
        snake = new Snake(width,height);
        scorePanel.setScore(0);
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