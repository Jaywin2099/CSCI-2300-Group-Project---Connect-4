import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Board_class extends JFrame{
	private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final int CELL_SIZE = 100;
    private JButton[][] buttons;
    private Game c4;

	// jacob: im passing the model to the board, so when the player presses a column just use c4.[whatever jeddrick decides the method for handling moves]( the column);
	
	public Board_class (Game connect4)
	{
		this.c4 = connect4;
		setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

		final JPanel panel = new JPanel(new GridLayout(ROWS, COLS));
		for (int col = 0; col < COLS; col++) {
		    buttons[ROWS][col] = new JButton();
		    buttons[ROWS][col].setBackground(Color.WHITE);
		    buttons[ROWS][col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		    buttons[ROWS][col].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
		// need to a method to move the piece to the bottom int lowestEmptyRow = getLowestEmptyRow(column);
			     
		//if {
		// } else {
				// if Column is full, handle accordingly
			    // Maybe display a message or take another action
				
				}
		    
		    });
		}
	
		      
		    panel.add(buttons[ROWS][COLS]);
}
}
	


