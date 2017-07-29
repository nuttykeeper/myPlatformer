package tiles;

import entities.Entity;
import graphics.SpriteSheet;

public abstract class Tile extends Entity
{
	protected boolean solid = true;
	
	public Tile(int x, int y, int width, int height, SpriteSheet sheet)
	{
		super(x, y, width, height, sheet);
	}
	
	public boolean isSolid() { return solid; }
	
	public void setSolid(boolean solid) { this.solid = solid; }
}
