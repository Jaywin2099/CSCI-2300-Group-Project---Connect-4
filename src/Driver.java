public class Driver {
    public static void main(String[] args) {
        // expects the number of players passed as argument
        if (args.length != 1) {
            System.out.println("Pass Number of Players as Argument: '1' or '2' ");
            System.exit(0);
        }

        Game connect4 = new Game();
        int numPlayers = Integer.parseInt(args[1]);

        // gui
        Board_class board = new Board_class(connect4);

        if (numPlayers == 1) {
            connect
        }
    }
}
