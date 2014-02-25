package com.hh.framework;

import com.hh.Game;

public class Camera
{
  private float x, y;

  public Camera(float x, float y)
  {
    this.x = x;
    this.y = y;
  }

  public void tick(GameObject go)
  {
    x = -go.getX() + Game.WIDTH / 3;
    y = -go.getY() + Game.HEIGHT * 2 / 3;
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
