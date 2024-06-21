package types;

/**
 * An implementation of the types.Symbol interface.
 * 
 * @author PCO Team
 */
public enum Squares implements Filling {
	
	YELLOW("🟨"),
	BROWN("🟫"),
	RED("🟥"),
	BLACK("⬛"),
	ORANGE("🟧"),
	GREEN("🟩"),
	PURPLE("🟪");
	
	private String rep;
	
    /**
     * Constructs a Squares enum with the specified emoji representation.
     *
     * @param s - The emoji representation of a square.
     */
	Squares(String s) {
		this.rep = s;
	}

    /**
     * @return An array containing all the Squares enum values.
     */
	public Squares[] fillings() {
		return Squares.values();
	}
	
	// toString
	
    /**
     * @return The emoji representation of a square.
     */
	public String toString() {
		return this.rep;
	}

}
