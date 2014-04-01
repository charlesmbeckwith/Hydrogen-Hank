

package com.hh.objects.powerups;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hh.Game;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.Powerup;

/**
 * COSC3550 Spring 2014 Created : Feb. 28, 2014 Last Updated : Feb. 28, 2014
 * Purpose: Defines a hydrogen molecule powerup
 * 
 * @author Charlie Beckwith
 */
public class HydrogenMolecule
    extends Powerup
{

  private BufferedImage IMG;
  private ArtAssets art;
  private double rotationRadian;
  
  

  /**
   * @param x
   * @param y
   * @param width
   * @param height
   * @param powerupType
   */
  public HydrogenMolecule(float x, float y, int width, int height)
  {
    super(x, y, width, height, PowerupType.HydrogenMolecule);
    art = Game.getArtAssets();
    Random rand = new Random();
    int frame = rand.nextInt(5);
    IMG = art.getSpriteFrame(spriteID.HYDROGEN, frame);
    rotationRadian = Math.toRadians((double) (Game.Rand.nextInt(360)));
    VALUE = .1;

  }

  @Override
  public void tick()
  {
    rotationRadian += .1;
    archimedesSpiralMovement(rotationRadian);

  }

  /* Movement that causes the molecules to move in a archemedes sprial */
  public void archimedesSpiralMovement(double theta){
    
    double a = -5; 
  y += (float) ((a*theta)/(2*Math.PI)*Math.cos(theta));
  x += (float) ((a*theta)/(2*Math.PI)*Math.sin(theta));

  }
  @Override
  public void render(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.rotate(rotationRadian, x, y);
    g2d.drawImage(IMG, (int) center.getX(), (int) center.getY(), (int) width,
                  (int) height, null);
    g2d.dispose();
    super.render(g);
  }

  @Override
  public Area boundingBox()
  {
    return new Area(new Ellipse2D.Double(center.getX()+width/4, center.getY()+height/4, width/2, height/2));

  }
  
  @Override
  public double getValue()
  {
	  return VALUE;
  }

}
