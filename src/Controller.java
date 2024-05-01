import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;
    private Board_class gui;

    public Controller () {
        this.observers = new ArrayList<Observer>();
    }

    public void register (Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers (int col) {
        for (int i = 0; i < observers.size(); i++) {
            int[][] board = observers.get(i).update(col);

            // sends board to gui to update view
            gui.updatePieces(board);
            
            // checks for win
            Boolean win = false;
            for (int k = 0; k < board.length; k++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[k][j] > 2) {
                        win = true;
                        System.out.println("Value found in winning coord: " + Integer.toString(board[k][j]));
                        break;
                    }
                }

                if (win) break;
            }
            
            if (win) break;
        }
    }
    
    public void setGUI (Board_class gui) {
        this.gui = gui;
    }
}
