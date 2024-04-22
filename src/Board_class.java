import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board_class extends JFrame{
	private Controller controller;

	// jacob: im passing the controller to the board, so when the player presses a column just use controller.notifyOberverse(col);
	
	public Board_class (Controller controller)
	{
		this.controller = controller;

		final JPanel panel = new JPanel();
		
	

}
}
