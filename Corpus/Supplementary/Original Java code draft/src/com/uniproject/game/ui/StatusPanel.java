package com.uniproject.game.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel implements IStatusPanel
{
	private JLabel label;
	private JFrame owner;
	private IGameModel model;

	public StatusPanel(JFrame owner, IGameModel model)
	{
		this.owner = owner;
		this.model = model;
		model.setStatusPanel(this);
		label = new JLabel("");
		add(label, BorderLayout.CENTER);
		setPlayerTurn(model.getCurrentPlayer());
	}
	
	@Override
	public void setPlayerTurn(String player)
	{
		label.setText("Current Player: " + player);
	}
	
	@Override
	public void setWinner(String player)
	{
		new GameEndDialog(owner, model, "Winning Player: " + player).setVisible(true);
	}
	
	@Override
	public void setGameEndNoWinner()
	{
		new GameEndDialog(owner, model, "Game Over: Draw").setVisible(true);
	}
	
	private final class GameEndDialog extends JDialog
	{
	   public GameEndDialog(JFrame owner, IGameModel model, String message)
	   {
	      super(owner, "Game End", true);
	      add(new JLabel(message), BorderLayout.CENTER);
	      final JButton ok = new JButton("Ok");
	      ok.addActionListener(new ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	               // TODO need to reset the board here back to beginning
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
}
