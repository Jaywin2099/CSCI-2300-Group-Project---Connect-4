import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;
    private Board_class gui;
    private Game connect4;

    public Controller () {
        this.observers = new ArrayList<Observer>();
    }

    public void register (Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers (int col) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(col);
        }
    }
}
