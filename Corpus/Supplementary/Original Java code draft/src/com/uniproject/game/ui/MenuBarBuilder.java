package com.uniproject.game.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Makes the menu which will let you select the type of game you want 
 * @author tahmoor
 *
 */
public class MenuBarBuilder 
{
	/**
	 * Builds the menu bar.
	 * @param owner The owning frame.
	 * @param gameUI 
	 * @param model The {@link IGameModel}.
	 * @return See above.
	 */
	public JMenuBar build(JFrame owner, GameUI gameUI, IGameModel model)
	{
		final JMenuBar menuBar = new JMenuBar();
		
		final JMenu gameMenu = addGameMenu(menuBar);
		addNewConnectFiveGameMenuItemToGameMenu(owner, model, gameMenu, gameUI);
		addNewOXOGameMenuItemToGameMenu(owner, model, gameMenu, gameUI);
		addExitMenuItemToGameMenu(model, gameMenu);
		
		final JMenu helpMenu = addHelpMenu(menuBar);
		addAboutMenuItemToHelpMenu(owner, model, helpMenu);
		
		return menuBar;
	}

	private JMenu addGameMenu(JMenuBar menuBar) 
	{
		final JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		gameMenu.getAccessibleContext().setAccessibleDescription("The game menu");
		menuBar.add(gameMenu);
		return gameMenu;
	}

	private void addNewConnectFiveGameMenuItemToGameMenu(JFrame owner, IGameModel model, JMenu gameMenu, GameUI gameUI) 
	{
		final JMenuItem newGameMenuItem = new JMenuItem("New Gomoku Game", KeyEvent.VK_F);
		gameMenu.add(newGameMenuItem);
		newGameMenuItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new SelectPlayerTypeDialog(owner, model).setVisible(true);
				model.onNewConnectFiveGame();
				gameUI.display();
			}
		});
	}
	
	private void addNewOXOGameMenuItemToGameMenu(JFrame owner, IGameModel model, JMenu gameMenu, GameUI gameUI) 
	{
		final JMenuItem newGameMenuItem = new JMenuItem("New TicTacToe Game", KeyEvent.VK_O);
		gameMenu.add(newGameMenuItem);
		newGameMenuItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new SelectPlayerTypeDialog(owner, model).setVisible(true);
				model.onNewOXOGame();
				gameUI.display();
			}
		});
	}

	private void addExitMenuItemToGameMenu(IGameModel model, JMenu gameMenu) 
	{
		final JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		gameMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.onExitGame();
			}
		});
	}
	
	private JMenu addHelpMenu(JMenuBar menuBar) 
	{
		final JMenu gameMenu = new JMenu("Help");
		gameMenu.setMnemonic(KeyEvent.VK_H);
		gameMenu.getAccessibleContext().setAccessibleDescription("The help menu");
		menuBar.add(gameMenu);
		return gameMenu;
	}
	
	private void addAboutMenuItemToHelpMenu(JFrame owner, IGameModel model, JMenu helpMenu) 
	{
		final JMenuItem aboutMenuItem = new JMenuItem("About...");
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AboutDialog(owner, model).setVisible(true);
			}
		});
	}
	
	/**
	 * The Help > About dialog.
	 */
	private final class AboutDialog extends JDialog
	{
	   public AboutDialog(JFrame owner, IGameModel model)
	   {
	      super(owner, "About", true);
	      final JTextArea textArea = new JTextArea(model.getHelpText());
	      textArea.setEditable(false);
	      add(textArea, BorderLayout.CENTER);
	      final JButton ok = new JButton("Ok");
	      ok.addActionListener(new ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	               setVisible(false);
	            }
	         });
	      final JPanel panel = new JPanel();
	      panel.add(ok);
	      add(panel, BorderLayout.SOUTH);
	      setLocationRelativeTo(owner);
	      setSize(400, 150);
	   }
	}
	
	private static final String COMPUTER = "Computer";
	private static final String HUMAN = "Human";
	private static final String[] PLAYER_TYPE = {HUMAN, COMPUTER};
	
	/**
	 * Dialog to get the player types.
	 */
	private final class SelectPlayerTypeDialog extends JDialog
	{
		   public SelectPlayerTypeDialog(JFrame owner, IGameModel model)
		   {
		      super(owner, "About", true);
		      setLayout(new GridLayout(3, 2));
		      add(new JLabel("Player X Type"));
		      final JComboBox<String> playerType1 = new JComboBox<String>(PLAYER_TYPE);
		      add(playerType1);
		      
		      add(new JLabel("Player O Type"));
		      final JComboBox<String> playerType2 = new JComboBox<String>(PLAYER_TYPE);
		      add(playerType2);
		      
		      final JButton ok = new JButton("Ok");
		      ok.addActionListener(new ActionListener() 
		      {
				public void actionPerformed(ActionEvent event) 
				{
					setPlayerXType(model, playerType1);
					setPlayerOType(model, playerType2);
					setVisible(false);
				}

				private void setPlayerXType(IGameModel model, JComboBox<String> playerType1) 
				{
					final String player1Type = (String)playerType1.getSelectedItem();
					switch(player1Type)
					{
						case COMPUTER:
							model.setPlayerXToComputer();
						break;
						case HUMAN:
							model.setPlayerXToHuman();
						break;
						default:
							throw new IllegalStateException();
					}
				}

				private void setPlayerOType(IGameModel model, JComboBox<String> playerType2) 
				{
					final String player2Type = (String)playerType2.getSelectedItem();
					switch(player2Type)
					{
						case COMPUTER:
							model.setPlayerOToComputer();
						break;
						case HUMAN:
							model.setPlayerOToHuman();
						break;
						default:
							throw new IllegalStateException();
					}
				}
				
		      });
		      final JPanel panel = new JPanel();
		      panel.add(ok);
		      add(panel);
		      setLocationRelativeTo(owner);
		      setSize(400, 150);
		   }
	}
}
