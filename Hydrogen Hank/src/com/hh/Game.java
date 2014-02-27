package com.hh;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import com.hh.framework.*;
import com.hh.graphics.ArtAssets;
import com.hh.keyboard.KeyBinding;
import com.hh.keyboard.KeyInput;
import com.hh.mouse.MouseInput;
import com.hh.states.*;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014 Last Updated : Feb. 11, 2014 Purpose: Create and run
 * the game thread for the 'Dust Bunny Mayhem' game
 * 
 * @author Mark Schlottke
 */
public class Game extends Canvas implements Runnable
{
  private static final long serialVersionUID = 5679202415177178318L;

  public static GameStateManager manager;
  public static PlayState playState;
  public static int WIDTH, HEIGHT, HEIGHTOFFSET;
  private static ArtAssets artassets;
  public static Window window;

  public boolean running = false;
  public Thread thread;

  private static BufferStrategy bs;

  /**
   * Initializes the game
   */
  private void init()
  {
    manager = new GameStateManager();
    playState = new PlayState();
    WIDTH = getWidth();
    HEIGHT = getHeight();
    HEIGHTOFFSET = 50;
    this.createBufferStrategy(3);
    bs = this.getBufferStrategy();
    artassets = new ArtAssets();

    KeyBinding.LOAD_BINDINGS();
    playState.restart();
    manager.STATES.push(playState);
    manager.STATES.push(new TitleMenuState());
    manager.STATES.push(new TitleMenuAnimState());
    this.addKeyListener(new KeyInput());
    this.addMouseListener(new MouseInput());
  }

  /**
   * Starts the game thread
   */
  public synchronized void start()
  {
    if (!running)
    {
      running = true;
      thread = new Thread(this);
      thread.start();
    }
  }

  /**
   * Primary routine for the game
   */
  public void run()
  {
    init();
    this.requestFocus();

    GameTime.start();
    while (running)
    {

      GameTime.update();
      tick();
      render();

      try
      {
        Thread.sleep(10);
      } catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * Advances the gameobjects
   */
  private void tick()
  {
    manager.tick();
  }

  /**
   * Renders the graphics for the game
   */
  private void render()
  {
    Graphics g = bs.getDrawGraphics();
    manager.render(g);
    g.dispose();
    bs.show();
  }

  /**
   * Toggles the game between the 'Running' and 'Paused' states
   */
  public static void togglePause()
  {
    GameState first = manager.STATES.getFirst();

    if (first.getClass() == TitleMenuState.class || first.getClass() == PauseState.class
        || first.getClass() == TitleMenuAnimState.class)
    {
      manager.STATES.pop();
    } else if (manager.STATES.getFirst().getClass() == PlayState.class)
    {
      manager.STATES.push(new PauseState());
    }
  }

  /**
   * Used to call the restart routine Only callable if the game is in the
   * 'Paused' state
   */
  public static void doRestart()
  {
    if (manager.STATES.getFirst().getClass() == PauseState.class)
    {
      playState.restart();
      manager.STATES.pop();
      manager.STATES.push(new TitleMenuState());
      manager.STATES.push(new TitleMenuAnimState());
    }
  }
  /**
   * getArtAssets
   * @return returns instance of ArtAssets
   */
  public static ArtAssets getArtAssets(){return artassets;}

  
  //TODO: What. 
  public static Point getPosition()
  {
    return window.getPosition();
  }

  /**
   * Main entry point for the program
   * 
   * @param args
   */
  public static void main(String args[])
  {
    window = new Window(800, 600, "Hydrogen Hank", new Game());
  }
}
