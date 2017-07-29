package tiles;

import java.awt.Graphics;

import graphics.SpriteSheet;

public class PointTile extends Tile	
{
	private boolean goal = false;
	private boolean active = true;
	
	public PointTile(int x, int y, int width, int height, SpriteSheet sheet, boolean goal)
	{
		super(x, y, width, height, sheet);
		this.goal = goal;
	}
	
	public void init()
	{
		if(goal)
		{
			id = "GoalTile";
			texture = sheet.cropImage(3, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
		}
		else
		{
			id = "CoinTile";
			texture = sheet.cropImage(4, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
		}
		drawPriority = 2;
		solid = false;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		if(active)
			g.drawImage(texture, x, y, width, height, null);
	}
	
	public boolean isGoal() { return goal; }
	public boolean isActive() { return active; }
	
	public void setGoal(boolean goal) { this.goal = goal; }
	public void setActive(boolean active) { this.active = active; }
}
