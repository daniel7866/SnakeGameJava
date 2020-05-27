import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private JButton btnStart,btnHighScores;
    private int width,height;
    public MenuPanel(int width,int height){
        this.width = width;
        this.height = height;
        btnStart = new JButton("Start");
        btnHighScores = new JButton("High Scores");
        btnStart.addActionListener(this);
        btnHighScores.addActionListener(this);
        this.add(btnStart);
        this.add(btnHighScores);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnStart){
            //start the game
            this.setVisible(false);
        }
        else if(e.getSource() == btnHighScores){
            //show highScores
        }
    }
}
