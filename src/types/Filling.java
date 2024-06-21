package types;

/**
 * Represents a type of filling that can be used in a bottle.
 */
public interface Filling {

    /**
     * Retrieves an array of possible fillings that can be used.
     *
     * @return An array of possible fillings.
     */
    Filling[] fillings();
    
}

