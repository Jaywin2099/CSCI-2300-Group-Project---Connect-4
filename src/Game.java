import java.util.ArrayList;

public class Game implements Observer {
	int turnsPlayed = 0;
	int COLS = 7;
	int ROWS = 6;
	int[][] board = new int[ROWS][COLS];
	int lastPiecePlacedRow;
	int lastPiecePlacedCol;
	private Boolean gameDone = false;

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
		for (int i = 0; i < board[0].length; ++i) {
			if (board[board.length - 1][i] == 0) {
				return false;
			}
		}

		return true;
	}

	public boolean isWin() {
		boolean win = false;
		if (turnsPlayed >= 7) {
			if (checkVertically() || checkHorizontally() || checkDiagonally()) {
				win = true;
			}
		} else {
			win = false; // Up() || checkDiagonallyDown()
		}

		return win;
	}

	public boolean checkVertically() {
		int player = getLastPlayerCheck(); // gets the player that just placed a piece
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();

		// bounds check
		if (currentRow < 3) return false;

		for (int i = 1; i <= 3; i++) {
			if (board[currentRow - i][currentCol] != player) {
				return false;
			}
		}

		return true;
	}

	public boolean checkHorizontally() {
		int player = getLastPlayerCheck(); // gets the player that just placed a piece
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		int inARow = 1;
		int maxInARow = 0;

		if (currentCol == 3) { //middle piece, check the whole row
			for (int i = 1; i <= 6; i++) {
				if (board[currentRow][i-1] == board[currentRow][i] && board[currentRow][i] == player) {
					inARow = inARow + 1;
					if(inARow > maxInARow) {
						maxInARow = inARow;
					}
					
				}
				else {
					inARow = 1;
				}
			}
		}
		else if (currentCol < 3) { // checks from start of the board
			for (int i = 1; i <= (currentCol + 4); i++) {
				if (board[currentRow][i-1] == board[currentRow][i] && board[currentRow][i] == player) {
					inARow = inARow + 1;
					if(inARow > maxInARow) {
						maxInARow = inARow;
					}
				}
				else {
					inARow = 1;
				}
			}
		}
		else { // checks til the end of the board
			for (int i = (currentCol - 2); i <= 6; i++) {
				if (board[currentRow][i-1] == board[currentRow][i] && board[currentRow][i] == player) {
					inARow = inARow + 1;
					if(inARow > maxInARow) {
						maxInARow = inARow;
					}
				}
				else {
					inARow = 1;
				}
			}
		}

		if (maxInARow >= 4) return true;
		return false;
	}
	
	public boolean checkDiagonally() {
		int player = getLastPlayerCheck();
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		boolean win = false;
	
		// check diagonal up-right
		int inARow = 0;
		for (int i = 1; i <= 3; i++) {
			if (currentRow + i >= ROWS || currentCol + i >= COLS) break; // Out of bounds
			if (board[currentRow + i][currentCol + i] == player) {
				inARow++;
			} else break; // Break if consecutive pieces not found
		}

		// down-left
		for (int i = 1; i <= 3; i++) {
			if (currentRow - i < 0 || currentCol - i < 0) break; // Out of bounds
			if (board[currentRow - i][currentCol - i] == player) {
				inARow++;
			} else break;
		}

		if (inARow >= 3) win = true;
	
		// down-right
		inARow = 0;
		for (int i = 1; i <= 3; i++) {
			// checks if next piece is out of bounds
			if (currentRow - i < 0 || currentCol + i >= COLS) break;

			if (board[currentRow - i][currentCol + i] == player) {
				inARow++;
			} else break;
		}

		// up-left
		for (int i = 1; i <= 3; i++) {
			// checks if next piece is out of bounds
			if (currentRow + i >= ROWS || currentCol - i < 0) break;

			if (board[currentRow + i][currentCol - i] == player) {
				inARow++;
			} else break;
		}

		if (inARow >= 3) win = true;
		
		return win;
	}

	public boolean checkDiagonallyRight() {
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		boolean win = false;
		int inARow = 1;
		int maxInARow = 0;
		int row = 0;
		int col = 0;
		
		if (currentCol == 3) { //middle column
			if(currentRow > 2) {
				row = 0;
				col = currentRow - 3;
				while(row <= 5) {
					if (board[row][col] == board[row + 1][col + 1]) {
						inARow = inARow + 1;
						row = row + 1;
						col = col + 1;
						if(inARow > maxInARow) {
							maxInARow = inARow;
						}
						
					}
					else {
						inARow = 1;
					}
				}
			}
			else {
				row = 6;
				col = currentRow + 3;
				while(row >= 0) {
					if (board[row][col] == board[row - 1][col - 1]) {
						inARow = inARow + 1;
						row = row - 1;
						col = col - 1;
						if(inARow > maxInARow) {
							maxInARow = inARow;
						}
						
					}
					else {
						inARow = 1;
					}
				}
			}
		}
		/*
		else if (currentCol < 3 && (currentRow - currentCol) <= 3) { // left side of board and not top left corner
			if(currentCol == 0) {
				row = currentRow;
				col = currentCol;
				
				while(row <= 5) {
					if (board[row][col] == board[row + 1][col + 1]) {
						inARow = inARow + 1;
						row = row + 1;
						col = col + 1;
						if(inARow > maxInARow) {
							maxInARow = inARow;
						}
						
					}
					else {
						inARow = 1;
					}
				}
			}
			else if(currentRow >= 1){
				row = currentRow;
				col = currentCol;
				while(row >= 0) {
					if (board[row][col] == board[row - 1][col - 1]) {
						inARow = inARow + 1;
						row = row - 1;
						col = col - 1;
						if(inARow > maxInARow) {
							maxInARow = inARow;
						}
						
					}
					else {
						inARow = 1;
					}
				}
			}
			}
		else { // checks til the end of the board
			for (int i = (currentCol - 2); i <= 6; i++) {
				if (board[currentRow][i-1] == board[currentRow][i]) {
					inARow = inARow + 1;
					if(inARow > maxInARow) {
						maxInARow = inARow;
					}
				}
				else {
					inARow = 1;
				}
			}
		} */
		if (maxInARow >= 4) {
			System.out.println("win found horizontally");
			win = true;
		}
		return win;
	}
		

	public boolean checkDiagonallyUp() {
		int player = getLastPlayerCheck();
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		boolean win = false;
		int inARow = 1;
		int maxInARow = 0;
		
		// checks the next three pieces
		if (currentRow > 2) {
			win = false;
		} else {
			if (currentCol <= 3) { // checks up right
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow + i][currentCol + i] != player) {
						win = false;
						break;
					}
				}
			}

			// reset win check for col 3 since it can connect 4 from left And right
			if (currentCol == 3)
				win = true;

			if (currentCol >= 3) { // checks up left
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow + i][currentCol - i] != player) {
						win = false;
						break;
					}
				}
			}
		}

		if (win) System.out.println("win found diagonally up");
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
		} else {
			if (currentCol <= 3) { // checks down right
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow - i][currentCol + i] != player) {
						win = false;
						break;
					}
				}
			}

			// reset win check for col 3 since it can connect 4 from left And right
			if (currentCol == 3)
				win = true;

			if (currentCol >= 3) { // checks down left
				for (int i = 1; i <= 3; i++) {
					if (board[currentRow - i][currentCol - i] != player) {
						win = false;
						break;
					}
				}
			}
		}

		if (win) System.out.println("win found diagonally down");
		return win;
	}


	public int getCurrentPieceRow() {// returns the row of the last placed piece
		return lastPiecePlacedRow;
	}

	public int getCurrentPieceCol() {// returns the column of the last placed piece
		return lastPiecePlacedCol;
	}

	public Boolean isFull(int col) {// checks if the board is full
		if (board[board.length - 1][col] != 0)
			return true;
		else
			return false;
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
		if (!gameDone) {
			move(col);

			if (isWin()) {
				gameDone = true;

				// adds two to value of winning piece so the board class knows that a win was found
				board[getCurrentPieceRow()][getCurrentPieceCol()] = getLastPlayerCheck() + 2;
			}
		}

		// returns a copy of the board
		return copy().getBoard();
	}
}