package com.hh.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

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
  private SpriteSheet dustright;
  private BufferedImage dustbunnyright_sheet = null;

  public BufferedImage[] dustbunnyright = new BufferedImage[9];
  public BufferedImage dirt, button, buttonpress, cloud;
  public BufferedImage mainBg, mainTitle, newButton, newButton2, scoresButton, scoresButton2, creditsButton, creditsButton2;

  /**
   * Initializes the art assets and loads them
   */
  public ArtAssets()
  {
    dustbunnyright_sheet = loadImage("/oldgraphics/dustbunnywalkright.png");
    dustright = new SpriteSheet(dustbunnyright_sheet);
    
    mainBg = loadImage("/graphics/mainmenu/mainmenubackground.png");
    mainTitle = loadImage("/graphics/mainmenu/HydrogenHankTitle.png");
    newButton = loadImage("/graphics/mainmenu/newGameButton.png");
    scoresButton = loadImage("/graphics/mainmenu/highScoresButton.png");
    creditsButton = loadImage("/graphics/mainmenu/creditsButton.png");
    newButton2 = loadImage("/graphics/mainmenu/newGameButton2.png");
    scoresButton2 = loadImage("/graphics/mainmenu/highScoresButton2.png");
    creditsButton2 = loadImage("/graphics/mainmenu/creditsButton2.png");
    
    dirt = loadImage("/oldgraphics/dirtblock.png");
    button = loadImage("/oldgraphics/button.png");
    buttonpress = loadImage("/oldgraphics/buttonpress.png");
    cloud = loadImage("/oldgraphics/cloud.png");
 
    getFrames();
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
   * Gets the frames of the animations
   */
  private void getFrames()
  {
    int width = 104;
    int height = 59;
    dustbunnyright[0] = dustright.grabImage(1, 1, width, height);
    dustbunnyright[1] = dustright.grabImage(2, 1, width, height);
    dustbunnyright[2] = dustright.grabImage(3, 1, width, height);
    dustbunnyright[3] = dustright.grabImage(4, 1, width, height);
    dustbunnyright[4] = dustright.grabImage(5, 1, width, height);
    dustbunnyright[5] = dustright.grabImage(6, 1, width, height);
    dustbunnyright[6] = dustright.grabImage(7, 1, width, height);
    dustbunnyright[7] = dustright.grabImage(8, 1, width, height);
    dustbunnyright[8] = dustright.grabImage(9, 1, width, height);
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
}
