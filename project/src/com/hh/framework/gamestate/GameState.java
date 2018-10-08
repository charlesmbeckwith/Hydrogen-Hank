package com.hh.framework.gamestate;

import java.awt.Graphics;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Feb. 25, 2014 
 * Purpose: Defines a game state 
 * 
 * @author Mark Schlottke
 */
public abstract class GameState
{
  public boolean blockTick = true;
  public boolean blockRender = true;

  public abstract void tick();

  public abstract void render(Graphics g);

  public void onDelete()
  {

  }
}
