/**
 * Programação Centrada em Objetos (26706) S1 (2023/24)
 * Projeto
 * Grupo 01
 * 60282 - Rayan Serafim Santana
 * 60471 - Guilherme Bastos
 * 60858 - Diogo Piçarra
 */

package types;

/**
 * An implementation of the types.Symbol interface.
 * 
 * @author PCO Team
 */
public enum Balls implements Filling {
	
	BALL1("①"),
	BALL2("②"), 
	BALL3("③"), 
	BALL4("④"), 
	BALL5("⑤"), 
	BALL6("⑥"),
	BALL7("⑦"),
	BALL8("⑧"),
	BALL9("⑨");
	
	private String rep;
	
    /**
     * Constructs a Balls enum with the specified emoji representation.
     *
     * @param s - The emoji representation of a ball.
     */
	Balls(String s) {
		this.rep = s;
	}

    /**
     * @return An array containing all the Balls enum values.
     */
	public Balls[] fillings() {
		return Balls.values();
	}
	
	// toString
	
    /**
     * @return The emoji representation of a ball.
     */
	public String toString() {
		return this.rep;
	}

}
