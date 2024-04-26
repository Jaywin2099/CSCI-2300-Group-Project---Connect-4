import java.util.Random;

public class RandomPlayer implements Observer {
    private Game connect4;

<<<<<<< HEAD:src/ComputerPlayer.java
    public ComputerPlayer (Game game) {
=======
    public RandomPlayer (Game game) {
>>>>>>> 51b2c82d2d81117b1aa9b82c78d16dca5dea97f6:src/RandomPlayer.java
        this.connect4 = game;
    }

    public void update(int col) {
        connect4.move(getMove());
    }

    public int getMove() {
        Random r = new Random();

        return r.nextInt(6);
    }
}