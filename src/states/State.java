package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

public abstract class State implements KeyListener
{
	protected Game game;
	
	public State(Game game)
	{
		this.game = game;
		game.addKeyListener(this);
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	///////////////////////////////////////
	
	public static State currentState;
	
	public static void changeState(State state)
	{
		state.init();
		currentState = state;
	}
}
