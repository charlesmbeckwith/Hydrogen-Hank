package com.hh.framework;

import com.hh.Game;
import com.hh.input.KeyBinding;
import com.hh.input.KeyInput;

public class Camera
{
  private float x, y;
  private float xOffset, yOffset;

  public Camera(float x, float y)
  {
    this.x = x;
    this.y = y;
    this.xOffset = Game.WIDTH / 3;
    this.yOffset = Game.HEIGHT * 5 / 9;
  }

  public void tick(GameObject go)
  {
    if(KeyInput.KEYSDOWN.contains(KeyBinding.PAN_LEFT.VALUE()))
    {
      panLeft();
    }
      
    if(KeyInput.KEYSDOWN.contains(KeyBinding.PAN_RIGHT.VALUE()))
    {
      panRight(); 
    }
      
    x = -go.getX() + xOffset;
    y = -go.getY() + yOffset;
    
    if(x < (-go.getX()+go.getWidth()/2))
    {
      x = (-go.getX()+go.getWidth()/2);
    }
    else if(x > ((-go.getX()-go.getWidth())+Game.WIDTH)){
      x = ((-go.getX()-go.getWidth())+Game.WIDTH);
    }
  }

  public float getX()
  {
    return x;
  }

  public float getY()
  {
    return y;
  }

  public float getXOffset()
  {
    return xOffset;
  }

  public float getYOffset()
  {
    return yOffset;
  }

  public void panRight()
  {
    xOffset -= 2;
  }
  
  public void panLeft()
  {
    xOffset += 2;
  }
}
