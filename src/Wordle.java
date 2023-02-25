

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {

	ArrayList<String> words = new ArrayList<>();

	private String targetWord;

	int guessAttempt = 0;

	public Wordle() {

		readWordFile();

		setupUI();

	}

	private void readWordFile() {

		File f = new File("words.txt");
		try {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNext()) {
				words.add(scanner.nextLine());
			}
			targetWord = words.get((int) (Math.random() * words.size()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void setupUI() {

		JFrame frame = new JFrame("Wordle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		GuessPanel panel = new GuessPanel();
		frame.add(panel);

		JTextField userInputTextField = new JTextField(JTextField.RIGHT);
		frame.add(userInputTextField);

		JLabel targetWordLabel = new JLabel();
		frame.add(targetWordLabel);
		targetWordLabel.setText("Enter a five letter word");
		// System.out.println(targetWord);

		userInputTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String userInput = userInputTextField.getText();
				if (userInput.length() < 5) {
					JOptionPane.showMessageDialog(frame, "Word should be of length 5.");

				} else if (!words.contains(userInput)) {
					JOptionPane.showMessageDialog(frame, "Word not in word list. Try again");

				} else {

					guessAttempt = guessAttempt + 1;
					Guess guess = new Guess(userInput, targetWord);
					char[] matchResults = guess.getMatchResult();
					panel.updatePanel(userInput.toUpperCase(), guessAttempt, matchResults);

					if (checkIfWordleSolved(matchResults)) {
						userInputTextField.setEnabled(false);
						userInputTextField.setEditable(false);
						targetWordLabel.setText("You got it! The word was: " + targetWord);
					}

					if (guessAttempt == 6) {
						targetWordLabel.setText("GAME OVER, the word was: " + targetWord);
						userInputTextField.setEnabled(false);
						userInputTextField.setEditable(false);
					}
				}

				userInputTextField.setText("");

			}
		});

		userInputTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				if (userInputTextField.getText().length() >= 5)
					e.consume();
			}
		});

		frame.pack();
		frame.setVisible(true);

	}

	private boolean checkIfWordleSolved(char[] matchResults) {
		for (int i = 0; i < 5; i++) {
			if (!Character.isAlphabetic(matchResults[i]) || Character.isLowerCase(matchResults[i])) {
				return false;
			}

		}

		return true;
	}

	public static void main(String[] args) {

		Wordle wordle = new Wordle();

	}
}
