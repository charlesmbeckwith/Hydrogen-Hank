package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.graphics.ArtAssets;
import com.hh.states.PlayState;

/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke
 */
public class Tile extends GameObject
{
  private BufferedImage IMG;
  private ArtAssets art = Game.getArtAssets();
  public Tile(float x, float y, int width, int height)
  {
    super(x, y, width, height, ObjectID.Tile, ObjectLayer.middleground);
    IMG = art.dirt;
    ALIVE = true;
  }

  public void tick()
  {
  }

  public void render(Graphics g)
  {
    if (X + WIDTH < -PlayState.cam.getX())
    {
      ALIVE = false;
    }

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
