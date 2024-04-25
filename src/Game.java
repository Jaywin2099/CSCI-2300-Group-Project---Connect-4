
public class Game implements Observer{
	int turnsPlayed = 0;
	int[][] board = new int[6][7];
	int lastPiecePlacedRow;
	int lastPiecePlacedCol;
	//Board_class board;
	
	public Game(Board_class board) {	
		//this.board = board;
	}

	public Game () {}

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

	public boolean isDraw () {
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
		if(turnsPlayed > 7) {
			if(checkVertically() == true || checkHorizontally() == true || checkDiagonallyUp() == true 
			   || checkDiagonallyDown() == true) { //add diagonal win condition
				win = true;
			}
		}
		else {
			win = false;
		}
		return win;
	}
	
	public boolean checkVertically() {
		int player = getPlayerCheck() + 1; // 0 + 1 = 1 which is player 1 piece
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		
		//checks the next three pieces
		if(board[currentRow][currentCol+1] == player && board[currentRow][currentCol+2] == player 
				&& board[currentRow][currentCol+3] == player) { // checks up
			return true;
		}
		
		else if(board[currentRow][currentCol-1] == player && board[currentRow][currentCol-2] == player 
				&& board[currentRow][currentCol-3] == player) { // checks down
			return true;
		}
		return false;
	}
	
	public boolean checkHorizontally() {
		int player = getPlayerCheck() + 1; // 0 + 1 = 1 which is player 1 piece
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		
		//checks the next three pieces
		if(board[currentRow+1][currentCol] == player && board[currentRow+2][currentCol] == player 
				&& board[currentRow+3][currentCol] == player) { // checks right
			return true;
		}
		
		else if(board[currentRow-1][currentCol] == player && board[currentRow-2][currentCol] == player 
				&& board[currentRow-3][currentCol] == player) { // checks left
			return true;
		}
		return false;
	}
	
	public boolean checkDiagonallyUp() {
		int player = getPlayerCheck() + 1; // 0 + 1 = 1 which is player 1 piece
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		
		//checks the next three pieces
		if(board[currentRow+1][currentCol+1] == player && board[currentRow+2][currentCol+2] == player 
				&& board[currentRow+3][currentCol+3] == player) { // checks up right
			return true;
		}
		
		else if(board[currentRow-1][currentCol+1] == player && board[currentRow-2][currentCol+2] == player 
				&& board[currentRow-3][currentCol+3] == player) { // checks up left
			return true;
		}
		return false;
	}
	
	public boolean checkDiagonallyDown() {
		int player = getPlayerCheck() + 1; // 0 + 1 = 1 which is player 1 piece
		int currentRow = getCurrentPieceRow();
		int currentCol = getCurrentPieceCol();
		
		//checks the next three pieces
		if(board[currentRow+1][currentCol-1] == player && board[currentRow+2][currentCol-2] == player 
				&& board[currentRow+3][currentCol-3] == player) { // checks down right
			return true;
		}
		
		else if(board[currentRow-1][currentCol-1] == player && board[currentRow-2][currentCol-2] == player 
				&& board[currentRow-3][currentCol-3] == player) { // checks down left
			return true;
		}
		return false;
	}
	
	public int getCurrentPieceRow(){
		return lastPiecePlacedRow;
	}
	
	public int getCurrentPieceCol(){
		return lastPiecePlacedCol;
	}
	
	public void move(int column) {
		lastPiecePlacedCol = column;
		if(getPlayerCheck() == 0) { //player 1 represented with 1
			for(int i = 0; i < 7; i++) {
				if (board[i][column] == 0){
					board[i][column] = 1;
					lastPiecePlacedRow = i;
					break;
				}
			}
		}
		else {//player 2 represented with 2
			for(int i = 0; i < 7; i++) {
				if (board[i][column] == 0){
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
	
	public void update(int col) {
		move(col);
	}
}