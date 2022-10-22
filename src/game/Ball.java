package game;

import java.util.Random;

public class Ball 
{
	Random random = new Random();
	public int ballX = 768;
	public int ballY = random.nextInt(961);
	public int ballDia = 24;
	public float ballSpeed = 6;
	public String ballDirectionX = "right";
	public String ballDirectionY = "up";
	public int ballRad = ballDia/2;
	public int ballCentreX = ballX + ballRad;
	public int ballCentreY = ballY + ballRad;
	
	public void Move()
	{
		if (ballDirectionX == "right") {ballX += ballSpeed;}
		else if (ballDirectionX == "left") {ballX -= ballSpeed;}
		
		if (ballDirectionY == "down") {ballY += ballSpeed;}
		else if (ballDirectionY == "up") {ballY -= ballSpeed;}
	}
	
	public void WallHit()
	{
		if (ballY <= 0) {ballY = 0; ballDirectionY = "down";}
		else if (ballY+ballDia >= 960) {ballY = 960-ballDia; ballDirectionY = "up";}
		
		if (ballX+ballDia >= 1536) {ballX = 1536-ballDia; ballDirectionX = "left";}
	}
	
	public void Collision()
	{
		ballDirectionX = "right";
	}
}