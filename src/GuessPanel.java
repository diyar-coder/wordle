

import javax.swing.*;
import java.awt.*;

class GuessPanel extends JPanel {
	public static final int CELL_SIZE = 100;

	public static final int NUM_ROWS = 6;

	public static final int NUM_COLS = 5;

	private String[] userInputs = new String[NUM_ROWS];

	private int guessAttempt = -1;

	private char[][] matchedResults = new char[NUM_ROWS][NUM_COLS];

	@Override
	protected void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);

		for (int row = 0; row < guessAttempt; row++) {
			for (int col = 0; col < NUM_COLS; col++) {

				if (matchedResults[row][col] == '*') {
					graphics.setColor(Color.GRAY);

				} else if (Character.isUpperCase(matchedResults[row][col])) {
					graphics.setColor(Color.GREEN);
				} else { // lowercase
					graphics.setColor(Color.YELLOW);
				}
				graphics.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);

				Font font = new Font("Arial", Font.BOLD, CELL_SIZE);
				graphics.setFont(font);
				graphics.setColor(Color.BLACK);
				graphics.drawString(String.valueOf(userInputs[row].charAt(col)), (col) * CELL_SIZE,
						(row + 1) * CELL_SIZE);

			}
		}

	}

	@Override
	public Dimension getPreferredSize() {

		return new Dimension(NUM_COLS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
	}

	public void updatePanel(String userInput, int guessAttempt, char[] matchedResult) {

		this.guessAttempt = guessAttempt;
		this.userInputs[guessAttempt - 1] = userInput;
		this.matchedResults[guessAttempt - 1] = matchedResult;
		this.repaint();
	}

}
