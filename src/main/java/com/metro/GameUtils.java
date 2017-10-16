package com.metro;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class GameUtils {

	Scanner scanner = new Scanner(System.in);

	/**
	 * Get the configured values from config.properties
	 * 
	 */
	public static Properties getProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.txt");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	/**
	 * Utility function to get the console input for position and if player want
	 * to continue playing
	 * 
	 */

	public String getInputValues(char inputType, int currPlayer, int size) {
		String input = "0";
		if (inputType == 'P') {
			String strPos = "";
			System.out.print("Enter the Position for Player "+ (currPlayer + 1) + " : ");
			scanner: while (scanner.hasNext()) {
				strPos = scanner.next();
				
				String regex = "^[0-"+(size - 1)+"],[0-"+(size - 1)+"]$";
				if (strPos.matches(regex)){
					break scanner;
				} else {
					System.out
							.println("Enter value between 0 and "+(size - 1)
									+ " in the format Row,Column : ");
				}
			}
			input = strPos;
		} else if (inputType == 'W') {
			String strPos = "";
			System.out.print("Start New Game (Y/N)? ");
			scanner: while (scanner.hasNext()) {
				strPos = scanner.next();
				if (strPos != null
						&& (strPos.length() == 1 && (strPos
								.equalsIgnoreCase("Y") || strPos
								.equalsIgnoreCase("N")))) {
					break scanner;
				} else {
					System.out.println("Invalid Input: Enter Y or N: ");
				}
			}
			input = strPos.toUpperCase();
		}
		return input;
	}

	/**
	 * Funtion to validate the property values
	 * 
	 */
	public boolean validatePropertyValues(Properties prop) {
		String boardsize = prop.getProperty("boardsize");
		String player1 = prop.getProperty("player1");
		String player2 = prop.getProperty("player2");
		String computer = prop.getProperty("computer");

		if (!boardsize.matches("^[3-9]|10$")) {
			System.out
					.println("Invalid Input: Boardsize should be a number and of range 3 to 10.");
			return false;
		}

		if (!(player1.matches("[^ ]") && player2.matches("[^ ]") && computer
				.matches("[^ ]"))) {
			System.out
					.println("Invalid Input: Players should be 1 character long.");
			return false;
		}
		return true;
	}
	
	public void initializeSkipMap(Map<String, Set<Integer>> skipmap){
		skipmap.put("row", new HashSet<Integer>());
		skipmap.put("column", new HashSet<Integer>());
		skipmap.put("diagonal", new HashSet<Integer>());
	}
}
