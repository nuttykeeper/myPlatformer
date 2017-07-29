package objects;

import entities.Entity;
import graphics.SpriteSheet;

public abstract class Object extends Entity
{
	protected int health;
	protected int velX, velY;
	
	public Object(int x, int y, int width, int height, SpriteSheet sheet)
	{
		super(x, y, width, height, sheet);
	}
	
	public int getHealth() { return health; }
	public int getVelX() { return velX; }
	public int getVelY() { return velY; }
	
	public void setHealth(int health) { this.health = health; }
	public void setVelX(int velX) { this.velX = velX; }
	public void setVelY(int velY) { this.velY = velY; }
}
