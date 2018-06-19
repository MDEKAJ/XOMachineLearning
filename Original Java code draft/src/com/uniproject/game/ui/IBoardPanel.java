package com.uniproject.game.ui;

/**
 * Controls the board.
 */
public interface IBoardPanel 
{
	/**
	 * Makes a move on the board and updates the state of the game.
	 * 
	 * @param row
	 *            The move row.
	 * @param column
	 *            The move column.
	 */
	void makeMove(int row, int column);
}
