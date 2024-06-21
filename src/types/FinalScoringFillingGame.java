package types;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a specific type of filling game
 * that involves final scoring based on certain rules and conditions.
 *
 * @see AbstractFillingGame
 */
public class FinalScoringFillingGame extends AbstractFillingGame {

    private Filling[] symbols;
    private int numberOfUsedSymbols;
    private int seed;
    private int bottleSize;
    private int score;

    /**
     * Constructs a FinalScoringFillingGame with the specified parameters.
     *
     * @param symbols - The array of symbols for the game.
     * @param numberOfUsedSymbols - The number of symbols used in the game.
     * @param seed - The seed for randomization.
     * @param bottleSize - The size of the bottle.
     * @requires numberOfUsedSymbols and bottleSize to be positive integers.
     */
    public FinalScoringFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bottleSize) {
        super(symbols, numberOfUsedSymbols, seed, bottleSize);
        this.symbols = symbols;
        this.numberOfUsedSymbols = numberOfUsedSymbols;
        this.seed = seed;
        this.bottleSize = bottleSize;
    }

    /**
     * Constructs a FinalScoringFillingGame with the specified parameters and initial score.
     *
     * @param symbols - The array of symbols for the game.
     * @param numberOfUsedSymbols - The number of symbols used in the game.
     * @param seed - The seed for randomization.
     * @param bottleSize - The size of the bottle.
     * @param score - The initial score for the game.
     * @requires numberOfUsedSymbols and bottleSize to be positive integers.
     * @requires score to be a non-negative integer.
     */
    public FinalScoringFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bottleSize, int score) {
        this(symbols, numberOfUsedSymbols, seed, bottleSize);
        this.score = score;
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
	 * @return The score value initialized in this instance.
	 */
	public int getScore() {
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
	
	// Methods

	@Override
	/**
	 * Extends the functionality of the provideHelp method.
	 * It calls the superclass's provideHelp method, deducts 100 points from the current score,
	 * and gets a new bottle.
	 */
	public void provideHelp() {
		super.provideHelp();
		System.out.println("(For a 100 points payment of course 🌚)");
		this.setScore(this.score() - 100);
		if (this.getScore() < 0) {
			this.setScore(0);
		}
		this.getNewBottle();
		System.out.println(this.toString());
	}

	@Override
	/**
	 * Updates the score for this instance.
	 * If the number of moves is >= 10, the initial score will
	 * remain 1000, if the number of moves is > 10, and <= 15
	 * the score will be set to 500, else if the number of moves is > 15 and <= 25, 
	 * the score will be set to 200, else if the number of moves is > 25
	 * the score will be set to 0.
	 */
	public void updateScore() {
		if (super.jogadas() > 10 && super.jogadas() <= 15) {
			if (this.getScore() >= 500) {
				this.setScore(500);
			}
			else if (this.getScore() <= 500 && this.getScore() >= 200) {
				this.setScore(200);
			}
			else {
				this.setScore(0);
			}
		} 
		else if (super.jogadas() > 15 && super.jogadas() <= 25) {
			if (this.getScore() >= 200) {
				this.setScore(200);
			}
			else {
				this.setScore(0);
			}
		} else if (super.jogadas() > 25) {
			this.setScore(0);
		}
	}
	
	@Override
	/**
	 * Checks if the round is finished.
	 * 
	 * @return true if the all Bottles in the game table initialized in
	 * this instance has a single type of content, false otherwise.
	 */
	public boolean isRoundFinished() {
		return super.areAllFilled();
	}

	@Override
	/**
	 * @return The score value initialized in this instance.
	 */
	public int score() {
		return score;
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
		if (!this.areAllFilled()) {
			return super.toString() + EOL + "Status: The round is not finished." + EOL
			+ this.jogadas() + " moves have been used until now.";
		}
		else {
			return super.toString() + EOL + "Status: This round is finished." + EOL
			+ this.jogadas() + " moves were used.";
		}
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
		result = prime * result + Objects.hash(bottleSize, numberOfUsedSymbols, score, seed);
		return result;
	}

	// equals
	
	@Override
	/**
	 * Indicates whether some other object is equal to this one.
	 *
	 * @param obj - The reference object with which to compare.
	 * @requires obj to be an instance of FinalScoringFillingGame.
	 * @return true if this object is the same as the obj argument, false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinalScoringFillingGame other = (FinalScoringFillingGame) obj;
		return bottleSize == other.bottleSize && numberOfUsedSymbols == other.numberOfUsedSymbols
				&& score == other.score && seed == other.seed && Arrays.equals(symbols, other.symbols);
	}
	
}
