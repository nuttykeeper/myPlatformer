package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;

public class MenuState extends State
{
	private int menuPosition = 1;
	
	public MenuState(Game game)
	{
		super(game);
	}
	
	public void init()
	{
		
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(new Color(135, 206, 250));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawString("Platformer", Game.WIDTH / 2 - 90, 50);
		setColor(1, g);
		g.drawString("Play", 100, 100);
		setColor(2, g);
		g.drawString("Level Select", 100, 200);
		setColor(3, g);
		g.drawString("Options", 100, 300);
		setColor(4, g);
		g.drawString("Exit", 100, 400);
	}
	
	private void setColor(int position, Graphics g)
	{
		if(menuPosition == position) g.setColor(Color.GREEN);
		else g.setColor(Color.WHITE);
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_DOWN && menuPosition != 4) menuPosition++;
		else if(key == KeyEvent.VK_UP && menuPosition != 1) menuPosition--;
		
		if(key == KeyEvent.VK_ENTER)
		{
			if(menuPosition == 1)
			{
				ChangeLevelState changeLevel = new ChangeLevelState(game, 1, null);
				State.changeState(changeLevel);
			}
		}
	}
}
