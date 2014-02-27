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
  
  public void  setX(float x){this.x = x;}
  public void  setY(float y){this.y = y;}
  public float getX(){return x;}
  public float getY(){return y;}
  public float getXOffset(){return xOffset;}
  public float getYOffset(){return yOffset;}
}
