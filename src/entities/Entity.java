package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.SpriteSheet;

public abstract class Entity
{
	public static final int BOUND_SIZE = 5;
	public static final int SIZE = 32;
	
	protected int x, y;
	protected int width, height;
	protected BufferedImage texture;
	protected SpriteSheet sheet;
	protected String id;
	protected int drawPriority;
	
	public Entity(int x, int y, int width, int height, SpriteSheet sheet)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public Rectangle getBounds() { return new Rectangle(x, y, width, height); }
	public Rectangle getLeft() { return new Rectangle(x, y, BOUND_SIZE, height); }
	public Rectangle getRight() { return new Rectangle(x + width - BOUND_SIZE, y, BOUND_SIZE, height); }
	public Rectangle getTop() { return new Rectangle(x, y, width, BOUND_SIZE); }
	public Rectangle getBottom() { return new Rectangle(x, y + height - BOUND_SIZE, width, BOUND_SIZE); }
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public BufferedImage getTexture() { return texture; }
	public String getID() { return id; }
	public int getDrawPriority() { return drawPriority; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setTexture(BufferedImage texture) { this.texture = texture; }
	public void setID(String id) { this.id = id; }
	public void setDrawPriority(int drawPriority) { this.drawPriority = drawPriority; }
}
