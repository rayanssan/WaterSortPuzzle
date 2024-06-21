package types;

/**
 * An implementation of the types.Symbol interface.
 * 
 * @author PCO Team
 */
public enum Emojis implements Filling {
	
	SMILE("😃"),
	SAD("😒"),
	FURIOUS("😡"),
	ANGEL("😇"),
	BLIINK("😉"),
	EVIL("😈"),
	SUN("😎"),
	LOVE("😍");
	
    /**
     * Constructs an Emojis enum with the specified emoji representation.
     *
     * @param s - An emoji representation.
     */
	private String rep;
	Emojis(String s) {
		this.rep = s;
	}

    /**
     * @return An array containing all the Emojis enum values.
     */
	public Emojis[] fillings() {
		return Emojis.values();
	}
	
	// toString
	
    /**
     * @return An emoji representation.
     */
	public String toString() {
		return this.rep;
	}
	
}
