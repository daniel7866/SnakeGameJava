import javax.swing.*;

public class ScorePanel extends JPanel{
    private JLabel lblScore;
    Score score = new Score();
    public ScorePanel(){
        this.score.score = 0;
        lblScore = new JLabel("Score: " + score.score);
        this.add(lblScore);
    }

    public int getScore() {
        return score.score;
    }

    public void setScore(int score) {
        this.score.score = score;
        lblScore.setText("Score: " + score);
    }
}
