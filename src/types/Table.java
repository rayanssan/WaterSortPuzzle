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
import java.util.Random;

import execution.Main;

/**
 * Represents a table with 5 bottles arranged on it,
 * each filled with a set of symbols used in the game. 
 */
public class Table {

	public static String EOL = System.lineSeparator();
	public static final String empty = "⬜";
	public static final int DIFICULTY = 3;
	public static final int DEFAULT_BOTTLE_SIZE = 5;

    private Filling[] symbols;
    private int numberOfUsedSymbols;
    private int seed;
    private int sizeBottles;
    private Bottle[] bottles;

    /**
     * Constructs a Table with bottles filled with symbols.
     *
     * @param symbols - An array of fillings to be added to the bottles.
     * @param numberOfUsedSymbols - The number of symbols to be used in each bottle.
     * @param seed - The seed for randomizing the symbols in each bottle.
     * @param sizeBottles - The size of each bottle.
     */
    public Table(Filling[] symbols, int numberOfUsedSymbols, int seed, int sizeBottles) {
    	this.symbols = symbols;
    	this.numberOfUsedSymbols = numberOfUsedSymbols;
    	this.seed = seed;
    	this.sizeBottles = sizeBottles;
        this.bottles = new Bottle[this.numberOfUsedSymbols + DIFICULTY];

    	Random random = new Random(seed);
        for (int i = 0; i < bottles.length; i++) {
            Filling[] bottleContent = new Filling[sizeBottles];
            Bottle bottle = null;
            if (i < bottles.length - DIFICULTY) {
	            for (int j = 0; j < sizeBottles; j++) {
	                int randomIndex = random.nextInt(numberOfUsedSymbols);
	                bottleContent[j] = symbols[randomIndex];
	            }
	            bottle = new Bottle(bottleContent);
            }
            else {
            	bottle = new Bottle(sizeBottles);
            }

            // Assign the initialized Bottle to the array
            bottles[i] = bottle;
        }
    }
    
    // Getters and Setters
    
    /**
     * @return The array of filling symbols initialized in this instance.
     */
    public Filling[] getSymbols() {
        return symbols;
    }

    /**
     * Sets the array of filling symbols initialized in this instance to a new array.
     *
     * @param symbols - The array to be set.
     */
    public void setSymbols(Filling[] symbols) {
        this.symbols = symbols;
    }

    /**
     * @return The number of symbols initialized in this instance.
     */
    public int getNumberOfUsedSymbols() {
        return numberOfUsedSymbols;
    }

    /**
     * Sets the number of symbols initialized in this instance.
     *
     * @param numberOfUsedSymbols - The number of symbols to be set.
     * @requires numberOfUsedSymbols > 0.
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
     * @return The bottle size value initialized in this instance.
     */
    public int getSizeBottles() {
        return sizeBottles;
    }

    /**
     * Sets the bottle size value initialized in this instance to a new value. 
     *
     * @param bottleSize - The size to be set.
     * @requires bottleSize > 0.
     */
    public void setSizeBottles(int bottleSize) {
        this.sizeBottles = bottleSize;
    }

    /**
     * @return The array of bottles initialized in this instance.
     */
    public Bottle[] getBottles() {
        return bottles;
    }

    /**
     * Sets the array of bottles initialized in this instance to a new array.
     *
     * @param bottles - The array to be set.
     */
    public void setBottles(Bottle[] bottles) {
        this.bottles = bottles;
    }
    
    // Methods
    
    /**
     * Retrieves a specified Bottle at the specified index in the Table.
     *
     * @param i The index of the Bottle to retrieve.
     * @requires i > 0 && i <= this.getBottles().length.
     * @return The Bottle at the specified index.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range.
     */
	public Bottle get(int i) {
		return this.getBottles()[i];
	}
	
    /**
     * Sets a specified Bottle at the specified index in the Table.
     *
     * @param i The index of the Bottle to be set.
     * @requires i > 0 && i <= this.getBottles().length.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range.
     */
	public void set(int i, Bottle bottle) {
		this.getBottles()[i] = bottle;
	}

	/**
	 * Restarting the main method of the Main class, 
	 * therefore regenerating the table and allowing a new game, with new rules, to be made.
	 */
	public void regenerateTable() {
		Main.main(null);
	}

	/**
	 * Checks if a specified bottle is filled by a single type of content.
	 *
	 * @param x - The index of the bottle to check.
	 * @requires x > 0 && x <= this.getBottles().length.
	 * @return true if the specified bottle has a single type of filling, false otherwise.
	 */
	public boolean singleFilling(int x) {
		return this.get(x).isSingleFilling();
	}

    /**
     * Checks if the bottle at the specified index is empty.
     *
     * @param x - The index of the bottle to check.
     * @requires x > 0 && x <= this.getBottles().length.
     * @return true if the bottle is empty, false otherwise.
     */
    public boolean isEmpty(int x) {
        // Return the isEmpty status of the bottle at the given index
        return this.get(x).isEmpty();
    }

    /**
     * Checks if the bottle at the specified index is full.
     *
     * @param x - The index of the bottle to check.
     * @requires x > 0 && x <= this.getBottles().length.
     * @return true if the bottle is full, false otherwise.
     */
    public boolean isFull(int x) {
        // Return the isFull status of the bottle at the given index
        return this.get(x).isFull();
    }
    
    /**
     * Checks if all bottles on the table are full with one type of content,
     * which is the condition for the game to be won.
     *
     * @return true if all bottles are full with one type of content, false otherwise.
     */
    public boolean areAllFilled() {
        for (Bottle bottle : this.getBottles()) {
            if (!bottle.isSingleFilling()) {
                return false;
            }
        }
        // All bottles are full
        return true;
    }
    
    /**
     * Pours the first non-null content from one bottle to another.
     *
     * @param i - The index of the source bottle.
     * @param j - The index of the target bottle.
     * @requires i > 0 && j > 0 && i <= this.getBottles().length && j <= this.getBottles().length.
     * @return true if the pouring is successful, false otherwise.
     */
	public boolean pourFromTo(int i, int j) {
        Bottle sourceBottle = this.get(i);
        Bottle targetBottle = this.get(j);
    	if (this.isFull(j)) {
    		System.out.println(String.format("Bottle %d is already full!", j));
    		return false;
    	}
        
    	// Identify cups in this table, and set them to null
    	for (Bottle bottle : this.getBottles()) {
    		if (bottle.size() == 1) {
    			bottle.set(0, null);
    		}
    	}
    	
        // Get the first non-null filling from the source bottle
        Filling firstNonNullFilling = this.top(i);

        // Pour the first non-null filling from source to target
        boolean didItReceive = targetBottle.receive(firstNonNullFilling);
        
        if (didItReceive) {
	        // Remove the transferred filling from the source bottle
	        sourceBottle.pourOut();
	        return true;
        }
        else if (targetBottle.size() == 1) {
        	// If the size of the target bottle is 1 and the pouring was unsuccessful then the cup has reached its usage limit
        	// Remove the cup from the table
        	sourceBottle.pourOut();
        	return false;
        }
        System.out.println(String.format("You cannot transfer %s from bottle %d to bottle %d, "
    	+ "because the top icons are different! 🧐", firstNonNullFilling, i, j));
        return false;
	}

	/**
	 * Adds a bottle to the existing array of bottles.
	 *
	 * @param bottle - The bottle to be added.
	 */
	public void addBottle(Bottle bottle) {
	    Bottle[] newBottles = Arrays.copyOf(this.getBottles(), this.getBottles().length + 1);

	    // Add the new bottle to the end of the new bottles array
	    newBottles[newBottles.length - 1] = bottle;

	    // Update the array of bottles
	    this.setBottles(newBottles);
	}
	
	/**
	 * Removes a cup from the array of bottles.
	 *
	 * @param cup - The cup to be removed.
	 */
	public void removeCup(Bottle cup) {
		System.out.println("Cup has reached its usage limit and must now go, so long!");
	    Bottle[] currentBottles = this.getBottles();
	    
	    int indexOfCup = -1;
	    int i = 0;
	    while (indexOfCup == -1 && i < this.getBottles().length) {
	        if (this.get(i) == cup) {
	        	indexOfCup = i;
	        }
	    	i++;
	    }
	        
	    if (indexOfCup != -1) {
	        Bottle[] newBottles = new Bottle[currentBottles.length - 1];
	        System.arraycopy(currentBottles, 0, newBottles, 0, indexOfCup);
	        System.arraycopy(currentBottles, indexOfCup + 1, newBottles, indexOfCup, 
	        currentBottles.length - indexOfCup - 1);

	        // Update the array of bottles
	        this.setBottles(newBottles);
	    }
	}

	/**
	 * Get the first non-null filling from the given bottle.
	 *
	 * @param x - The index of the bottle to search for the first non-null filling.
	 * @requires x > 0 && x <= this.getBottles().length.
	 * @return The first non-null filling, or null if no non-null filling is found.
	 */
	public Filling top(int x) {
	    try {
	        return this.get(x).top();
	        // If top() doesn't throw an exception, the bottle is not empty
	    } catch (IndexOutOfBoundsException e) {
	        return null; // If top() throws an exception, the bottle is empty
	    }
	}

	// toString
	
	/**
	 * Returns a String representation of the Table, including the contents of each bottle.
	 *
	 * @return A String representation of the Table.
	 */
	public String toString() {
	    StringBuilder tableView = new StringBuilder();
	  
	    for (int i = 0; i < this.getSizeBottles(); i++) {
	        for (Bottle bottle : this.getBottles()) {
                if (i == this.getSizeBottles() - 1 && bottle.size() == 1)  {
                	tableView.append(bottle.toString());
                }
                else if (bottle.size() != 1) {
                    tableView.append(bottle.toString().split(EOL)[i]);
	            }
	            // Separate bottles with a tab
	            tableView.append("\t");
	        }
	        tableView.append(EOL);
	    }
	    
	    return tableView.toString().trim();
	}

	// hashCode

	@Override
	/**
	 * @return the hash code value for this object.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bottles);
		result = prime * result + Arrays.hashCode(symbols);
		result = prime * result + Objects.hash(numberOfUsedSymbols, seed, sizeBottles);
		return result;
	}

	// equals
	
	@Override
	/**
	 * Indicates whether some other object is equal to this one.
	 *
	 * @param obj - The reference object with which to compare.
	 * @requires obj to be an instance of Table.
	 * @return true if this object is the same as the obj argument, false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		return Arrays.equals(bottles, other.bottles) && numberOfUsedSymbols == other.numberOfUsedSymbols
				&& seed == other.seed && sizeBottles == other.sizeBottles && Arrays.equals(symbols, other.symbols);
	}

}
