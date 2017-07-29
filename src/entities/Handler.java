package entities;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler 
{
	private LinkedList<Entity> entities;
	private LinkedList<Entity> priority1;
	private LinkedList<Entity> priority2;
	
	public Handler()
	{
		entities = new LinkedList<Entity>();
		priority1 = new LinkedList<Entity>();
		priority2 = new LinkedList<Entity>();
	}
	
	public void init()
	{
		for(Entity e: entities)
		{
			e.init();
			if(e.getDrawPriority() == 1) priority1.add(e);
			else if(e.getDrawPriority() == 2) priority2.add(e);
		}
	}
	
	public void tick()
	{
		for(Entity e: entities)
		{
			e.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(Entity e: priority2)
		{
			e.render(g);
		}
		
		for(Entity e: priority1)
		{
			e.render(g);
		}
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	public void removeEntity(Entity e)
	{
		entities.remove(e);
	}
}
