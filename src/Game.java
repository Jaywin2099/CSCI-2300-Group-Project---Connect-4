import java.util.ArrayList;

public class Game implements Observer {
	int turnsPlayed = 0;
	int[][] board = new int[6][7];
	int lastPiecePlacedRow;
	int lastPiecePlacedCol;

	public Game() {}
	
	public Game copy() {
		// creates new game
		Game c = new Game();
		int[][] b = c.getBoard(); // creates a shallow copy of c's board
		int count = 0;

		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[i].length; ++j) {
				// copies values from each to the corresponding square in the new board
				b[i][j] = this.board[i][j];

				if (b[i][j] != 0) {
					count++;
				}
			}
		}

		c.setTurnsPlayed(count);
		return c;
	}

	public void setTurnsPlayed(int turns) {
		this.turnsPlayed = turns;
	}

	public boolean isDraw() {
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[i].length; ++j) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean isWin() {
		boolean win = false;
		if (turnsPlayed > 7) {
			if (checkVertically() == true || checkHorizontally() == true || checkDiagonallyUp() || checkDiagonallyDown()) { // add diagonal win condition
				win = true;
			}
		} else {
			win = false;
		}
		return win;
	}

	public boolean checkVertically() {
		int player = getLastPlayerCheck(); // gets the player that just placed a piece

		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();

		if (currentRow < 3) return false;

		for (int i = 1; i <= 3; i++) {
			if (board[currentRow - i][currentCol] != player) {
				return false;
			}
		}
		return true;
	}

	public boolean checkHorizontally() {
		int player = getLastPlayerCheck();
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();

		boolean win = true;

		// checks the next three pieces
		if (currentCol <= 3) { // checks right
			for (int i = 1; i <= 3; i++) {
				if (board[currentRow][currentCol + i] != player) {
					win = false;
					break;
				}
			}
		}

		// reset win check for col 3 since it can connect 4 from left And right
		if (currentCol == 3) win = true;

		if (currentCol >= 3) { // checks left
			for (int i = 1; i <= 3; i++) {
				if (board[currentRow][currentCol - i] != player) {
					win = false;
					break;
				}
			}
		}
		return win;
	}

	public boolean checkDiagonallyUp() {
		int player = getLastPlayerCheck();
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();

		boolean win = true;
		// checks the next three pieces
		if (currentRow > 2) {
			win = false;
		}
		else {
			if (currentCol <= 3) { // checks up right
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow + i][currentCol + i] != player) {
						win = false;
						break;
					}
				}
			}
			// reset win check for col 3 since it can connect 4 from left And right
			if (currentCol == 3) win = true;
	
			if (currentCol >= 3) { // checks up left
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow + i][currentCol - i] != player) {
						win = false;
						break;
					}
				}
			}
		}
		return win;
	}

	public boolean checkDiagonallyDown() {
		int player = getLastPlayerCheck();
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();

		boolean win = true;
		// checks the next three pieces
		if (currentRow < 3) {
			win = false;
		}
		else {
			if (currentCol <= 3) { // checks down right
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow - i][currentCol + i] != player) {
						win = false;
						break;
					}
				}
			}
			// reset win check for col 3 since it can connect 4 from left And right
			if (currentCol == 3) win = true;
	
			if (currentCol >= 3) { // checks down left
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow - i][currentCol - i] != player) {
						win = false;
						break;
					}
				}
			}
		}
		return win;
	}
	
	public int getCurrentPieceRow() {
		return lastPiecePlacedRow;
	}

	public int getCurrentPieceCol() {
		return lastPiecePlacedCol;
	}

	public Boolean isFull (int col) {
		if (board[board.length - 1][col] != 0) return true;
		else return false;
	}

	public ArrayList<Integer> validMoves() {
		ArrayList<Integer> moves = new ArrayList<Integer>();

		for (int i = 0; i < board[0].length; i++) {
			if (board[board.length - 1][i] == 0) {
				moves.add(i);
			}
		}

		return moves;
	}

	public void move(int column) {
		lastPiecePlacedCol = column;
		if (getPlayerCheck() == 0) { // player 1 represented with 1
			for (int i = 0; i < 7; i++) {
				if (board[i][column] == 0) {
					board[i][column] = 1;
					lastPiecePlacedRow = i;
					break;
				}
			}
		} else {// player 2 represented with 2
			for (int i = 0; i < 7; i++) {
				if (board[i][column] == 0) {
					board[i][column] = 2;
					lastPiecePlacedRow = i;
					break;
				}
			}
		}
		turnsPlayed++;
	}

	public int getCurrentTurn() {
		return turnsPlayed;
	}

	public int[][] getBoard() {
		return this.board;
	}

	public void restart() {
		turnsPlayed = 0;
		this.board = new int[6][7];
	}

	public int getPlayerCheck() {
		// if odd turn, next turn is player 2, if even player 1
		return turnsPlayed % 2;
	}

	public int getLastPlayerCheck() {
		// since the turns played is incrememented after the move, you must subtract 1
		// from turnsplayed to get the previously placed player's number
		return (turnsPlayed - 1) % 2 + 1;
	}

	public int[][] update(int col) {
		int [][] n = new int[0][0];

		move(col);

		if (isWin() || isDraw()) {
			return n;
		}

		// returns a copy of the board
		return copy().getBoard();
	}
}