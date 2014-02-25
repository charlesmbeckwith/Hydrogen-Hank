package com.hh;

import javax.swing.*;

import java.awt.*;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014 
 * Last Updated : Feb. 7, 2014 
 * Purpose: JFrame display for 'Dust Bunny Mayhem' game canvas
 * 
 * @author Mark
 */
public class Window
{
  /**
   * Set up the display of the game and start it
   * @param w - width of the game window
   * @param h - height of the game window
   * @param title - title of the game
   * @param game - game canvas to attach
   */
  public Window(int w, int h, String title, Game game)
  {
    game.setPreferredSize(new Dimension(w, h));
    game.setMaximumSize(new Dimension(w, h));
    game.setMinimumSize(new Dimension(w, h));

    JFrame frame = new JFrame(title);
    frame.add(game);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    game.start();
  }
}
