package com.hh.framework;

import com.hh.Game;

public class Camera
{
  private float x, y;
  private float xOffset, yOffset;

  public Camera(float x, float y)
  {
    this.x = x;
    this.y = y;
    this.xOffset = Game.WIDTH / 3;
    this.yOffset = Game.HEIGHT * 2 / 3;
  }

  public void tick(GameObject go)
  {
	  
    x = -go.getX() + xOffset;
    y = -go.getY()+ yOffset;
  }

  public float getX()
  {
    return x;
  }

  public void setX(float x)
  {
    this.x = x;
  }

  public float getY()
  {
    return y;
  }

  public void setY(float y)
  {
    this.y = y;
  }
}
