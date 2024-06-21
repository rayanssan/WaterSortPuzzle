package types;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a specific type of filling game that includes betting mechanics.
 *
 * @see AbstractFillingGame
 */
public class BettingFillingGame extends AbstractFillingGame { 

	private Filling[] symbols;
	private int numberOfUsedSymbols;
	private int seed;
	private int bottleSize;
	private int score;
	private int bet;
	private int maxPlays;

    /**
     * Constructs a BettingFillingGame with the specified parameters.
     *
     * @param symbols - The array of symbols for the game.
     * @param numberOfUsedSymbols - The number of symbols used in the game.
     * @param seed - The seed for randomization.
     * @param bottleSize - The size of the bottle.
     * @param score - The initial score for the game.
     * @param bet - The bet amount for the game.
     * @param maxPlays - The maximum number of plays allowed.
     * @requires numberOfUsedSymbols, bottleSize, bet, and maxPlays to be a positive integer.
     * @requires score to be a non-negative integer.
     */
	public BettingFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, 
	int bottleSize, int score, int bet, int maxPlays) {
		super(symbols, numberOfUsedSymbols, seed, bottleSize);
		this.symbols = symbols;
		this.numberOfUsedSymbols = numberOfUsedSymbols;
		this.seed = seed;
		this.bottleSize = bottleSize;
		this.score = score;
		this.bet = bet;
		this.maxPlays = maxPlays;
	}

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
	
	@Override
	/**
	 * @return The score value initialized in this instance.
	 */
	public int score() {
		return score;
	}
	
	/**
	 * Sets score value initialized in this instance to a new value.
	 *
	 * @param score - The score value to be set.
     * @requires score to be a non-negative integer.
	 */
	public void setScore(int score) {
	    this.score = score;
	}
	
	/**
	 * @return The bet value initialized in this instance.
	 */
	public int getBet() {
		return bet;
	}
	
	/**
	 * Sets the bet value initialized in this instance to a new value.
	 *
	 * @param bet - The bet value to be set.
     * @requires bet to be a positive integer.
	 */
	public void setBet(int bet) {
	    this.bet = bet;
	}
	
	/**
	 * @return The maximum number of plays value initialized in this instance.
	 */
	public int getMaxPlays() {
		return maxPlays;
	}
	
	/**
	 * Sets the maximum number of plays initialized in this instance to a new value.
	 *
	 * @param maxPlays - The maximum number of plays to be set.
     * @requires maxPlays to be a positive integer.
	 */
	public void setMaxPlays(int maxPlays) {
	    this.maxPlays = maxPlays;
	}
	
	// Methods
	
	@Override
	/**
	 * Extends the functionality of the provideHelp method of the superclass, 
	 * by deducting a move 100 points from the score of this instance.
	 */
	public void provideHelp() {
		super.provideHelp();
		System.out.println("(For a 100 points and one move payment of course 🌚)");
		super.setJogadas(this.jogadas() + 1);
		this.setScore(this.score() - 100);
		this.getNewBottle();
		System.out.println(this.toString());
	}

	@Override
	/**
	 * Checks if the round is finished.
	 *
	 * @return true if the round is finished either by reaching the maximum number of moves
	 * or if all the bottles are all filled by a single type of content, false otherwise.
	 */
	public boolean isRoundFinished() {
		return this.jogadas() == this.getMaxPlays() || super.areAllFilled();
	}

	/**
	 * Calculates and returns the number of remaining plays based on the maximum allowed plays.
	 *
	 * @return The number of plays left.
	 */
	public int numberOfPlaysLeft() {
		return this.getMaxPlays() - super.jogadas();
	}

	@Override
	/**
	 * Creates a new Cup instance and adds it to the game table.
	 *
	 * @return The newly created Cup instance.
	 */
	public Bottle getNewBottle() {
		Cup cup = new Cup();
		this.getGameTable().addBottle(cup);
		return cup;
	}

	@Override
	/**
	 * Updates the score for this instance.
	 * If the number of moves is less than the number of maximum plays
	 * the score will incremented with the the duplicated value of the bet,
	 * otherwise the score will be decremented with the duplicated value of the bet.
	 */
	public void updateScore() {
		if (this.numberOfPlaysLeft() == 0) {
			if (super.jogadas() < getMaxPlays()) {
				this.setScore(this.score() + this.getBet()*2);
			}
			else {
				this.setScore(this.score() - this.getBet()*2 < 0 ? 0 : this.score() - this.getBet()*2);
			}
		}
	}
	
	@Override
	/**
	 * @return true if all bottles are filled by a
	 * single type of content, false otherwise.
	 */
	public boolean areAllFilled() {
		return super.areAllFilled();
	}
	
	// toString

	@Override
	/**
	 * @return a String representation of the 
	 * current state of the game table initialized in 
	 * the current instance, along with the status of the current round.
	 */
	public String toString() {
		String table = super.toString();
		if (!this.isGameStarted()) {
			table += String.format("\nYou have %d moves, good luck!", this.getMaxPlays());
		}
		else if (!super.areAllFilled()) {
			int nPlaysLeft = this.numberOfPlaysLeft();
			table += String.format(nPlaysLeft == 1 ? 
			"\nStatus: %d moves have been used until now. You only have %d move left! 😱" : 
			nPlaysLeft == 0 ? "\nStatus: You have no moves left! 😭"
			: "\nStatus: %d moves have been used until now. You still have %d moves left.", 
			this.jogadas(), nPlaysLeft);
		}
		else {
			return super.toString() + EOL + "Status: This round is finished." + EOL
			+ this.jogadas() + " moves were used.";
		}
		return table;
	}

	// hashCode
	
	@Override
	/**
	 * @return the hash code value for this object.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(symbols);
		result = prime * result + Objects.hash(bet, bottleSize, maxPlays, numberOfUsedSymbols, score, seed);
		return result;
	}

	// equals
	
	@Override
	/**
	 * Indicates whether some other object is equal to this one.
	 *
	 * @param obj - The reference object with which to compare.
	 * @requires obj to be an instance of BettingFillingGame.
	 * @return true if this object is the same as the obj argument, false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BettingFillingGame other = (BettingFillingGame) obj;
		return bet == other.bet && bottleSize == other.bottleSize && maxPlays == other.maxPlays
				&& numberOfUsedSymbols == other.numberOfUsedSymbols && score == other.score && seed == other.seed
				&& Arrays.equals(symbols, other.symbols);
	}
	
}
