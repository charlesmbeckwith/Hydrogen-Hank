package com.hh.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import com.hh.*;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 10, 2014
 * Purpose: Gives the player control over aspects of the game
 * 
 * @author Mark Schlottke
 */
public class KeyInput extends KeyAdapter
{
  public static LinkedList<Integer> KEYSDOWN;

  /**
   * Initializes the list of keys held down
   */
  public KeyInput()
  {
    KEYSDOWN = new LinkedList<Integer>();
  }

  /**
   * Determines the action to perform upon a key press
   */
  public void keyPressed(KeyEvent e)
  {
    if (!KEYSDOWN.contains(e.getKeyCode()))
    {
      KEYSDOWN.add(e.getKeyCode());
    }
    
    if (e.getKeyCode() == KeyBinding.RESTART.VALUE())
    {
      Game.doRestart();
    }
  }

  /**
   * Determines the action to perform upon a key release
   */
  public void keyReleased(KeyEvent e)
  {
    if (KEYSDOWN.contains(e.getKeyCode()))
    {
      KEYSDOWN.remove((Integer) e.getKeyCode());
    }
    
    if (e.getKeyCode() == KeyBinding.PAUSE.VALUE())
    {
      Game.togglePause();
    }
    
    if (e.getKeyCode() == KeyBinding.COLLIDERS.VALUE())
    {
      Game.toggleColliders();
    }
  }
}
