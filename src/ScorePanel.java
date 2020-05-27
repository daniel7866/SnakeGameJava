import javax.swing.*;

public class ScorePanel extends JPanel{
    private JLabel lblScore;
    private int score;
    public ScorePanel(){
        this.score = 0;
        lblScore = new JLabel("Score: " + score);
        this.add(lblScore);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        lblScore.setText("Score: " + score);
    }
}
