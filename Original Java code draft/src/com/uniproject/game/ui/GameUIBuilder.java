package com.uniproject.game.ui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

/**
 * Creates the game for the UI
 * @author tahmoor
 *
 */
public class GameUIBuilder
{
	public GameUI buildGameUI(IGameModel gameModel)
	{
		final JFrame frame = new JFrame("Connect Games");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final GameUI gameUI = new GameUI(frame, gameModel);
		frame.setJMenuBar(new MenuBarBuilder().build(frame, gameUI, gameModel));
		final GridBagLayout manager = new GridBagLayout();
		frame.setLayout(manager);
		return gameUI;
	}
}
