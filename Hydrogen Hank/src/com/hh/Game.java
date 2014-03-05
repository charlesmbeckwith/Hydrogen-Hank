package com.hh;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import com.hh.framework.*;
import com.hh.framework.gamestate.*;
import com.hh.framework.gamestate.states.*;
import com.hh.graphics.ArtAssets;
import com.hh.input.KeyBinding;
import com.hh.input.KeyInput;
import com.hh.input.MouseInput;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014 Last Updated : Feb. 11, 2014 Purpose: Create and run
 * the game thread for the 'Dust Bunny Mayhem' game
 * 
 * @author Mark Schlottke
 */
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 5679202415177178318L;

	public static GameStateManager manager;
	public static PlayState playState;
	public static int WIDTH, HEIGHT, HEIGHTOFFSET;
	private static ArtAssets artassets;
	public static Window window;
	public static boolean debugOn = true;
	public boolean running = false;
	public Thread thread;

	private static BufferStrategy bs;

	/**
	 * Initializes the game
	 */
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		HEIGHTOFFSET = 50;
		createBufferStrategy(3);
		bs = getBufferStrategy();
		artassets = new ArtAssets();
		playState = new PlayState();
		manager = new GameStateManager(playState);

		KeyBinding.LOAD_BINDINGS();
		playState.restart();
		manager.push(playState);
		manager.push(new TitleMenuState());
		manager.push(new TitleMenuAnimState());
		manager.push(new IntroAnimation());
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput());
	}

	/**
	 * Starts the game thread
	 */
	public synchronized void start() {
		if (!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Primary routine for the game
	 */
	public void run() {
		init();
		this.requestFocus();

		GameTime.start();
		while (running) {

			GameTime.update();
			tick();
			render();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Advances the gameobjects
	 */
	private void tick() {
		manager.tick();
	}

	/**
	 * Renders the graphics for the game
	 */
	private void render() {
		Graphics g = bs.getDrawGraphics();
		manager.render(g);
		g.dispose();
		bs.show();
	}

	/**
	 * Toggles the game between the 'Running' and 'Paused' states
	 */
	public static void togglePause() {
		if (manager.getFirstClass() == PauseState.class
				|| manager.getFirstClass() == TitleMenuAnimState.class
				|| manager.getFirstClass() == IntroAnimation.class) {
			manager.pop();
		} else if (manager.getFirstClass() == PlayState.class) {
			manager.push(new PauseState());
		}
	}

	/**
	 * Used to call the restart routine Only callable if the game is in the
	 * 'Paused' state
	 */
	public static void doRestart() {
		if (manager.getFirstClass() == PauseState.class) {
			playState.restart();
			manager.pop();
			manager.push(new TitleMenuState());
			manager.push(new TitleMenuAnimState());
		}
	}

	/**
	 * getArtAssets
	 * 
	 * @return returns instance of ArtAssets
	 */
	public static ArtAssets getArtAssets() {
		return artassets;
	}

	// TODO: What.
	public static Point getPosition() {
		return window.getPosition();
	}

	/**
	 * Debug toggle
	 * 
	 * @return if debug is toggled on or off
	 */
	public static boolean isDebug() {
		return debugOn;
	};

	/**
	 * Main entry point for the program
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		window = new Window(800, 600, "Hydrogen Hank", new Game());
	}

}
