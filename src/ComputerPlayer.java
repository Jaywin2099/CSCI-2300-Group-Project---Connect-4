import java.util.Random;

public class ComputerPlayer implements Observer {
    private Game connect4;

    public void update(int col) {
        // when the gui gets a word, i check if its a one player game
        connect4.update(getMove());
    }

    public int getMove() {
        Random r = new Random();

        return r.nextInt(6);
    }
}
