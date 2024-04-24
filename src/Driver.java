public class Driver implements Observer {
    private Game connect4;
    private int numPlayers;

    public static void main(String[] args) {
        // expects the number of players passed as argument
        if (args.length != 1) {
            System.out.println("Pass Number of Players as Argument: '1' or '2' ");
            System.exit(0);
        }

        // gets number of players
        numPlayers = Integer.parseInt(args[1]);
    
        // creates game
        connect4 = new Game();

        // registers game as observer
        Controller controller = new Controller();
        controller.register(connect4);

        // initiates gui
        Board_class board = new Board_class(controller);
    }

    public void update(int col) {
        // when the gui gets a word, i check if its a one player game
        if (numPlayers == 1) {
            connect4.update(getComputerMove());
        }
    }

    public int getComputerMove() {
        return 0;
    }
}
