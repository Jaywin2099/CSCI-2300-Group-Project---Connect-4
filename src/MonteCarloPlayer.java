import java.util.Random;

public class MonteCarloPlayer implements Observer {

    private static final int MAX_SIMULATIONS = 1000;
    private Random random;
    private Game connect4;

    public MonteCarloPlayer(Game g) {
        this.connect4 = g;
        this.random = new Random();
    }

    public int[][] update (int col) {
        int move = findBestMove();

        return connect4.update(move);
    }

    public int findBestMove() {
        int[] legalMoves = {0, 1, 2, 3, 4, 5, 6};
        int bestMove = legalMoves[0];
        int bestScore = Integer.MIN_VALUE;

        for (int move : legalMoves) {
            int score = simulateMove(move);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        System.out.println(bestMove);

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
	            int randomMove = random.nextInt(6);
	            copy.move(randomMove);
	
	            if (copy.isWin()) {
	                if (copy.getCurrentTurn() % 2 == 0) {
	                    score = 1;
	                } else {
	                    score = -1;
	                }
	            }
	        }
	
	        // adds the final game state score to total
	        totalScore += score;
	    }
	
	    return totalScore;
    }
}