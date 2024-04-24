
public class Game implements Observer{
	int turnsPlayed = 0;
	int[][] board = new int[6][7];
	//Board_class board;
	
	public Game(Board_class board) {	
		//this.board = board;
	}

	public boolean isWin() {
		if(turnsPlayed > 7) {
			
		}
		else {
			return false;
		}
	}

	public void getCurrentPieceLocation(){
		
	}
	
	public void move(int column) {
		if(getPlayerCheck() == 0) { //player 1 represented with 1
			for(int i = 0; i < 7; i++) {
				if (board[i][column] == 0){
					board[i][column] = 1;
				}
			}
		}
		else {//player 2 represented with 2
			for(int i = 0; i < 7; i++) {
				if (board[i][column] == 0){
					board[i][column] = 2;
				}
			}
		}
		turnsPlayed++;
	}
	
	public int getCurrentTurn() {
		return turnsPlayed;
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
		// ...
	}
}



