package algo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Thanu
 */
public class EightQueenBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// declare class constants as needed
	private static final int NUM_QUEENS = 8;
	final int BOARD_DIMENSION = NUM_QUEENS;

	boolean[][] board;

	// array for queens' position
	int[][] queens = new int[8][2];

	// gui components
	JPanel chessPanel;
	JPanel square[];
	Icon whiteQueen, blackQueen;
	JLabel whiteIcon, blackIcon;

	// constructor method
	public EightQueenBoard() {

		// gui stuff

		setupBoard();

		// add the squares to the chess panel

	} // end constructor

	public void setupBoard() {
		setTitle("Simulated Annealing for 8 Queens Problem");
		setPreferredSize(new Dimension(640, 640));
		setResizable(false);

		chessPanel = new JPanel(new GridLayout(8, 8));
		chessPanel.setPreferredSize(new Dimension(80, 80));
		chessPanel.setBackground(Color.BLACK);
		square = new JPanel[64];
		whiteQueen = new ImageIcon(getClass().getResource("white_queen.png"));
		blackQueen = new ImageIcon(getClass().getResource("black_queen.png"));
		whiteIcon = new JLabel("", whiteQueen, JLabel.CENTER);
		blackIcon = new JLabel("", blackQueen, JLabel.CENTER);
		for (int i = 0; i < square.length; i++)// adds chess squares to the
		// board
		{
			square[i] = new JPanel(new BorderLayout());
			square[i].setBackground(Color.BLACK);
			chessPanel.add(square[i]);
		}
		// this will make the squares the correct colors
		for (int i = 0; i < BOARD_DIMENSION; i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		for (int i = BOARD_DIMENSION + 1; i < (2 * BOARD_DIMENSION)
				&& i > BOARD_DIMENSION; i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		for (int i = (2 * BOARD_DIMENSION); i < (3 * BOARD_DIMENSION)
				&& i >= (2 * BOARD_DIMENSION); i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		for (int i = (3 * BOARD_DIMENSION) + 1; i < (4 * BOARD_DIMENSION)
				&& i > (3 * BOARD_DIMENSION); i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		for (int i = (4 * BOARD_DIMENSION); i < (5 * BOARD_DIMENSION)
				&& i >= (4 * BOARD_DIMENSION); i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		for (int i = (5 * BOARD_DIMENSION) + 1; i < (6 * BOARD_DIMENSION)
				&& i > (5 * BOARD_DIMENSION); i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		for (int i = (6 * BOARD_DIMENSION); i < (7 * BOARD_DIMENSION)
				&& i >= (6 * BOARD_DIMENSION); i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		for (int i = (7 * BOARD_DIMENSION) + 1; i < (8 * BOARD_DIMENSION)
				&& i > (7 * BOARD_DIMENSION); i = i + 2) {
			square[i].setBackground(Color.WHITE);
		}
		// puts the chess panel on the EightQueens frame
		add(chessPanel);

	}

	// recursive method solve
	public boolean loadQueen(boolean[][] queens) {
		setupBoard();

		board = new boolean[8][8];
		// The Queen has been placed on the board in column
		int row, column, i = 0;
		for (int j = 0; j < NUM_QUEENS; j++) {
			for (int k = 0; k < NUM_QUEENS; k++) {
				whiteIcon = new JLabel("", whiteQueen, JLabel.CENTER);
				blackIcon = new JLabel("", blackQueen, JLabel.CENTER);
				// row = queens[k][0];
				// column = queens[k][1];
				// board[row][column] = true;
				// i = row * BOARD_DIMENSION + column;
				// i=queens[k][0]*BOARD_DIMENSION+queens[k][1];
				i = j * NUM_QUEENS + k;
				if (queens[j][k] == true) {

					if (square[i].getBackground() == Color.BLACK) {
						square[i].add(blackIcon, BorderLayout.CENTER);
					} else {
						square[i].add(whiteIcon, BorderLayout.CENTER);
					}
				}
				// System.out.println("\nPP"+board[row][column]+i+" "+row+" "+column);
			}
		}
		/*
		 * square[0].add(blackIcon, BorderLayout.CENTER);
		 * square[9].add(blackIcon, BorderLayout.CENTER);
		 * square[18].add(blackIcon, BorderLayout.CENTER);
		 * square[27].add(blackIcon, BorderLayout.CENTER);
		 * square[36].add(blackIcon, BorderLayout.CENTER);
		 * square[45].add(blackIcon, BorderLayout.CENTER);
		 * square[54].add(blackIcon, BorderLayout.CENTER);
		 * square[63].add(blackIcon, BorderLayout.CENTER);
		 */
		return true;
	}

	public void printBoard() {
		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < 8; j++) {
				if (board[k][j]) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
	}
}
