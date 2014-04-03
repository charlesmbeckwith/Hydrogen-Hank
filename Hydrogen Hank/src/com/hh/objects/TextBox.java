package com.hh.objects;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.graphics.ArtAssets;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Apr. 4, 2014 
 * Last Updated : Apr. 4, 2014 
 * Purpose: Defines a text box
 * 
 * @author Mark Schlottke
 */
public class TextBox extends GameObject
{
  private RenderHelper renderHelp = new RenderHelper();
  private Font font = new Font("Arial", Font.BOLD, 32);
  String text = "";
  int maxChars = 32;
  float lastBlink = 0;

  public TextBox(String text, int maxChars, float x, float y, int width, int height)
  {
    super(x, y, width, height, ObjectID.Tile, ObjectLayer.background);
    alive = true;
    this.text = text;
    this.maxChars = maxChars;
  }

  public TextBox(String text, int maxChars, Font font, float x, float y, int width, int height)
  {
    super(x, y, width, height, ObjectID.Tile, ObjectLayer.background);
    alive = true;
    this.font = font;
    this.text = text;
    this.maxChars = maxChars;
  }

  @Override
  public void tick()
  {
    lastBlink += GameTime.delta();
  }

  @Override
  public void render(Graphics g)
  {
    if (alive)
    {
      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.black);
      g2d.fillRect((int) (x - width / 2 - 1), (int) (y - height / 2 - 1), (int) (width + 2),
          (int) (height + 2));
      g2d.setColor(Color.white);
      g2d.fillRect((int) (x - width / 2), (int) (y - height / 2), (int) (width), (int) (height));

      FontMetrics metrics = g.getFontMetrics(font);
      int textX = (int) (x - (width / 2) + (width * 0.04));
      int textY = (int) y + (metrics.getHeight() / 4);
      renderHelp.outlinedText(g2d, font, text, 1.25f, Color.black, Color.black, textX, textY);
      
      if (lastBlink > 1)
      {
        g2d.setColor(Color.black);
        g2d.drawLine((int) (textX + metrics.stringWidth(text)), (int) (y - height / 2 + 8),
            (int) (textX + metrics.stringWidth(text)), (int) (y - height / 2 + metrics.getHeight()));

        if (lastBlink > 1.3)
        {
          lastBlink = 0;
        }
      }
    }
  }

  public String getText()
  {
    return this.text;
  }

  public void addChar(char c)
  {
    if (this.text.length() <= maxChars)
    {
      this.text += c;
    }
  }

  public void removeLast()
  {
    if (this.text.length() > 0)
    {
      this.text = text.substring(0, text.length() - 1);
    }
  }

  @Override
  public Area boundingBox()
  {
    return new Area(new Rectangle((int) (x - width / 2), (int) (y - height / 2), (int) width,
        (int) height));
  }

}
