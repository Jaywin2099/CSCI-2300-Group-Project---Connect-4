public class Driver {
    public static void main(String[] args) {
        // expects the number of players passed as argument
        if (args.length != 1 || (!args[0].equals("1") && !args[0].equals("2"))) {
            System.out.println("Pass Number of Players as Argument: 1 or 2");
            System.exit(0);
        }

        // creates controller
        Controller controller = new Controller();

        // gets number of players
        int numPlayers = Integer.parseInt(args[0]);

        // initiates gui
        Board_class board = new Board_class(controller);
    
        // creates game
        Game connect4 = new Game(board);

        // registers game with controller
        controller.register(connect4);

        // registers computer player as another observer if the user chose singleplayer
        if (numPlayers == 1) {
            controller.register(new MonteCarloPlayer(connect4));
        }
    }
}