import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomPlayer implements Observer {
    private Game connect4;

    public RandomPlayer (Game game) {
        this.connect4 = game;
    }

    public int[][] update(int col) {
        return connect4.update(getMove());
    }

    public int getMove() {
        Random r = new Random();

        return r.nextInt(6);
    }
}