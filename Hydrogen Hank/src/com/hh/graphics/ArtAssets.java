package com.hh.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.hh.graphics.SpriteSheet.spriteID;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 11, 2014
 * Purpose: Loads an provides access to Art Assets for the 'Dust Bunny Mayhem' game
 * 
 * @author Mark Schlottke
 */
public class ArtAssets
{
  public SpriteSheet hydrogenhank_sheet, balloon_sheet, bird_sheet, hydrogen_sheet;
  
  public BufferedImage dirt, cloud, pauseScreen;
  public BufferedImage mainTitle, newButton, newButton2, scoresButton, scoresButton2,
      creditsButton, creditsButton2;

  /**
   * Initializes the art assets and loads them
   */
  public ArtAssets()
  {
   
    hydrogenhank_sheet = new SpriteSheet(spriteID.HANK, "/hydrogenhank/HydrogenHankSpriteSheet.png");
    balloon_sheet = new SpriteSheet(spriteID.BALLOON, "/hydrogenhank/balloons/balloonvariations.png");
    hydrogen_sheet = new SpriteSheet(spriteID.HYDROGEN, "/graphics/powerups/hydrogen/hydrogenv3.png" );
    bird_sheet = new SpriteSheet(spriteID.BIRD, "/oldgraphics/birdsheet.png");
    mainTitle = loadImage("/graphics/mainmenu/Logo3.png");
    newButton = loadImage("/graphics/mainmenu/newGameButton.png");
    scoresButton = loadImage("/graphics/mainmenu/highScoresButton.png");
    creditsButton = loadImage("/graphics/mainmenu/creditsButton.png");
    newButton2 = loadImage("/graphics/mainmenu/newGameButton2.png");
    scoresButton2 = loadImage("/graphics/mainmenu/highScoresButton2.png");
    creditsButton2 = loadImage("/graphics/mainmenu/creditsButton2.png");
   
    dirt = loadImage("/oldgraphics/dirtblock.png");
    cloud = loadImage("/graphics/bgelements/cloud2.png");
    pauseScreen = loadImage("/oldgraphics/PauseScreen.png");

   
  }

  /**
   * Loads the BufferedImage from the path
   * @param path - location of the image
   * @return - BufferedImage of the asset image
   */
  private BufferedImage loadImage(String path)
  {
    BufferedImage image = null;
    try
    {
      image = ImageIO.read(getClass().getResource(path));
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    return image;
  }

  /**
   * Hues an image to the color given
   * BUG: Displays a filled rectangle of the color occasionally
   * @param image - image to be hued
   * @param width - width of the image
   * @param height - height of the image
   * @param color - color to hue the image
   * @return - hued image
   */
  public BufferedImage hueImg(BufferedImage image, int width, int height, Color color)
  {
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = newImage.createGraphics();
    Color translucent = new Color(color.getRed(), color.getGreen(), color.getBlue(), 50);
    g.drawImage(image, 0, 0, width, height, null);
    g.setColor(translucent);
    g.fillRect(0, 0, width, height);
    g.setComposite(AlphaComposite.DstIn);
    g.drawImage(image, 0, 0, width, height, null);
    g.dispose();
    return newImage;
  } 

  /**
   * 
   * @param id - spriteID
   * @param frame - frame number in sprite sheet to be grabbed
   * @return BufferedImage - return BufferedImage of sprite sheet called for
   */
  public BufferedImage getSpriteFrame(spriteID id, int frame){
	  switch(id){
	  case HANK:
		  return hydrogenhank_sheet.getFrame(frame);
	  case BALLOON:
		  return balloon_sheet.getFrame(frame);
	  case BIRD:
      return bird_sheet.getFrame(frame);
	  case HYDROGEN:
		  return hydrogen_sheet.getFrame(frame);
	  default:
		  return null;
			  
	  }
  }

}
