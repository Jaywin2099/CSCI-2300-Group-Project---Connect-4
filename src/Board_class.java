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

        columnButtons = new JButton[COLS]; // Initialize columnButtons array
        boardButtons = new JButton[ROWS][COLS];

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                boardButtons[row][col] = new JButton(); 
                boardButtons[row][col].setBackground(Color.WHITE);
                boardButtons[row][col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

                boardButtons[row][col].setEnabled(false);
                boardPanel.add(boardButtons[row][col]);
            }
        }

        JPanel columnPanel = new JPanel(new GridLayout(1, COLS));
        for (int col = 0; col < COLS; col++) {
            columnButtons[col] = new JButton("" + col); 
            columnButtons[col].setBackground(Color.WHITE);
            columnButtons[col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
            columnButtons[col].addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();
        int col = -1;
        for (int c = 0; c < COLS; c++) {
            if (clickedButton == columnButtons[c]) {
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
                boardButtons[i][j].setOpaque(true);

                if (board[i][j] == 1) {
                    boardButtons[ROWS - i - 1][j].setBackground(Color.red);
                } else if (board[i][j] == 2) {
                    boardButtons[ROWS - i - 1][j].setBackground(Color.blue);
                }

                boardButtons[i][j].setVisible(true);
            }
        }

        pack();
        repaint();
    }
}