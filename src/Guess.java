

public class Guess {

	private char[] matchResult = new char[5];

	public Guess(String userInput, String targetWord) {

		for (int i = 0; i < 5; i++) {
			if (userInput.charAt(i) == targetWord.charAt(i)) {
				matchResult[i] = Character.toUpperCase(userInput.charAt(i));
			} else if (targetWord.contains(String.valueOf(userInput.charAt(i)))) {
				matchResult[i] = Character.toLowerCase(userInput.charAt(i));

			} else {
				matchResult[i] = '*';
			}
		}
	}

	public char[] getMatchResult() {
		return matchResult;
	}
}
