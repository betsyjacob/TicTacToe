# TicTacToe
TicTacToe 2.0

1. Deployment Details

o	mvn install (will create the executable jar file tictactoe-0.0.1.jar in target folder)

o	Copy the config.txt to the target folder and execute > java -jar tictactoe-0.0.1.jar


2. Project Overview

•	Following are the objects and the components involved in the tictactoe game.
      GAME	   board – Board
               players – String[]
               currentPlayer – Integer
               isGameOver – boolean
      BOARD	   size – Integer
               cell – Cell [][]
      CELL	   value – String
               hasBeenPlayed()

•	The size of the board and the characters used for the players are configurable using config.txt.
•	The player starting the game is found randomly.
•	User enters the position he would like to play in the following format – Row, Column.

•	The following validations are handled
      o	The size of the board should be in the range 3 to 11.
      o	The symbol used for the players are 1-character long. 
      o	The user entered position has the following validations.
            	The length of the format should be of length 3 (eg: 0,0 to 9,9)
            	The format entered should be comma separated.
            	The row and column values should be between 0 and 9.
      o	Validation when user tries to input in an already marked position.

•	Business Logic
      o	The player starting the game is found randomly.
      o	The Game is initialized with the size and players configured.
      o	The position of the player is entered till the game is over.
      o	When a user enters the position, system will check if the game is still going on and if the entered position is vacant.
            	If yes, logic to check if there is a winner with the current move.
            	If not, the next player can cintinue the game until a winner is found or the board has finished.
      o	If there is a Winner, the same will be displayed on the console and the user will be prompted to enter Y/N
         if he wants to continue the game.
      o	If it’s a Draw, the same will be displayed on the console and the user will be prompted to enter Y/N
         if he wants to continue the game.
      o	The winner logic checks the rows, columns and the diagonals to see if the current player has occupied all of it.
      o	The Board is displayed after each move by the players. 

3. Technical Specifications
   •	mvn install (will create the executable jar file tictactoe-0.0.1.jar in target folder)
   •	Edit & Copy the config.txt to the target folder and execute > java -jar tictactoe-0.0.1.jar
   •	The Junit test cases will be executed while build (mvn install) or run the command mvn test to 
      run the test cases explicitly.
