package entities;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import graphics.SpriteSheet;
import objects.BasicEnemy;
import objects.Player;
import states.PlayState;
import tiles.FloorTile;
import tiles.PointTile;
import tiles.Tile;

public class Map
{	
	private Handler handler;
	private Player player;
	private SpriteSheet sheet;
	private LinkedList<Tile> tiles;
	private int count = 0;
	private int mapWidth, mapHeight;
	private int level;
	private PlayState playState;
	
	public Map(int level, Player player, SpriteSheet sheet, PlayState playState)
	{
		this.sheet = sheet;
		this.level = level;
		this.playState = playState;
		tiles = new LinkedList<Tile>();
		String path = "res/Level";
		if(this.level == 1) path += "1";
		else if(this.level == 2) path += "2"; 
		else if(this.level == 3) path += "3";
		
		path += ".txt";
		handler = new Handler();
		this.player = player;
		loadMap(path);
	}
	
	private void loadMap(String path)
	{
		StringBuilder sb = new StringBuilder();
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = br.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			
			br.close();
			
		} catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("Could not load the map file: " + path);
		}
		
		String contents = sb.toString();
		String[] tokens = contents.split("\\s+");
		
		mapWidth = Integer.parseInt(tokens[0]);
		mapHeight = Integer.parseInt(tokens[1]);
		
		System.out.println(mapWidth + " " + mapHeight);
		
		for(int y = 0; y < mapHeight; y++)
		{
			for(int x = 0; x < mapWidth; x++)
			{
				if(Integer.parseInt(tokens[x + y * mapWidth + 2]) == 1)
					tiles.add(new FloorTile(x * Entity.SIZE, y * Entity.SIZE, Entity.SIZE, Entity.SIZE, sheet, true, false));
				else if(Integer.parseInt(tokens[x + y * mapWidth + 2]) == 2)
					tiles.add(new FloorTile(x * Entity.SIZE, y * Entity.SIZE, Entity.SIZE, Entity.SIZE, sheet, false, false));
				else if(Integer.parseInt(tokens[x + y * mapWidth + 2]) == 3)
					tiles.add(new PointTile(x * Entity.SIZE, y * Entity.SIZE, Entity.SIZE, Entity.SIZE, sheet, true));
				else if(Integer.parseInt(tokens[x + y * mapWidth + 2]) == 4)
					tiles.add(new PointTile(x * Entity.SIZE, y * Entity.SIZE, Entity.SIZE, Entity.SIZE, sheet, false));
				else if(Integer.parseInt(tokens[x + y * mapWidth + 2]) == 5)
					handler.addEntity(new BasicEnemy(x * Entity.SIZE, y * Entity.SIZE, Entity.SIZE, Entity.SIZE, sheet, player));
				else if(Integer.parseInt(tokens[x + y * mapWidth + 2]) == 6)
					tiles.add(new FloorTile(x * Entity.SIZE, y * Entity.SIZE, Entity.SIZE, Entity.SIZE, sheet, false, true));
				else if(Integer.parseInt(tokens[x + y * mapWidth + 2]) == -1)
				{
					player.setX(x * Entity.SIZE);
					player.setY(y * Entity.SIZE);
					player.setStartX(player.getX());
					player.setStartY(player.getY());
				}
			}
		}
	}
	
	public void init()
	{	
		for(Tile t: tiles)
		{
			handler.addEntity(t);
		}
		
		handler.addEntity(player);
		
		handler.init();
	}
	
	public void tick()
	{
		handler.tick();
		collision();
	}
	
	public void render(Graphics g)
	{
		handler.render(g);
	}
	
	private void collision()
	{
		count = 0;
		for(Tile t: tiles)
		{
			count++;
			if(t.getBounds().intersects(player.getBounds()))
			{
				if(t.isSolid())
				{
					if(t.getTop().intersects(player.getBottom()))
					{
						player.setY(t.getY() - player.getHeight());
						player.setFalling(false);
					}
					if(t.getLeft().intersects(player.getRight()))
					{
						player.setX(t.getX() - player.getWidth());
					}
					if(t.getRight().intersects(player.getLeft()))
					{
						player.setX(t.getX() + t.getWidth());
					}
					if(t.getBottom().intersects(player.getTop()))
					{
						player.setY(t.getY() + t.getHeight());
					}
				}
				else if(t.getID() == "GoalTile")
				{
					playState.changeLevel();
				}
				else if(t.getID() == "CoinTile" && ((PointTile) t).isActive())
				{
					player.setNumCoins(player.getNumCoins() + 1);
					((PointTile) t).setActive(false);
				}
				else if(t.getID() == "SpikeTile")
				{
					player.setLives(player.getLives() - 1);
					player.setX(player.getStartX());
					player.setY(player.getStartY());
				}
				count--;
			}
			
			if(count == tiles.size() && !player.isJumping())
			{
				player.setFalling(true);
			}
		}
	}
}
