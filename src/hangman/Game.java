package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private int score;
	private HashMap<String, Integer> categoryWordcount;
	private Scanner ui = new Scanner(System.in);
	private Phrase gamePhrase;
	private short attemptsLeft;
	
	public Game() {
		
		categoryWordcount = new HashMap<>();
		
		// populating the categories and their word counts by reading the dictionary file
		File file = new File("dictionary.txt");
		FileReader fileReader = null;
		BufferedReader in = null;
		
		try {
			fileReader = new FileReader(file);
			in = new BufferedReader(fileReader);
			
			String line;
			String category = null;
			int wordCount = 0;
			while ((line = in.readLine()) != null) {
				if (line.startsWith("_")) {
					
					line = line.substring(1);
					
					if (category != null && wordCount != 0) {
							categoryWordcount.put(category, wordCount);	//assumes unique categories
							category = line;
							wordCount = 0;
					} else {
						category = line;
						wordCount = 0;
					}
				}
				else
					wordCount++;
			}
			if (!categoryWordcount.containsKey(category) && wordCount != 0)
				categoryWordcount.put(category, wordCount);	//	last category
			
		} catch (FileNotFoundException e) {
			System.out.println("The dictionary file was not found and the game cannot start.");
			System.exit(1);
		} catch(IOException e) {
			System.out.println("The dictionary file is corrupted.");
			System.exit(1);
		} finally {
			try {
				in.close();
			} catch(IOException e) {
				System.out.println("Memory leak: the bufferedReader would not close.");
			}
		}
		
	}
	
	private void showCategories() {
		
		for (String category: categoryWordcount.keySet()) {
			System.out.println(category);
		}
	}
	
	public void start() {
		
		boolean won = true;
		
		while (won) {
			setup();
			won = play();
			if (won) {
				System.out.println("Congratulations you have revealed the word/phrase: \n" + gamePhrase.getPhrase() +"\n");
				System.out.println("Current score: " + ++score);
			}
			else {
				System.out.println("Sorry, you ran out of attempts. The word/phrase was: \n" + gamePhrase.getPhrase() + "\n");
				System.out.println("Your score: " + score);
			}
				
		}
	}
	
	private void setup() {
		
		System.out.println("Please choose a category:");
		showCategories();
		
		boolean containsCategory = false;
		OUTERLOOP: while(!containsCategory) {
			
			String selectedCategory = ui.nextLine();
			
			// linear loop complication due to key-sensitivity
			for(String category: categoryWordcount.keySet()) {
				if(category.equalsIgnoreCase(selectedCategory)) {
					
					containsCategory = true;
					
					// generate a random number for the word/phrase selection
					Random rand = new Random();
					int phraseNumber = rand.nextInt(categoryWordcount.get(category)-1) + 1;
					
					gamePhrase = new Phrase( findPhrase(category, phraseNumber) );
					
					attemptsLeft = 10;
					
					break OUTERLOOP;
				}
			}
			
			System.out.println("No such category. Try again:");
		}
	}
	
	private String findPhrase(String category, int phraseNumber) {
		
		String result = null;
		
		File file = new File("dictionary.txt");
		FileReader fileReader = null;
		BufferedReader in = null;
		
		try {
			fileReader = new FileReader(file);
			in = new BufferedReader(fileReader);
			
			String line;
			String fileCategory = "_" + category;
			while ((line = in.readLine()) != null) {
				if (line.equals(fileCategory)) {
					for(int i = 1; i < phraseNumber; i++)
						line = in.readLine();
					result = in.readLine();
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("The dictionary file was not found and so goes for the phrase.");
			System.exit(1);
		} catch(IOException e) {
			System.out.println("The dictionary file is corrupted.");
			System.exit(1);
		} finally {
			try {
				in.close();
			} catch(IOException e) {
				System.out.println("Memory leak: the bufferedReader would not close.");
			}
		}
		
		return result;
	}
	
	private boolean play() {
		
		while (attemptsLeft != 0 && !gamePhrase.solved() ) {
			statusMenu();
			String guess = ui.nextLine();
			boolean guessed = false;
			
			if (guess.length() > 1)
				guessed = gamePhrase.guess(guess);
			else if (guess.length() == 1)
				guessed = gamePhrase.guess(guess.charAt(0));
			
			if (!guessed)
				attemptsLeft--;
		}
		
		return (attemptsLeft == 0)? false: true;
	}
	
	private void statusMenu() {
		System.out.println("Attempts left: " + attemptsLeft);
		System.out.print("Current word/phrase: ");
		gamePhrase.printSolvedPhrase();
		System.out.println();
		System.out.println("Please enter a letter: ");
	}
}
