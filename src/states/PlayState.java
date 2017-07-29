package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entities.Entity;
import entities.Map;
import graphics.Camera;
import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.Game;
import objects.Player;

public class PlayState extends State
{
	private Player player;
	private Map map;
	private SpriteSheet sheet;
	private Camera cam;
	private int level = 1;
	
	public PlayState(Game game)
	{
		super(game);
	}
	
	public void init()
	{
		if(level == 1)
		{
			sheet = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet.png"));
			player = new Player(100, 100, Entity.SIZE, Entity.SIZE, sheet);
		}
		else
		{
			player.setX(100);
			player.setY(100);
		}
		map = new Map(level, player, sheet, this);
		cam = new Camera(0, 0, player);
		player.setCam(cam);
		
		map.init();
	}
	
	public void tick()
	{
		map.tick();
		cam.update();
		
		if(player.getLives() <= 0)
		{
			GameOverState gameOver = new GameOverState(game, this);
			game.removeKeyListener(this);
			State.changeState(gameOver);
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(new Color(135, 206, 250));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.translate(-cam.getX(), -cam.getY());
		map.render(g);
		g2d.translate(cam.getX(), cam.getY());
	}
	
	public void reset()
	{
		level = 0;
		player.setNumCoins(0);
		changeLevel();
	}
	
	public void changeLevel()
	{
		level++;
		ChangeLevelState state = new ChangeLevelState(game, level, this);
		State.changeState(state);
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) player.setVelX(-Player.SPEED);
		else if(key == KeyEvent.VK_D) player.setVelX(Player.SPEED);
		
		if(key == KeyEvent.VK_SPACE && !player.isJumping()) player.setJumping(true);
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) player.setVelX(0);
		else if(key == KeyEvent.VK_D) player.setVelX(0);
	}
	
	public int getLevel() { return level; }
	
	public void setLevel(int level) { this.level = level; }
}
