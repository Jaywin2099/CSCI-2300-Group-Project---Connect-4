import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board_class extends JFrame {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final int CELL_SIZE = 100;
    private JButton[] columnButtons;
    private JButton[][] boardButtons;
    private Controller controller;
   

    public Board_class(Controller controller) {
        this.controller = controller;
        

        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        columnButtons = new JButton[COLS]; 
        boardButtons = new CircularButton[ROWS][COLS];
        Color c1 = new Color(51,201,255);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        boardPanel.setBackground(c1);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
            	
                
            	boardButtons[row][col] = new CircularButton();
               
                ((CircularButton) boardButtons[row][col]).setColor(Color.WHITE);
                //boardButtons[row][col].setOpaque(true);
                boardButtons[row][col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                boardButtons[row][col].setEnabled(true);
                boardPanel.add(boardButtons[row][col]);
                boardButtons[row][col].addActionListener(new ActionListener() {
                	
	public void actionPerformed(ActionEvent e) {
		    CircularButton boardButton  =  (CircularButton) e.getSource();
		    int col = -1;
		    for (int r = 0; r < ROWS; r++) {
			    for (int c = 0; c < COLS; c++) {
			        if (boardButton == boardButtons[r][c]) {
			        	boardButtons[r][c].setEnabled(false);
			        	col = c;
			            break;
			        }
			    }
		    
			    // Notify controller about the button click
			    if (col != -1) {
			        controller.notifyObservers(col);
			        break;
			    }
		    }
		}
});
            }
        }

        JPanel columnPanel = new JPanel(new GridLayout(1, COLS));
        for (int col = 0; col < COLS; col++) {
            columnButtons[col] = new JButton("DROP"); 
            columnButtons[col].setBackground(Color.WHITE);
            columnButtons[col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
            columnButtons[col].addActionListener(new ActionListener() {
	
	public void actionPerformed(ActionEvent e) {
	    JButton clickedButton = (JButton) e.getSource();
	
	    int col = -1;
	    for (int c = 0; c < COLS; c++) {
	        if ( clickedButton == columnButtons[c]) {
	            col = c;
	            
	            break;
	        }
	    }
        // Notify controller about the button click
        if (col != -1) {
            controller.notifyObservers(col);
        	}
    	}
    });
            columnPanel.add(columnButtons[col]);
        }
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(columnPanel, BorderLayout.SOUTH);

        add(mainPanel); 
        pack(); 
        setVisible(true); 
    }

    public void updatePieces (int[][] board) {
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                if (board[i][j] == 1) {
                	boardButtons[ROWS - i - 1][j].setBackground(Color.RED);
                
                } else if (board[i][j] == 2) {
                	((CircularButton)boardButtons[ROWS - i - 1][j]).setBackground(Color.YELLOW);
                
                } else if (board[i][j]==3) {
                	((CircularButton) boardButtons[ROWS - i - 1][j]).setBackground(Color.red);
                	setTitle("Player 1 wins");
                	onWin();
                
                } else if (board[i][j]==4) {
                	((CircularButton) boardButtons[ROWS - i - 1][j]).setBackground(Color.YELLOW);
                	setTitle("Player 2 wins");
                	onWin();
                } else if (board[i][j] != 0) {
                    System.out.println("error, board assigned to value greater than 4");
                }
                
            }
        }

        for (int i = 0; i < columnButtons.length; i++) {
            if (board[board.length - 1][i] != 0) {
                columnButtons[i].setEnabled(false);
            }
        }

        pack();
        repaint();
    }
    

    public void onWin () {
        // disables all buttons
        for (int i = 0; i < columnButtons.length; i++) {
            columnButtons[i].setEnabled(false);
        }
    }
}