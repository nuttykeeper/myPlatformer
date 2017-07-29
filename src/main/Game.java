package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import states.MenuState;
import states.State;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Platformer";
	
	private boolean running = false;
	private Thread thread;
	
	private State startState;
	private Font font;
	
	public Game()
	{
		Dimension d = new Dimension(WIDTH, HEIGHT);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
	}
	
	public void init()
	{
		requestFocus();
		
		startState = new MenuState(this);
		State.changeState(startState);
		
		font = null;
		File file = new File("res/Riffic.ttf");
		try 
		{
			font = Font.createFont(Font.TRUETYPE_FONT, file);
		} catch (FontFormatException | IOException e) 
		{
			System.err.println("Could not load imported font");
			e.printStackTrace();
		}
		
		font = font.deriveFont(32.0f);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
	}
	
	public void tick()
	{
		State.currentState.tick();
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setFont(font);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		State.currentState.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	public synchronized void start()
	{
		if(running) return;
		
		thread = new Thread(this);
		running = true;
		
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		
		running = false;
		
		try 
		{
			thread.join();
		} catch (InterruptedException e) 
		{
			System.err.println("Could not close thread.");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame(TITLE);
		Game game = new Game();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(100, 50);
		frame.add(game);
		frame.pack();
		
		game.start();
	}
}
