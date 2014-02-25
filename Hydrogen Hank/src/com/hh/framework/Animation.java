package com.hh.framework;

import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 7, 2014
 * Purpose: Creates a flipbook animation. 
 * 
 * @author Mark
 */
public class Animation
{
  private int speed;
  private int frames;

  private int index = 0;
  private int count = 0;

  private BufferedImage[] images;
  private BufferedImage currentImg;

  /**
   * Creates the Animation object
   * @param speed - how many calls to runanimation() before next frame
   * @param args - BufferedImages to make up the animation
   */
  public Animation(int speed, BufferedImage... args)
  {
    this.speed = speed;
    images = new BufferedImage[args.length];

    for (int i = 0; i < images.length; i++)
    {
      images[i] = args[i];
    }

    frames = args.length;
  }

  /**
   * Advances the animation according to its speed
   */
  public void runAnimation()
  {
    index++;

    if (index > speed)
    {
      index = 0;
      nextFrame();
    }
  }

  /**
   * Advances the animation to the next frame
   */
  private void nextFrame()
  {
    for (int i = 0; i < frames; i++)
    {
      if (count == i)
      {
        currentImg = images[i];
      }
    }

    count++;

    if (count > frames)
    {
      count = 0;
    }
  }

  /**
   * Draws the current frame of the animation
   * @param g - Graphics object to draw with
   * @param x - Starting x position
   * @param y - Starting y position
   */
  public void drawAnimation(Graphics g, int x, int y)
  {
    g.drawImage(currentImg, x, y, null);
  }

  /**
   * Draws the current frame of the animation
   * @param g - Graphics object to draw with
   * @param x - Starting x position
   * @param y - Starting y position
   * @param scaleX - Width to stretch the image to
   * @param scaleY - Height to stretch the image to
   */
  public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY)
  {
    g.drawImage(currentImg, x, y, scaleX, scaleY, null);
  }

  /**
   * Gets the current animation frame
   * @return BufferedImage of the animation frame
   */
  public BufferedImage getAnimationFrame()
  {
    return currentImg;
  }
}
