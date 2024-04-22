import java.util.ArrayList;

public class Controller {
    private ArrayList<Observer> observers;
    private Connect4GUI gui;
    private Game connect4;

    public Controller () {
        this.observers = new ArrayList<Observer>();
    }

    public void register (Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers (int col) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(col, gui);
        }
    }
}
