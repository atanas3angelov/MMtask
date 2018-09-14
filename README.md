# MMtask
MentorMate's task for an intern position

Task description:
Create Java console application that implements the classical game Hangman. It is not expected from you to draw the "Hangman", but there should be an indicator showing how many attempts the player has left. The code with the solution should be published to a public github repository.

Requirements:
● The game should use a file as a dictionary containing all words/phrases and categories that can be used during play (* the file format is shown below).
● As the game starts the players should be able to select one of several categories of words/phrases to play with.
● After the player selects a category the application choses random word/phrase from that category.
● In the beginning all letters of the chosen word/phrase should be displayed as "_" separated with a whitespace " " and all spaces are transformed to double spaces (" ").
● The player can enter single latin letter. If the chosen word has this letter the corresponding positions in the word are changed from “_” to the letter
● The maximum number of mistakes that player can make for a word/phrase is 10.
● After the player guesses a word he wins a point for his score and should be able to continue playing.
● Score should be saved while playing.
● The game ends when a person makes 10 mistakes for the current word/phrase.

Dictionary:
The file should contain categories and words, each on a new line. The categories are marked with an underscore symbol “_” - example “_football team”.
Example:
_Football teams
Manchester United
Arsenal
Chelsea
Liverpool
FC Bayern München
Juventus
Real Madrid
FC Barcelona
Olympique de Marseille
Paris Saint-Germain
_Books
In Search of Lost Time
Don Quixote
Ulysses
The Great Gatsby
Moby Dick
Hamlet
War and Peace
The Odyssey
_Programming principles
Encapsulation
Abstraction
Inheritance
Polymorphism
KISS
DRY
Open Closed
Single Responsibility
Composition over inheritance

Game flow example:

Please choose a category:
Football teams
Books
Programming principles
>books
Attempts left: 10
Current word/phrase: _ _ _ _ _ _ _ _ _ _ _
Please enter a letter:
> k
The word/phrase doesn’t have this letter.
Attempts left: 9
Current word/phrase: _ _ _ _ _ _ _ _ _ _ _
Please enter a letter:
> a
Attempts left: 9
Current word/phrase: _ a _ a _ _ _ _ a _ _
Please enter a letter:
> e
Attempts left: 9
Current word/phrase: _ a _ a _ _ _ e a_ e
Please enter a letter:
....
Congratulations you have revealed the word/phrase:
W a r a n d p e a c e

Current score: 1
Please choose a category:
Football teams
Books
Programming principles
