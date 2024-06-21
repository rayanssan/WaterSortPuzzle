package types;

/**
 * An implementation of the types.Symbol interface.
 * 
 * @author PCO Team
 */
public enum Animals implements Filling {
	
	DRAGON("🐲"),
	WHALE("🐳"), 
	HORSE("🐴"), 
	MONKEY("🐒"), 
	PIG("🐷"), 
	FROG("🐸"),
	BEE("🐝"),
	ANT("🐜"),
	TURTLE("🐢"),
	SQUIRREL("🐿"),
	DOLPHIN("🐬"),
	FISH("🐠")
	;
	
	private String rep;
	
	/**
     * Constructs an Animals enum with the specified emoji representation.
     *
     * @param s - The emoji representation of an animal.
     */
	Animals(String s) {
		this.rep = s;
	}

	/**
     * @return An array containing all the Animals enum values.
     */
	public Animals[] fillings() {
		return Animals.values();
	}
	
	// toString
	
	/**
     * @return The emoji representation of an animal.
     */
	public String toString() {
		return this.rep;
	}
	
}
