package com.uniproject.game.main;

import com.uniproject.game.model.GameModel;
import com.uniproject.game.ui.GameUIBuilder;
import com.uniproject.game.ui.IGameModel;

public class Main 
{
	/**
	 * Main function to start the game up
	 * @param args
	 */
	public static void main(String[] args) 
	{
		final IGameModel gameModel = GameModel.getInstance();
		gameModel.onNewConnectFiveGame();
		new GameUIBuilder().buildGameUI(gameModel).display();
	}
}
