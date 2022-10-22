package game;

import java.awt.event.*;

public class KeyHandler implements KeyListener
{
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	public boolean upPressed, downPressed;
	
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = true;
		}
		if(code == KeyEvent.VK_S)
		{
			downPressed = true;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		if(code == KeyEvent.VK_S)
		{
			downPressed = false;
		}
	}
}
