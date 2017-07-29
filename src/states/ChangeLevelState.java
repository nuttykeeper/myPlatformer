package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;

public class ChangeLevelState extends State
{
	private int levelNumber;
	private String levelName;
	private PlayState playState;
	private int menuPosition = 1;
	
	public ChangeLevelState(Game game, int levelNumber, PlayState playState)
	{
		super(game);
		this.levelNumber = levelNumber;
		this.playState = playState;
	}
	
	public void init()
	{
		setupName();
	}
	
	private void setupName()
	{
		if(levelNumber == 1) levelName = "The Begining";
		else if(levelNumber == 2) levelName = "Making Introdutions";
		else if(levelNumber == 3) levelName = "Watch out for Spikes";
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(new Color(135, 206, 250));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawString("Level " + levelNumber, Game.WIDTH / 2 - 32, 100);
		g.drawString(levelName, Game.WIDTH / 2 - levelName.length() * 7, 200);
		
		setColour(1, g);
		g.drawString("Play", 150, 300);
		setColour(2, g);
		g.drawString("Exit", 450, 300);
	}
	
	private void setColour(int position, Graphics g)
	{
		if(menuPosition == position) g.setColor(Color.GREEN);
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
				if(levelNumber == 1)
				{ 
					PlayState newState = new PlayState(game);
					game.removeKeyListener(this);
					State.changeState(newState);
				}
				else
				{
					State.changeState(this.playState);
				}
			}
		}
	}
	
	public int getLevelNumber() { return levelNumber; }
	public String getLevelName() { return levelName; }
	
	public void setLevelNumber(int levelNumber) { this.levelNumber = levelNumber; }
	public void setLevelName(String levelName) { this.levelName = levelName; }
}
