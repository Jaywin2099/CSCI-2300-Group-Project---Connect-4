import java.util.ArrayList;
import java.util.Random;

public class MonteCarloPlayer implements Observer {
    private static final int MAX_SIMULATIONS = 10000;
    private Random random;
    private Game connect4;
    private int me;

    public MonteCarloPlayer(Game g) {
        this.connect4 = g;
        this.random = new Random();
    }

    public int[][] update(int col) {
        int move = findBestMove();
        return connect4.update(move);
    }

    public int findBestMove() {
        ArrayList<Integer> legalMoves = connect4.validMoves();
        int bestMove = legalMoves.get(0);
        int bestScore = Integer.MIN_VALUE;

        me = connect4.getPlayerCheck() + 1;

        for (int move : legalMoves) {
            int score = simulateMove(move);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int simulateMove(int move) {
        int totalScore = 0;

        for (int i = 0; i < MAX_SIMULATIONS; i++) {
            int score = 0;
            Game copy = connect4.copy(); // Make a copy of the current game state

            copy.move(move); // Simulate making the move

            // Simulate random moves until the game ends
            while (!copy.isDraw()) {
                // checks for win
                if (copy.isWin()) {
                    if (copy.getLastPlayerCheck() == me) {
                        score = 1;
                    } else {
                        score = -1;
                    }
                    break;
                }

                ArrayList<Integer> moves = copy.validMoves();

                int randomMove = random.nextInt(moves.size());

                // does that move
                copy.move(moves.get(randomMove));
            }

            // adds the final game state score to total
            totalScore += score;
        }

        return totalScore;
    }
}