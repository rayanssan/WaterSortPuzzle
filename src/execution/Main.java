/**
 * Programação Centrada em Objetos (26706) S1 (2023/24)
 * Projeto
 * Grupo 01
 * 60282 - Rayan Serafim Santana
 * 60471 - Guilherme Bastos
 * 60858 - Diogo Piçarra
 */

package execution;

import java.util.Scanner;
import java.util.Random;

import types.*;

/**
 * Represents the entry point of the water sort puzzle program.
 */
public class Main {
	
	private static boolean firstRound = true;

    /**
     * The entry point of the water sort puzzle program.
     * Prompts the user to choose game settings and initializes a chosen game mode.
     *
     * @param args - The command-line arguments (unused in this program).
     */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		if (firstRound) {
			System.out.println("Welcome to");
		}
		System.out.println("                                                                                                                                                          \n"
		+ "                                                                                                                                                                           \n"
		+ "                           ___                                                             ___                                                            ,--,             \n"
		+ "                         ,--.'|_                                                         ,--.'|_          ,-.----.                                      ,--.'|             \n"
		+ "         .---.           |  | :,'           __  ,-.                      ,---.   __  ,-. |  | :,'         \\    /  \\         ,--,       ,----,      ,----|  | :             \n"
		+ "        /. ./|           :  : ' :         ,' ,'/ /|          .--.--.    '   ,'\\,' ,'/ /| :  : ' :         |   :    |      ,'_ /|     .'   .`|    .'   .`:  : '             \n"
		+ "     .-'-. ' | ,--.--. .;__,'  /    ,---. '  | |' |         /  /    '  /   /   '  | |' .;__,'  /          |   | .\\ : .--. |  | :  .'   .'  .' .'   .'  .|  ' |     ,---.   \n"
		+ "    /___/ \\: |/       \\|  |   |    /     \\|  |   ,'        |  :  /`./ .   ; ,. |  |   ,|  |   |           .   : |: ,'_ /| :  . |,---, '   ./,---, '   ./'  | |    /     \\  \n"
		+ " .-'.. '   ' .--.  .-. :__,'| :   /    /  '  :  /          |  :  ;_   '   | |: '  :  / :__,'| :           |   |  \\ |  ' | |  . .;   | .'  / ;   | .'  / |  | :   /    /  | \n"
		+ "/___/ \\:     '\\__\\/: . . '  : |__.    ' / |  | '            \\  \\    `.'   | .; |  | '    '  : |__         |   : .  |  | ' |  | |`---' /  ;--`---' /  ;--'  : |__.    ' / | \n"
		+ ".   \\  ' .\\   ,\" .--.; | |  | '.''   ;   /;  : |             `----.   |   :    ;  : |    |  | '.'|        :     |`-:  | : ;  ; |  /  /  / .`| /  /  / .`|  | '.''   ;   /| \n"
		+ " \\   \\   ' \\ /  /  ,.  | ;  :    '   |  / |  , ;            /  /`--'  /\\   \\  /|  , ;    ;  :    ;        :   : :  '  :  `--'   ./__;     .'./__;     .';  :    '   |  / | \n"
		+ "  \\   \\  |--;  :   .'   \\|  ,   /|   :    |---'            '--'.     /  `----'  ---'     |  ,   /         |   | :  :  ,      .-.;   |  .'   ;   |  .'   |  ,   /|   :    | \n"
		+ "   \\   \\ |  |  ,     .-./ ---`-'  \\   \\  /                   `--'---'                     ---`-'          `---'.|   `--`----'   `---'       `---'        ---`-'  \\   \\  /  \n"
		+ "    '---\"    `--`---'              `----'                                                                   `---`                                                 `----'   \n"
		+ "                                                                                                                                                                           ");
		System.out.println("Type 'exit' at any time to quit the game\n");
		
		if (firstRound) {
			System.out.println("Water Sort Puzzle features two exciting game modes for you to choose from: Normal and Bet.\n"
	        + "In Normal mode, your objective is to organize all liquids of one type under a single bottle.\n"
	        + "Complete the puzzle within the following move limits to earn points:\n"
	        + "- 1000 points for 10 or fewer moves.\n"
	        + "- 500 points for 11 to 15 moves.\n"
	        + "- 200 points for 16 to 25 moves.\n"
	        + "In Bet mode, you have a limited number of plays and place a bet between 50 and 500"
	        + "against your current score.\n"
	        + "If you successfully complete the puzzle, your get double the score!\n"
	        + "Good luck!\n");
		}
		
		// Game mode choice
		int gameMode = getUserInput(scanner, "Choose your game mode:\nNormal: 1\nBet: 2", 1, 2, null);

		// Icons choice
        Filling[] symbols = null;
        int icons = getUserInput(scanner, 
        String.format("Choose the game icons:\nBalls %s: 1\nAnimals %s: 2\nSquares %s: 3\nEmojis %s: 4",
        Balls.values()[0], Animals.values()[0], Squares.values()[0], Emojis.values()[0]), 1, 4, null);
		
        switch (icons) {
	        case 1:
	            symbols = Balls.values();
	            break;
	        case 2:
	            symbols = Animals.values();
	            break;
	        case 3:
	            symbols = Squares.values();
	            break;
	        case 4:
	            symbols = Emojis.values();
	            break;
        }
	    
	    // Create a random Seed
	    Random randomSeedMaker = new Random();
	    int seed = randomSeedMaker.nextInt();
	    
	    // Bottle size choice
	    int bottleSize = getUserInput(scanner, "Enter a bottle size between 3 and 20, inclusive: ", 3, 20, null);
	  
	    // Initial Score
		int score = 1000;
		
	    switch(gameMode) {
		    case 1: {
			    	FinalScoringFillingGame normal = new FinalScoringFillingGame(
				        symbols,
				        symbols.length,
				        seed,
				        bottleSize,
				        score
			    	);
			    	System.out.println(normal.toString());
			    	
			    	while (!normal.isRoundFinished()) {
			    		normal.setGameStarted(true);
			    		
				    	// Bottle choice or help (?)
			    		int bottle1 = getUserInput(scanner, String.format("Enter the index (0-%d) of the bottle "
			    		+ "which you would like to pick ", 
			    		normal.getNumberOfUsedSymbols() - 1 + 3)
			    		+ "(You can also type '?' for help or 'new round' to start a new game): ", 
			    		0, normal.getNumberOfUsedSymbols() - 1 + 3, normal);
			    		
			    		if (bottle1 == -1) {
			    			normal.provideHelp();
			    			continue;
			    		}
			    			
				    	// Bottle to transfer choice or help (?)
				    	int bottle2 = getUserInput(scanner, String.format("Enter the index (0-%d) of the bottle "
				    	+ "which you would like to transfer to: ", 
				    	normal.getNumberOfUsedSymbols() - 1 + 3)
				    	+ "(You can also type '?' for help or 'new round' to start a new game): ", 
				    	0, normal.getNumberOfUsedSymbols() - 1 + 3, normal);
				    	
			    		if (bottle2 == -1) {
			    			normal.provideHelp();
			    			continue;
			    		}
	
			    		normal.play(bottle1, bottle2);
			    		
				    	normal.updateScore();
				    	System.out.println(normal.toString());
				    } 
			    	normal.setGameStarted(false);
			    	
			    	System.out.println("WINNER! 🏆");
			    	System.out.println("Score: " + normal.score());
				    int playAgain = getUserInput(scanner, "Do you want to play another round?\nYes: 1\nNo: 2", 1, 2, null);
					if (playAgain == 1) {
						firstRound = false;
						normal.startNewRound();
					} else {
						System.out.println("Goodbye! 👋 Exiting game...");
				        System.exit(0);
					}
	    		}
		    	break;
		    case 2: {
		    	int bet = getUserInput(scanner, "Enter your bet, between 50 and 500: ", 50, 500, null);
		    	int maxPlays = getUserInput(scanner, "Enter the number of maximum plays, between 20 and 50: ", 20, 50, null);
		    	
		    	BettingFillingGame betting = new BettingFillingGame(
		    		symbols,
		    		symbols.length,
					seed,
					bottleSize,
					score + 2*bet,
					bet,
					maxPlays
		    	);
		    	System.out.println(betting.toString());
		    	while (!betting.isRoundFinished())  {
		    		betting.setGameStarted(true);
		    		
		    		 // Bottle choice or help (?)
		    		int bottle1 = getUserInput(scanner,  String.format("Enter the index (0-%d) of the bottle "
    				+ "which you would like to pick ", 
    				betting.getNumberOfUsedSymbols() - 1 + 3)
		    		+ "(You can also type '?' for help or 'new round' to start a new game): ", 
		    		0, betting.getNumberOfUsedSymbols() - 1 + 3, betting);
		    		
		    		if (bottle1 == -1) {
		    			betting.provideHelp();
		    			continue;
		    		}
		    		
			    	// Bottle to transfer choice or help (?)
			    	int bottle2 = getUserInput(scanner, String.format("Enter the index (0-%d) of the bottle "
	    			+ "which you would like to transfer to: ", 
	    			betting.getNumberOfUsedSymbols() - 1 + 3)
			    	+ "(You can also type '?' for help or 'new round' to start a new game): ", 
			    	0, betting.getNumberOfUsedSymbols() - 1 + 3, betting);
			    	
		    		if (bottle2 == -1) {
		    			betting.provideHelp();
		    			continue;
		    		}
		    		
		    		// Validate move
		    		betting.play(bottle1, bottle2);
			    	
			    	betting.updateScore();
			    	System.out.println(betting.toString());
			    } 
		    	betting.setGameStarted(false);
		    	
		    	if(betting.areAllFilled()){
			    	System.out.println("WINNER! 🏆");
			    	System.out.println("Score: " + betting.score());
			    } else {
			    	System.out.println("GAME OVER 😭");
			    	System.out.println("Score: " + betting.score());
			    }
			    int playAgain = getUserInput(scanner, "Do you want to play another round?\nYes: 1\nNo: 2", 1, 2, null);
				if (playAgain == 1) {
					firstRound = false;
					betting.startNewRound();
				} else {
					System.out.println("Goodbye! 👋 Exiting game...");
			        System.exit(0);
				}
		    }
		    break;
	    }
	    scanner.close();
	}
	
	/**
	 * Prompts the user to input an integer within a specified range, 
	 * or one of the allowed strings ("new round", "exit", and "?" if hasHelp is true).
	 *
	 * @param prompt - The message displayed to the user as a prompt.
	 * @param min - The minimum allowed value for user input (inclusive).
	 * @param max - The maximum allowed value for user input (inclusive).
	 * @param gameInstance - An optional argument, given only for input queries made during the course of a game,
	 * otherwise should be set to null.
	 * @requires min and max to be greater than 0, and max to be greater or equal than min.
	 * @throws InputMismatchException - if the input is not a valid integer or one of the allowed strings.
	 * @return The validated user input within the specified range.
	 */
    private static int getUserInput(Scanner scanner, String prompt, int min, int max, FillingGame gameInstance) {
        String userInput = "";
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println(prompt);
            userInput = scanner.nextLine();

            if (userInput.length() == 0) {
            	System.out.println("Invalid choice. Please choose a valid option.");
            }
            else if (userInput.equals("exit")) {
            	System.out.println("Goodbye! 👋 Exiting game...");
		        System.exit(0);
            }
            else if (userInput.equals("new round") && gameInstance != null) {
            	firstRound = false;
            	gameInstance.startNewRound();
            	return -1;
            }
            else if (userInput.equals("?") && gameInstance != null) {
            	return -1;
            }
            else {
	            try {
	                if (Integer.parseInt(userInput) >= min && Integer.parseInt(userInput) <= max) {
	                    validChoice = true;
	                } 
	                else {
	                	System.out.println("Invalid choice. Please choose a valid option.");
	                	validChoice = false;
	                	continue;
	                }
	            } 
	            catch (NumberFormatException e) {
	            	System.out.println("Invalid choice. Please choose a valid option.");
	            }
            }
        }
        return Integer.parseInt(userInput);
    }

}
