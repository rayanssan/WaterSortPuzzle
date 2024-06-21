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
 * Defines methods for gameplay, round management, help, and scoring of Water Sort Puzzle.
 */
public interface FillingGame {

	/**
	 * Initiates a move by pouring the contents from the bottle with index x into the bottle with index y.
	 *
	 * @param x - The index of the source bottle.
	 * @param y - The index of the destination bottle.
	 */
	void play(int x, int y);

	/**
	 * Checks if the round has ended, i.e., if all bottles are filled with a single content or empty.
	 *
	 * @return True if the round has ended, false otherwise.
	 */
	boolean isRoundFinished();

	/**
	 * Generates a new table with new bottles.
	 */
	void startNewRound();

	/**
	 * Provides a hint to the player by creating a new bottle or its sub-type, depending on the ongoing game.
	 */
	void provideHelp();

	/**
	 * Retrieves the content at the top of the bottle with index x.
	 *
	 * @param x - The index of the bottle.
	 */
	Filling top(int x);

	/**
	 * Checks if the bottle with index x has only one content.
	 *
	 * @param x - The index of the bottle.
	 * @return True if the bottle has a single content, false otherwise.
	 */
	boolean singleFilling(int x);

	/**
	 * Checks if all bottles are filled with the same content or are empty.
	 *
	 * @return True if all bottles are filled with the same content or are empty, false otherwise.
	 */
	boolean areAllFilled();

	/**
	 * Retrieves the game score.
	 *
	 * @return The game score.
	 */
	int score();
	
}

