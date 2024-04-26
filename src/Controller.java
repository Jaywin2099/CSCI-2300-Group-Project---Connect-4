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
        }
    }
    
    public void setGUI (Board_class gui) {
        this.gui = gui;
    }
}
