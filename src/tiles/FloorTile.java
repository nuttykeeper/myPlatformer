package tiles;

import java.awt.Graphics;

import graphics.SpriteSheet;

public class FloorTile extends Tile
{
	private boolean grass;
	private boolean spikes;
	
	public FloorTile(int x, int y, int width, int height, SpriteSheet sheet, boolean grass, boolean spikes)
	{
		super(x, y, width, height, sheet);
		this.grass = grass;
		this.spikes = spikes;
	}
	
	public void init()
	{
		if(!spikes)
			id = "FloorTile";
		else
		{
			id = "SpikeTile";
			solid = false;
		}
		drawPriority = 1;
		if(grass)
			texture = sheet.cropImage(1, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
		else if(spikes)
			texture = sheet.cropImage(0, 1, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
		else
			texture = sheet.cropImage(2, 0, SpriteSheet.IMAGE_SIZE, SpriteSheet.IMAGE_SIZE);
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(texture, x, y, width, height, null);
	}
}
