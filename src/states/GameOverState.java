package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;

public class GameOverState extends State
{
	private int menuPosition = 1;
	private PlayState playState;
	
	public GameOverState(Game game, PlayState playState)
	{
		super(game);
		this.playState = playState;
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
		g.drawString("Game Over", Game.WIDTH / 2 - 90, 100);
		g.drawString("Resume from last Checkpoint?", 100 ,200);
		setColour(1, g);
		g.drawString("Yes", 150, 300);
		setColour(2, g);
		g.drawString("No", 450, 300);
	}
	
	private void setColour(int position, Graphics g)
	{
		if(position == menuPosition) g.setColor(Color.GREEN);
		else g.setColor(Color.WHITE);
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT && menuPosition == 2) menuPosition = 1;
		else if(key == KeyEvent.VK_RIGHT && menuPosition == 1) menuPosition = 2;
		
		if(key == KeyEvent.VK_ENTER)
		{
			if(menuPosition == 1) 
			{
				playState.reset();
			}
		}
	}
}
