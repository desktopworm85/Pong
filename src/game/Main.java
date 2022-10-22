package game;

import javax.swing.*;

public class Main 
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame();
		//JLabel score = new JLabel("test");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setName("Pong");
		//score.setBounds(50,50,160,20);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}
