package graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet 
{
	public static final int IMAGE_SIZE = 32;
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	
	public BufferedImage cropImage(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x * IMAGE_SIZE, y * IMAGE_SIZE, width, height);
	}
}
