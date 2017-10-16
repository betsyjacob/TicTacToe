package com.metro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.metro.exception.GameExceptionHandler;
import com.metro.model.Cell;
import com.metro.model.Game;

public class GameController {

	Game game;	
	Map<String, Set<Integer>> skipmap = new HashMap<String, Set<Integer>>();
	public final int boardsize;
	public final String player1;
	public final String player2;
	public final String computer;
	GameUtils util = new GameUtils();

	/**
	 * Initialize the instances with the values configured in the property file.
	 * The program exits if the configured values are not valid.
	 */
	public GameController() throws GameExceptionHandler {
		Properties prop = GameUtils.getProperties();
		boolean isValid = util.validatePropertyValues(prop);
		if (!isValid)
			System.exit(0);
		this.boardsize = Integer.parseInt(prop.getProperty("boardsize"));
		this.player1 = prop.getProperty("player1");
		this.player2 = prop.getProperty("player2");
		this.computer = prop.getProperty("computer");
		util.initializeSkipMap(skipmap);
	}

	/**
	 * The Tictactoe game is initialized with default and configured values. The
	 * start of game is initiated.
	 */
	public static void main(String[] args) throws GameExceptionHandler{
		GameController gameController = new GameController();
		gameController.startGame();
	}

	/**
	 * The Game pass through the various steps. Start player is selected
	 * randomly Player enters the position he wish to play from console
	 * (Format:row,column) The game continues till the whole board is marked or
	 * the winner is found System checks if the game is still ON or the entered
	 * position is filled. If vacant, system checks if the current one is a
	 * winner move If yes, User will be prompted to enter if he want to continue
	 * the game IF not, the player is switched to next and the game continues.
	 * 
	 */
	public void startGame() throws GameExceptionHandler{
		int iFrstPlyr = ThreadLocalRandom.current().nextInt(0, 3);
		game = new Game(boardsize, new String[] { player1, player2, computer },
				iFrstPlyr);
		displayBoard();
		int maxEntries = boardsize * boardsize;
		int totEntries = 0;
		while (totEntries < maxEntries) {
			// Enter the location for start player
			String position = util.getInputValues('P', game.getCurrentPlayer(),
					boardsize);
			String[] pos = position.split(",");
			int r = Integer.parseInt(pos[0]);
			int c = Integer.parseInt(pos[1]);
			if (game.isGameOver())
				break;
			if (game.getBoard().getCell()[r][c].hasBeenPlayed()) {
				System.out
						.println("The position is occupied. Kindly enter a vacant position.");
				continue;
			}
			game.getBoard().getCell()[r][c].setValue(game.getPlayers()[game.getCurrentPlayer()]);
			
			this.checkWinner(r, c);
			if (game.isGameOver()) {
				displayBoard();
				break;
			}
			this.switchPlayer();
			displayBoard();
			totEntries++;
		}
		System.out
				.println((totEntries == maxEntries) ? "Game Over : It's a Draw"
						: "");
		String newGame = util.getInputValues('W', 0, boardsize);
		if (newGame.equals("Y")) {
			resetGame();
		}
	}

	/**
	 * Winner section checks if the current moves of the player has completely
	 * filled up any row or column or either diagonals
	 */

	public void checkWinner(int r, int c) {
		if (!skipmap.get("row").contains(r)) {
			checkBoardCombinations("row", r);
		}
		if (!skipmap.get("column").contains(c)) {
			checkBoardCombinations("column", c);
		}
		if (!skipmap.get("diagonal").contains(0)) {
			checkBoardCombinations("A", -1);
		}
		if (!skipmap.get("diagonal").contains(1)) {
			checkBoardCombinations("B", -1);
		}
	}
	
	/**
	 * Checking all the possible combination of winning Type being Row, Column
	 * or either diagonals named A and B
	 */
	public void checkBoardCombinations(String type, int index) {
		HashSet<String> cellVals = new HashSet<String>();
		int hasWon = Integer.MIN_VALUE;
		for (int i = 0; i < this.boardsize; i++) {
			Cell cell = (type.equalsIgnoreCase("row")) ? game.getBoard()
					.getCell()[index][i]
					: (type.equalsIgnoreCase("column")) ? game.getBoard()
							.getCell()[i][index]
							: (type.equalsIgnoreCase("A")) ? game.getBoard()
									.getCell()[i][i] : game.getBoard()
									.getCell()[i][this.boardsize - i - 1];

			String cellVal = cell.getValue();
			if (cellVal.equals("")) {
				hasWon = -1;
				break;
			}
			if (cellVal.length() > 0)
				cellVals.add(cellVal);
			if (cellVals.size() > 1) {
				hasWon = -10;
				break;
			}
		}
		if (hasWon == Integer.MIN_VALUE && cellVals.size() == 1) {
			String val = cellVals.iterator().next();
			hasWon = (val.equals(game.getPlayers()[0]) ? 0 : (val.equals(game
					.getPlayers()[1])) ? 1 : 2);
		}
		udateGameResult(type, index, hasWon);
	}

	/**
	 * Updates the game result after each move
	 * Check if winner OR update the skip map
	 */
	public void udateGameResult(String type, int index, int winner) {
		if (winner >= 0) {
			setWinner(winner);
		} else if (winner != -1) {
			updateSkipMap(type, index);
		}
	}


	/**
	 * Message if a winner is found
	 */
	public void setWinner(int winnerPlayer) {
		System.out.println("\nGame Over : Player " + (winnerPlayer + 1)
				+ " has won.");
		game.setGameOver(true);
	}
	
	/**
	 * The players are switched after each move
	 */
	public void switchPlayer() {
		game.setCurrentPlayer((game.getCurrentPlayer() < 2) ? (game
				.getCurrentPlayer() + 1) : 0);
	}

	/**
	 * Reset if the User wants to continue the game
	 */
	public void resetGame() throws GameExceptionHandler {
		GameController gameController = new GameController();
		gameController.startGame();
	}

	/**
	 * Updates the skip Map
	 */
	public void updateSkipMap(String type, int index) {
		if (type.equals("row")) {
			Set<Integer> vals = new HashSet<Integer>();
			vals = skipmap.get(type);
			vals.add(index);
			skipmap.put(type, vals);
		}
	}
	
	/**
	 * The board is displayed evertime a player makes a valid move
	 */
	public void displayBoard() {
		for (int i = 0; i < boardsize; i++) {
			System.out.println("\n");
			System.out.print("| ");
			for (int j = 0; j < boardsize; j++) {
				String cellVal = game.getBoard().getCell()[i][j].getValue();
				System.out
						.print(((cellVal != null && cellVal.length() > 0) ? cellVal
								: " ") + " | ");
			}
		}
		System.out.println("\n");
	}
}
