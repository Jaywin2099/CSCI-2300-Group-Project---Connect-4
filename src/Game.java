
public class Game implements Observer{
	int turnsPlayed = 0;
	Board_class board;
	
	public Game(Board_class board) {	
		this.board = board;
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
		if(getPlayerCheck() == 0) { //player 1
			
		}
		turnsPlayed++;
	}
	
	public int getCurrentTurn() {
		return turnsPlayed;
	}

	public void restart() {
		
	}
	
	public int getPlayerCheck() {
		return turnsPlayed % 2; // if odd turn, next turn is player 2, if even player 1
	}
	
	public void update(int col) {
		// ...
	}
}



