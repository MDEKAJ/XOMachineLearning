package com.uniproject.game.ui;

import java.awt.GridBagConstraints;

import javax.swing.JFrame;

public class GameUI 
{
	private JFrame frame;
	private IGameModel gameModel;

	public GameUI(JFrame frame, IGameModel gameModel) 
	{
		this.frame = frame;
		this.gameModel = gameModel;
	}

	public void display()
	{
		frame.getContentPane().removeAll();
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		final BoardPanel boardPanel = new BoardPanel(gameModel);
		frame.add(boardPanel, c);
		frame.add(new StatusPanel(frame, gameModel), c);
		frame.pack();
		frame.setVisible(true);
	}
}
