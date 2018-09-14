package hangman;

public class Phrase {

	private String phrase;
	private int[] solved;
	
	public Phrase(String phrase) {
		this.phrase = phrase;
		solved = new int[phrase.length()];
		for (int i = 0; i < phrase.length(); i++) {
			if (phrase.charAt(i) == ' ')
				solved[i] = 1;
		}
	}
	
	public String getPhrase() {
		return phrase;
	}
	
	public void printSolvedPhrase() {
		for (int i = 0; i < phrase.length(); i++) {
			if (solved[i] == 1) {
				if (phrase.charAt(i) == ' ')
					System.out.print("  ");
				else
					System.out.print(phrase.charAt(i) + " ");
			} else 
				System.out.print("_ ");
		}
	}
	
	/**
	 * Marks characters as solved and returns whether a guess was correct.
	 * @param ch
	 * @return true, if the character was in the phrase and false otherwise
	 */
	public boolean guess(char ch) {
		
		boolean guessed = false;
		
		for (int i = 0; i < phrase.length(); i++) {
			if ( Character.toLowerCase( phrase.charAt(i) ) == Character.toLowerCase(ch) ) {
				solved[i] = 1;
				guessed = true;
			}
		}
		return guessed;
	}
	
	public boolean guess(String phrase) {
		if ( this.phrase.equalsIgnoreCase(phrase) ) {
			for (int i = 0; i < solved.length; i++)
				solved[i] = 1;
			return true;
		}
		return false;
	}
	
	public boolean solved() {
		
		for (int i = 0; i < phrase.length(); i++) {
			if (solved[i] == 0)
				return false;
		}
		
		return true;
	}
}
