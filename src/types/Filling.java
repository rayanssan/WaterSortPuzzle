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

