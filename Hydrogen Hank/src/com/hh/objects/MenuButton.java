package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.*;


/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke
 */
public class MenuButton extends GameObject
{
  private BufferedImage BASEIMG, SELECTIMG;
  public boolean SELECTED;
  private ButtonID BUTTONID;
  
  public enum ButtonID{
	  NEWGAME,
	  HIGHSCORE,
	  CREDITS
  };

  public MenuButton(BufferedImage base, BufferedImage selected, float x, float y, int width,
      int height, ButtonID buttontype)
  {
    super(x, y, width, height, ObjectID.Tile, ObjectLayer.background);
    ALIVE = true;
    BASEIMG = base;
    SELECTIMG = selected;
    SELECTED = false;
    BUTTONID = buttontype;
  }

  public void tick()
  {
    Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
    mouseLoc.x -= Game.getPosition().x;
    mouseLoc.y -= Game.getPosition().y +25; // 25 pixels is roughly the size of the top of the window
    
    if (mouseLoc.x > (X - WIDTH / 2) && mouseLoc.y > (Y - HEIGHT / 2)
        && mouseLoc.x < (X + WIDTH / 2) && mouseLoc.y < (Y + HEIGHT / 2))
    {
      SELECTED = true;
    }
    else{
      SELECTED = false;
    }
  }

  public void render(Graphics g)
  {
    if (ALIVE)
    {
      Graphics2D g2d = (Graphics2D) g;

      if (!SELECTED)
      {
        g2d.drawImage(BASEIMG, (int) (X - WIDTH / 2), (int) (Y - HEIGHT / 2), WIDTH, HEIGHT, null);
      } else
      {
        g2d.drawImage(SELECTIMG, (int) (X - WIDTH / 2), (int) (Y - HEIGHT / 2), WIDTH, HEIGHT, null);
      }
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
  
  public ButtonID getButtonID(){
	  return BUTTONID;
  }
}
