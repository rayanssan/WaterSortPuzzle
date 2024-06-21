/**
 * Programação Centrada em Objetos (26706) S1 (2023/24)
 * Projeto
 * Grupo 01
 * 60282 - Rayan Serafim Santana
 * 60471 - Guilherme Bastos
 * 60858 - Diogo Piçarra
 */

package types;

import java.util.Arrays;
import java.util.Objects;

/**
 * The AbstractFillingGame class serves as a base class for filling game implementations
 * and implements the FillingGame interface.
 *
 * @see FillingGame
 */
public abstract class AbstractFillingGame implements FillingGame {

	public static String EOL = System.lineSeparator();
	private int numberOfMoves = 0;
	private boolean gameStarted = false;

	private Filling[] symbols;
	private int numberOfUsedSymbols;
	private int seed;
	private int bottleSize;
	private Table gameTable;
	
	/**
     * Constructs an AbstractFillingGame with the specified parameters.
     *
     * @param symbols - The array of symbols for the game.
     * @param numberOfUsedSymbols - The number of symbols used in the game.
     * @param seed - The seed for randomization.
     * @param bottleSize - The size of the bottle.
     * @requires numberOfUsedSymbols and bottleSize to be a positive integer.
     */
	public AbstractFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bottleSize) {
		this.symbols = symbols;
		this.numberOfUsedSymbols = numberOfUsedSymbols;
		this.seed = seed;
		this.bottleSize = bottleSize;
	    // Create game table with user choices
	    this.gameTable = new Table(symbols, symbols.length, seed, bottleSize);
	}
	
	// Getters and Setters
	
	/**
	 * @return The symbols initialized in this instance.
	 */
	public Filling[] getSymbols() {
		return symbols;
	}
	
	/**
	 * Sets the symbols initialized in this instance to a new Filling array.
	 *
	 * @param symbols - The array of symbols to be set.
	 */
	public void setSymbols(Filling[] symbols) {
	    this.symbols = symbols;
	}
	
	/**
	 * @return The number of used symbols initialized in this instance.
	 */
	public int getNumberOfUsedSymbols() {
		return numberOfUsedSymbols;
	}
	
	/**
	 * Sets the number of used symbols initialized in this instance to a new value.
	 *
	 * @param numberOfUsedSymbols - The number of used symbols to be set.
	 * @requires numberOfUsedSymbols to be a positive integer.
	 */
	public void setNumberOfUsedSymbols(int numberOfUsedSymbols) {
	    this.numberOfUsedSymbols = numberOfUsedSymbols;
	}

	/**
	 * @return The seed value initialized in this instance.
	 */
	public int getSeed() {
		return seed;
	}
	
	/**
	 * Sets the seed value initialized in this instance to a new value.
	 *
	 * @param seed - The seed to be set.
	 */
	public void setSeed(int seed) {
	    this.seed = seed;
	}

	/**
	 * @return The size of the bottle initialized in this instance.
	 */
	public int getBottleSize() {
		return bottleSize;
	}
	
	/**
	 * Sets the size of the bottle initialized in this instance to a new value.
	 *
	 * @param bottleSize - The size of the bottle to be set.
	 * @requires bottleSize to be a positive integer.
	 */
	public void setBottleSize(int bottleSize) {
	    this.bottleSize = bottleSize;
	}
	
	/**
	 * @return The game Table object initialized in this instance.
	 */
	public Table getGameTable() {
		return gameTable;
	}
	
	/**
	 * Sets the game Table object initialized in this instance to a new Table object.
	 *
	 * @param gameTable - The Table object to be set.
	 */
	public void setGameTable(Table gameTable) {
	    this.gameTable = gameTable;
	}
	
	/**
	 * @return The number of moves in this instance.
	 */
	public int jogadas() {
		return numberOfMoves;
	}

	/**
	 * Sets the number of moves in this instance to a new value.
	 *
	 * @param numberOfMoves - The value to be set.
	 * @requires numberOfMoves to be a positive integer.
	 */
	public void setJogadas(int numberOfMoves) {
		this.numberOfMoves = numberOfMoves;
	}

	/**
	 * @return if the game of the current instance has started or not.
	 */
	public boolean isGameStarted() {
		return gameStarted;
	}

	/**
	 * Sets the game started value in this instance to a new value.
	 *
	 * @param gameStarted - The value to be set.
	 */
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
	
	// Abstract Methods

	/**
	 * Creates and returns an extra new instance of a Bottle.
	 *
	 * @return A new Bottle instance.
	 */
	public abstract Bottle getNewBottle();

	/**
	 * Updates the score.
	 */
	public abstract void updateScore();
	
	/**
	 * Checks if the round is finished.
	 * 
	 * @return true if the all Bottles in the game table initialized in this instance
	 * have a single type of content, false otherwise.
	 */
	public abstract boolean isRoundFinished();

	/**
	 * @return the current status of the score.
	 */
	public abstract int score();
	
	// Methods

	/**
	 * Increases the number of moves by one.
	 */
	public void increaseMoves() {
		this.setJogadas(this.jogadas() + 1);
	}

	/**
	 * Handles the transferring of a symbol from one bottle to another.
	 * Prints the details of the transfer to the console.
	 *
	 * @param x - The source bottle index from which the symbol is transferred.
	 * @param y - The destination bottle index to which the symbol is transferred.
	 * @requires x and y to be non-negative integers.
	 */
	public void play(int x, int y) {
		this.increaseMoves();
		
		if (x == y) {
			if (this.getGameTable().get(x).size() == 1) {
				System.out.println("You just poured the cup into the cup! ¯\\_(ツ)_/¯");
			}
			else {
				System.out.println("You just poured into the same bottle again! ¯\\_(ツ)_/¯");
			}
			return;
		}
		
		if (this.getGameTable().get(x).size() == 1) {
			System.out.println("You cannot pour out from a cup!");
			return;
		}
		
		Filling sourceFilling = this.top(x);
		
		if (sourceFilling == null) {
			System.out.println(String.format("You poured the empty bottle %d into bottle %d, "
    		+ "nothing happened! ¯\\_(ツ)_/¯", x, y));
    		return;
		}
		
		boolean didItPour = this.getGameTable().pourFromTo(x, y);
		
		if (didItPour) {
			if (this.getGameTable().get(y).size() == 1) {
				System.out.println(String.format("%s was sent from bottle %d to the cup and got drank! 🥤", 
				sourceFilling, x));
			}
			else {
				System.out.println(String.format("%s transferred from bottle %d to bottle %d",
				sourceFilling, x, y));
			}
		}
		else if (this.getGameTable().get(y).size() == 1) {
			// It's a cup that reached its limit
			System.out.println(String.format("%s was sent from bottle %d to the cup and got drank! 🥤", 
			sourceFilling, x));
			this.getGameTable().removeCup(this.getGameTable().get(y));
			this.setNumberOfUsedSymbols(this.getNumberOfUsedSymbols() - 1);
		}
	}
	
	/**
	 * Provides help for the player.
	 * Prints a message indicating that the help has been activated and increases the number of used symbols 
	 * in this instance in preparation for the addition of the Cup.
	 */
	public void provideHelp() {
		System.out.println("Help - Don't worry the Cup is here to save the day! 🥤");
		this.setNumberOfUsedSymbols(this.getNumberOfUsedSymbols() + 1);
	}

	/**
	 * Retrieves the topmost (first non-null) filling from a given 
	 * Bottle initialized in the game table initialized in this instance.
	 *
	 * @param x - The index of a Bottle initialized in the game table 
	 * initialized in this instance.
	 * @requires x > 0.
	 * @return the filling present a the top of the Bottle at the given index.
	 */
	public Filling top(int x) {
		return this.getGameTable().top(x);
	}

    /**
     * Checks if the non-null contents present in a given Bottle instance,
     * initialized in the game table initialized in this instance, are equal.
     *
     * @return true if all non-null contents of the Bottle at the given index are equal, false otherwise.
     */
	public boolean singleFilling(int x) {
		return this.getGameTable().singleFilling(x);
	}

	/**
	 * Initiates a new round of the game.
	 */
	public void startNewRound() {
		System.out.println("\nNew round starting... 🆕");
		this.getGameTable().regenerateTable();
	}

	/**
	 * @return true if all bottles are filled by a
	 * single type of content, false otherwise.
	 */
	public boolean areAllFilled() {
		return this.getGameTable().areAllFilled();
	}

	// toString

	/**
	 * @return a String representation of the 
	 * current state of the game table initialized in 
	 * the current instance.
	 * Each bottle printed corresponds to a representation of the contents of the 
	 * 5 bottle objects associated with the current instance.
	 */
	public String toString() {
		return "Score: " + this.score() + EOL + gameTable.toString();
	}
	
	// hashCode
	
	@Override
	/**
	 * @return the hash code value for this object.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(symbols);
		result = prime * result
				+ Objects.hash(bottleSize, gameStarted, gameTable, numberOfMoves, numberOfUsedSymbols, seed);
		return result;
	}
	
	// equals

	@Override
	/**
	 * Indicates whether some other object is equal to this one.
	 *
	 * @param obj - The reference object with which to compare.
	 * @requires obj to be an instance of AbstractFillingGame.
	 * @return true if this object is the same as the obj argument, false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractFillingGame other = (AbstractFillingGame) obj;
		return bottleSize == other.bottleSize && gameStarted == other.gameStarted
				&& Objects.equals(gameTable, other.gameTable) && numberOfMoves == other.numberOfMoves
				&& numberOfUsedSymbols == other.numberOfUsedSymbols && seed == other.seed
				&& Arrays.equals(symbols, other.symbols);
	}
	
}
