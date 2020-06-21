import java.io.Serializable;

public class Score implements Serializable {
    int score = 0;
    String playerName = "";

    public void setPlayerName(String player) {
        this.playerName = player;
    }
}
