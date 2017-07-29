package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import graphics.Camera;
import graphics.SpriteSheet;

public class Player extends Object 
{
	public static final int SPEED = 3;
	public static final int GRAVITY = 1;
	public static final int MAX_SPEED = 5;
	
	private boolean falling = false;
	private boolean jumping = false;
	private int timer = 0;
	private int numCoins;
	private int lives = 3;
	private int startX, startY;
	private Camera cam;
	
	private BufferedImage lifeImage;
	private BufferedImage noLifeImage;
	
	public Player(int x, int y, int width, int height, SpriteSheet sheet)
	{
		super(x, y, width, height, sheet);
		startX = x;
		startY = y;
		numCoins = 0;
		drawPriority = 1;
	}
	
	public void init()
	{
		id = "Player";
		texture = sheet.cropImage(0, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
		lifeImage = sheet.cropImage(6, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
		noLifeImage = sheet.cropImage(7, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		if(velY >= MAX_SPEED)
			velY = MAX_SPEED;
		else if(velY <= -MAX_SPEED)
			velY = -MAX_SPEED;
			
		if(falling)
		{
			velY += GRAVITY;
		}
		
		if(jumping)
		{
			velY -= GRAVITY;
			timer++;
			
			if(timer == 15)
			{
				timer = 0;
				jumping = false;
				falling = true;
			}
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(texture, x, y, width, height, null);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(cam.getX(), cam.getY());
		
		g2d.setColor(Color.WHITE);
		g2d.drawImage(sheet.cropImage(4, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE), 550, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
		g2d.drawString(String.valueOf(numCoins), 600, 50);
		
		g2d.drawString("Lives: ", 20, 50);
		if(lives == 3)
		{
			g2d.drawImage(lifeImage, 110, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
			g2d.drawImage(lifeImage, 145, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
			g2d.drawImage(lifeImage, 180, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
		}
		else if(lives == 2)
		{
			g2d.drawImage(lifeImage, 110, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
			g2d.drawImage(lifeImage, 145, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
			g2d.drawImage(noLifeImage, 180, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
		}
		else
		{
			g2d.drawImage(lifeImage, 110, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
			g2d.drawImage(noLifeImage, 145, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
			g2d.drawImage(noLifeImage, 180, 20, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE, null);
		}
		
		g2d.translate(-cam.getX(), -cam.getY());
	}
	
	public boolean isFalling() { return falling; }
	public boolean isJumping() { return jumping; }
	public int getNumCoins() { return numCoins; }
	public int getLives() { return lives; }
	public int getStartX() { return startX; }
	public int getStartY() { return startY; }
	
	public void setFalling(boolean falling) { this.falling = falling; }
	public void setJumping(boolean jumping) { this.jumping = jumping; }
	public void setNumCoins(int numCoins) { this.numCoins = numCoins; }
	public void setCam(Camera cam) { this.cam = cam; }
	public void setLives(int lives) { this.lives = lives; }
	public void setStartX(int startX) { this.startX = startX; }
	public void setStartY(int startY) { this.startY = startY; }
}
