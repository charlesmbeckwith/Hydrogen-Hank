package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.graphics.Animation;
import com.hh.keyboard.KeyBinding;
import com.hh.keyboard.KeyInput;
import com.hh.states.PlayState;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 11, 2014
 * Purpose: Defines the Dust Bunny
 * 
 * @author Mark Schlottke
 */
public class Player extends GameObject
{
  private final float GRAVITY = 200f;

  private Animation RIGHT, CURRENT;
  private float BUOYANCY;
  private Color HUE;

  public Player(float x, float y, int width, int height, Vector2D v)
  {
    super(x, y, width, height, v, ObjectID.Player, ObjectLayer.foreground);

    ALIVE = true;
    Random rand = new Random();
    HUE = new Color(50 + rand.nextInt(200), 50 + rand.nextInt(200), 50 + rand.nextInt(200));
    BUOYANCY = 0.0f;

    RIGHT = new Animation(1, Game.artassets.hydrogenhank[0]);
    	/*
    		, Game.artassets.hydrogenhank[1],
        Game.artassets.hydrogenhank[2], Game.artassets.hydrogenhank[3],
        Game.artassets.hydrogenhank[4], Game.artassets.hydrogenhank[5],
        Game.artassets.hydrogenhank[6], Game.artassets.hydrogenhank[7]);
        */

    CURRENT = new Animation(3, Game.artassets.hydrogenhank[8]);
  }

  public void tick()
  {
    if (ALIVE)
    {
      boolean collision = collision();

      if (KeyInput.KEYSDOWN.contains(KeyBinding.INFLATE.VALUE()))
      {
        BUOYANCY -= 5.1f;
      }

      if (KeyInput.KEYSDOWN.contains(KeyBinding.DEFLATE.VALUE()))
      {
        BUOYANCY += 1.1f;
      }

      if (BUOYANCY > 0)
      {
        BUOYANCY = 0;
      }
      if (BUOYANCY < -(GRAVITY * 2))
      {
        BUOYANCY = -(GRAVITY * 2);
      }

      if (V.DX < 5000 && !collision && KeyInput.KEYSDOWN.contains(KeyBinding.FANON.VALUE()))
      {
        V.DX += 1;
      }

      V.DY = BUOYANCY + GRAVITY;

      X += V.DX * GameTime.delta();
      Y += V.DY * GameTime.delta();

      CURRENT = RIGHT;
      CURRENT.runAnimation();

      // Simulate a slow leak in the balloon
      BUOYANCY += 1;
    }
  }

  public boolean collision()
  {
    boolean col = false;

    for (GameObject go : PlayState.handler.getObjects())
    {
      if (go != this && go.getID() != ObjectID.Background && (Y + (HEIGHT / 2) - 14) >= go.getY()
          && X > go.getX() && X < go.getX() + go.getWidth())
      {
        col = true;

        Y = (go.getY() - (HEIGHT / 2) + 14);
        V.DY = 0;

        if (V.DX > 0)
          V.DX -= (V.DX * 0.01);
      }
    }

    return col;
  }

  public void render(Graphics g)
  {
    if (ALIVE)
    {
      Graphics2D g2d = (Graphics2D) g;

      BufferedImage image = Game.artassets.hueImg(CURRENT.getAnimationFrame(), WIDTH, HEIGHT, HUE);
      g2d.drawImage(image, (int) (X - (WIDTH / 2)), (int) (Y - (HEIGHT / 2)), WIDTH, HEIGHT, null);
    }
  }
}
