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
 * Represents a cup used for providing help in the game.
 * 
 * @see Bottle
 */
public class Cup extends Bottle {

	public static final int CUP_SIZE = 1; 
	private static final int TIMES_OF_USAGES = 3;
	public static String empty = "⚪";
	
	private int usageCount = 0;
	private Filling[] bottle;

	 /**
     * Default constructor.
     * Initializes a Cup instance.
     */
	public Cup() {
		super(CUP_SIZE);
		System.out.println("A Cup has come to help you! You can send a maximum of " +
		TIMES_OF_USAGES + " icons to the cup to make the game easier!");
		this.usageCount = 0;
	}
	
    /**
     * Initializes a Cup instance with a given bottle content array.
     * 
     * @param bottle - An array of bottle contents to initialize the cup's content.
     */
	public Cup(Filling[] bottle) {
		super(bottle.length);
		this.bottle = bottle;
		this.usageCount = 0;
	}
	
    /**
     * @return The array of bottle contents.
     */
	public Filling[] getBottle() {
		return bottle;
	}
	
    /**
     * Sets the array of bottle contents initialized in this instance to a new array.
     * 
     * @param bottle - The array of bottle contents to be set.
     */
	public void setBottle(Filling[] bottle) {
		this.bottle = bottle;
	}
	
    /**
     * @return The usage count value initialized in this instance.
     */
	public int getUsageCount() {
		return usageCount;
	}
	
    /**
     * Sets the usage count value initialized in this instance to a new value.
     * 
     * @param usageCount - The usage count value to be set.
     * @requres usageCount > 0
     */
	public void setUsageCount(int usageCount) {
		this.usageCount = usageCount;
	}

	/**
	 * Receives a filling and updates the cup's usage count.
	 *
	 * @param s - The filling to be received by the cup.
	 * @return true if the cup can keep receiving content afterwards, false otherwise.
	 */
	public boolean receive(Filling s) {
		if (this.getUsageCount() < TIMES_OF_USAGES) {
			super.getContent()[0] = null;
			this.setUsageCount(this.getUsageCount() + 1);
			super.receive(s);
			
			if (this.getUsageCount() == TIMES_OF_USAGES) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	// toString
	
	/**
	 * Returns a String representation of this Cup instance.
	 *
	 * @return A String representation of the Cup.
	 */
	public String toString() {
		StringBuilder cupRep = new StringBuilder();
		if (super.getContent()[0] == null) {
			cupRep.append(empty);
		}
		else {
			cupRep.append(super.getContent()[0]);
		}
		return cupRep.toString();
	}
	
	// hashCode

	@Override
	/**
	 * @return the hash code value for this object.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(bottle);
		result = prime * result + Objects.hash(usageCount);
		return result;
	}
	
	// equals

	@Override
	/**
	 * Indicates whether some other object is equal to this one.
	 *
	 * @param obj - The reference object with which to compare.
	 * @requires obj to be an instance of Cup.
	 * @return true if this object is the same as the obj argument, false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cup other = (Cup) obj;
		return Arrays.equals(bottle, other.bottle) && usageCount == other.usageCount;
	}

}