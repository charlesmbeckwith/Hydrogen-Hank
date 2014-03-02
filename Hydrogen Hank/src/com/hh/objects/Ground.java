package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.graphics.ArtAssets;

/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke
 */
public class Ground extends GameObject
{
  private BufferedImage IMG;
  private ArtAssets art = Game.getArtAssets();
  public Ground(float x, float y, int width, int height)
  {
    super(x, y, width, height, ObjectID.Ground, ObjectLayer.middleground);
    IMG = art.dirt;
    ALIVE = true;
  }

  public void tick()
  {
  }

  public void render(Graphics g)
  {
    if (ALIVE)
    {
      Graphics2D g2d = (Graphics2D) g;
      g2d.drawImage(IMG, (int) X, (int) Y, WIDTH, HEIGHT, null);
    }
  }

  public int getWidth()
  {
    return WIDTH;
  }

  public int getHeight()
  {
    return HEIGHT;
  }
}
