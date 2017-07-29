package graphics;

import main.Game;
import objects.Player;

public class Camera 
{
	private int x, y;
	private Player player;
	
	public Camera(int x, int y, Player player)
	{
		this.x = x;
		this.y = y;
		this.player = player;
	}
	
	public void update()
	{
		x = player.getX() + (player.getWidth() / 2) - (Game.WIDTH / 2);
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
}
