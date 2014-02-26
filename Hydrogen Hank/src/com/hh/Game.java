package com.hh;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.hh.framework.*;
import com.hh.graphics.ArtAssets;
import com.hh.keyboard.KeyInput;
import com.hh.objects.*;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 11, 2014
 * Purpose: Create and run the game thread for the 'Dust Bunny Mayhem' game
 * 
 * @author Mark Schlottke
 */
public class Game extends Canvas implements Runnable
{
  public enum GameState
  {
    MainMenu, Paused, Running
  }

  private static final long serialVersionUID = 5679202415177178318L;

  public static int backgroundTileCount;
  public static GameState state;
  public static int WIDTH, HEIGHT, HEIGHTOFFSET;
  public static boolean showColliders = false;
  public static ArtAssets artassets;
  public static Player player;
  public static Camera cam;

  public boolean running = false;
  public Thread thread;

  private static BufferStrategy bs;
  private static int start;

  /**
   * Initializes the game
   */
  private void init()
  {
    state = GameState.Paused;
    WIDTH = getWidth();
    HEIGHT = getHeight();
    HEIGHTOFFSET = 50;
    this.createBufferStrategy(3);
    bs = this.getBufferStrategy();
    artassets = new ArtAssets();
    cam = new Camera(0, 0);
    
    restart();
    this.addKeyListener(new KeyInput());
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
      if (state == GameState.Running)
      {
    	 
        tick();
      }
      render();

      if (Handler.getObjects().size() < 50)
      {
        addBackground(start);
        start += 75;
      }

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
    Handler.tick();
    cam.tick(player);
  }

  /**
   * Renders the graphics for the game
   */
  private void render()
  {
    Graphics g = bs.getDrawGraphics();
    Graphics2D g2d = (Graphics2D) g;

    if (state == GameState.Running)
    {
      g.setColor(new Color(150, 150, 255));
      g.fillRect(0, 0, getWidth(), getHeight());

      g2d.translate(cam.getX(), cam.getY()); //begin cam
      // Begin Drawing
      Handler.render(g);
      // End Drawing
      g2d.translate(-cam.getX(), -cam.getY()); //end cam
    } else if (state == GameState.Paused)
    {
      g2d.drawImage(artassets.mainmenu, 0, 0, WIDTH, HEIGHT, null);
    }

    g.dispose();
    bs.show();
  }

  /**
   * Toggles the game between the 'Running' and 'Paused' states
   */
  public static void togglePause()
  {
    if (state == GameState.Running)
    {
      state = GameState.Paused;
    } else if (state == GameState.Paused)
    {
      state = GameState.Running;
    }
  }

  /**
   * Toggles the display of the gameobject colliders
   */
  public static void toggleColliders()
  {
    showColliders = !showColliders;
  }

  /**
   * Used to call the restart routine
   * Only callable if the game is in the 'Paused' state
   */
  public static void doRestart()
  {
    if (state == GameState.Paused)
    {
      restart();
    }
  }

  /**
   * Clears the necessary objects and reinitializes them
   * to enable a restart of the game
   */
  private static void restart()
  {
    backgroundTileCount = 0;
    Handler.clearObjects();
    Graphics g = bs.getDrawGraphics();
    Handler.render(g);
    g.dispose();
    bs.show();

    player = new Player(100, 100, 75, 50, new Vector2D(0, 100));
    Handler.addObject(player);

    start = -175;
    for (int i = start; i < start + WIDTH * 2; i += 75)
    {
      addBackground(i);
    }
    
    start = start + WIDTH * 2;
  }

  private static void addBackground(int x)
  {
    Handler.addObject(new Cloud((int) (x + (Math.random() * 20)), (int) (Math.random() * 350), 75, 25));
    Handler.addObject(new Tile(x, 400, 75, 300));
  }

  /**
   * Main entry point for the program
   * @param args
   */
  public static void main(String args[])
  {
    new Window(800, 600, "Hydrogen Hank", new Game());
  }
}
