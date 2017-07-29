package objects;

import java.awt.Graphics;

import entities.Entity;
import graphics.SpriteSheet;

public class BasicEnemy extends Object
{
	public static final int SPEED = 2;
	
	private Player player;
	private boolean left = true;
	private int destination;
	
	public BasicEnemy(int x, int y, int width, int height, SpriteSheet sheet, Player player)
	{
		super(x, y, width, height, sheet);
		this.player = player;
	}
	
	public void init()
	{
		texture = sheet.cropImage(5, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
		destination = x - (Entity.SIZE * 3);
		drawPriority = 1;
	}
	
	public void tick()
	{
		collision();
		
		if(x == destination) changeDirection();
		
		if(left) velX = -SPEED;
		else velX = SPEED;
		
		x += velX;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(texture, x, y, width, height, null);
	}
	
	private void collision()
	{
		if(getBounds().intersects(player.getBounds()))
		{
			player.setLives(player.getLives() - 1);
			player.setX(player.getStartX());
			player.setY(player.getStartY());
		}
	}
	
	private void changeDirection()
	{
		if(left)
		{
			left = false;
			destination = destination + (Entity.SIZE * 3);
		}
		else 
		{
			left = true;
			destination =  destination - (Entity.SIZE * 3);
		}
	}
}
