package com.hh;

import javax.swing.*;

import java.awt.*;

/**
 * COSC3550 Spring 2014
 * 
 * 
 * 
 * @author Mark Schlottke
 */
public class Window
{
	
	private Game game;
	private JFrame frame;
  /**
   * Set up the display of the game and start it
   * @param w - width of the game window
   * @param h - height of the game window
   * @param title - title of the game
   * @param game - game canvas to attach
   */
  public Window(int w, int h, String title, Game game)
  {
    this.game = (Game) game;
    this.game.setPreferredSize(new Dimension(w, h));
    this.game.setMaximumSize(new Dimension(w, h));
    this.game.setMinimumSize(new Dimension(w, h));

    frame = new JFrame(title);
    frame.add(game);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    this.game.start();
  }

  public Point getPosition()
  {
    return frame.getLocationOnScreen();
  }
  
  public void swapWindow(Canvas canvas){
	  frame.add(canvas);
  }
}
