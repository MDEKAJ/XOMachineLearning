package com.uniproject.game.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Board element UI, used to deal with anything related to the baord so making the board and adding the pieces 
 * @author tahmoor
 *
 */
public class BoardPanel extends JPanel implements IBoardPanel
{
	private final IGameModel model;
	private final JButton[][] boardButtons;
	private final Map<String, Color> buttonColors = new HashMap<String, Color>();
	
	public BoardPanel(IGameModel model)
	{
		this.model = model;
		buttonColors.put(model.getPlayerO(), Color.RED);
		buttonColors.put(model.getPlayerX(), Color.GREEN);
		model.setBoardPanel(this);
		
		final int rows = model.getBoardRowCount();
		final int columns = model.getBoardColumnCount();
		setLayout(new GridLayout(rows, columns));
		boardButtons = new JButton[rows][columns];
		for (int i = 0; i < rows; i++) 
		{
			for (int j = 0; j < columns; j++) 
			{
				final JButton button = new JButton("    ");
				boardButtons[i][j] = button;
				add(button);
				button.addActionListener(new PlayerMoveActionListener(i, j));
			}
		}
	}
	
	@Override
	public void makeMove(int row, int column)
	{
		final String currentPlayer = model.getCurrentPlayer();
		model.setCurrentPlayerMove(row, column);
		updateSelectedButton(row, column, currentPlayer);
		model.currentPlayerFinishedMove();
	}

	private void updateSelectedButton(int row, int column, String currentPlayer) 
	{
		final JButton selectedButton = boardButtons[row][column];
		selectedButton.setText(currentPlayer);
		selectedButton.setEnabled(false);
		final Color currentPlayerButtonColor = buttonColors.get(currentPlayer);
		selectedButton.setBackground(currentPlayerButtonColor);
	}
	
	/**
	 * Updates the selected button with the player's mark, and updates the state of
	 * the game.
	 */
	private final class PlayerMoveActionListener implements ActionListener
	{
		private final int rowIndex;
		private final int columnIndex;
		
		public PlayerMoveActionListener(int rowIndex, int columnIndex) 
		{
			this.rowIndex = rowIndex;
			this.columnIndex = columnIndex;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			makeMove(rowIndex, columnIndex);
		}
	}
}
