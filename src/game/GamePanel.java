package game;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable
{
	final int tileSize = 32;
	
	final int maxScreenCol = 48;
	final int maxScreenRow = 30;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	KeyHandler keyH = new KeyHandler();
	Ball ball = new Ball();
	Thread gameThread;
	
	int playerX = 0;
	int playerY = 430;
	int playerSpeed = 8;
	int score = 0;
	boolean scoreCountable = true;
	int framesSinceHit = 0;
	boolean GameOver = false;
	int FPS = 120;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() 
	{
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null)
		{
			update();
			repaint();
			
			try 
			{
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if(remainingTime < 0)
				{
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			framesSinceHit += 1;
			if (framesSinceHit == FPS) {scoreCountable=true;}
		}
	}
	
	public void update()
	{
		if (keyH.upPressed == true) {playerY -= playerSpeed;}
		else if (keyH.downPressed == true) {playerY += playerSpeed;}
		
		if (playerY < 0) {playerY = 0;}
		else if (playerY+100 > screenHeight) {playerY = screenHeight - 100;}
		
		int ballx = ball.ballX;
		int ballY = ball.ballY;
		int ballDia = ball.ballDia;
		
		
		if (ballx < -25) {GameOver = true; scoreCountable=false;}
		
		if (ballx <= 10 && ballY <= playerY + 100 && ballY + ballDia >= playerY)
		{
			ball.Collision();
			if (scoreCountable) {score+=1; scoreCountable=false; framesSinceHit=0; if(score%5==0) {ball.ballSpeed+=0.1;}}
		}
		
		if (GameOver == false) {ball.Move();}
		ball.WallHit();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, 10, 100);
		g2.fillOval(ball.ballX, ball.ballY, ball.ballDia, ball.ballDia);
		g2.setFont(new Font("calibri", Font.PLAIN, 30));
		g2.drawString("Score: " + score, 1400, 50);
		if(GameOver) {g2.setFont(new Font("calibri", Font.PLAIN, 80));g2.drawString("Game Over", 550, 400);}
		g2.dispose();
	}
}