=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: wmicah
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Recursion : I used the course concept of recursion to clear out the rest of the game board 
  		until squares with numbers appeared. This was the correct concept to use because with each 
  		game board being different, and a single algorithm required multiple times, its made 
  		sense to use a recursive approach. 

  2. IO : To keep track of the high scores, I used IO to read and write to a file with fields for 
  		the user's score, first and last names, and age. This allowed the program to have a 
  		permanent "database", where previous users' scores were obtainable at any given point in 
  		time. 

  3. 2D Array : Due to the grid layout of the original minesweeper game, I chose to use a 2D array 
  		to keep track of the layout of the board. Through the use of nestled for loops, its was easy
  		to iterate through the entire board to find values of the number of bombs around around any 
  		given square. 

  4. JUnit : I used JUnit test to test certain functionality of different parts of the game. It was
  		helpful in ensuring each part of the game worked in a variety of different situations that 
  		many different input were handled appropriately. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game.java 
  	Compiles all the other files and runs the main method of the game. 
  	
  GameCourt.java
  	Runs the 2D array of the game board and all major game play logic.
  	
  NewSquare.java
  	A newer version of the old legacy Square class. Was originally created to test a new concept 
  	but went on to become a thing of its own. This class that extend JToggleButton and mostly 
  	keeps track of the different graphics of each square depending on its state. 
  	The board is made up of a 20x20 array of NewSquare. 
  
  ScoreBoard.java
  	Keeps track of the time/score of the player. 
  	
  ParaseScore.java
  	Was supposed to be spelled as parse, but this class keeps track of the IO and top scores.
  	Keeps a LinkedList of HighScorers. 
  
  HighScorer.java
  	This class helps create a consistent syntax to write out to the high score file. 
  	
  GameTest.java
  	JUnit test that test functionality of different parts of the game. 
  


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  		Understanding the Swing environment and its associated classes and functions was a huge 
  		hurdle to implementing this project. Once I discovered that the functionality was similar 
  		to the OCaml paint project, its made things a little easier. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  		I think overall there was good separation of functions and classes. I probably would have 
  		made game concepts more compartmentalized and easier to test individual functionality before 
  		trying to compile an entire game. Private state isn't as encapsulated as it could have been, 
  		but I had to allow certain manipulations due to lack of previous consideration. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
