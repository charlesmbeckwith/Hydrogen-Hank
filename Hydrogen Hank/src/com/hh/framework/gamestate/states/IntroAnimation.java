package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.RenderHelper;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.bg.Cloud;

public class IntroAnimation extends GameState
{
  private ArtAssets art = Game.getArtAssets();
  private float hankXPosition = 100;
  private float hankYPosition = (Game.HEIGHT / 2);
  private int heightShift = 2;
  private int hankSize = 500;
  private int scriptProgCounter = 0;
  private String scriptPath = "/scripts/introanimation.script";
  private LinkedList<String> script = new LinkedList<String>();
  private String currentline = "";
  private String lineToPrint = "";
  private int charPtr = 0;
  private Font animFont = new Font("Arial", Font.PLAIN, 50);
  private ListIterator<String> scriptIter;
  private RenderHelper renderHelp;
  public static Handler handler;

  private int redBoxHeight = 0;

  public IntroAnimation()
  {
    renderHelp = new RenderHelper();
    parseScript();
    scriptIter = script.listIterator();

    handler = new Handler();
    // Adding Animated Clouds
    handler.addObject(new Cloud(800, 20, 256, 128, new Vector2D(0, 0), true, true));
    handler.addObject(new Cloud(100, 100, 256, 128, new Vector2D(0, 0), true, true));
    handler.addObject(new Cloud(450, 225, 256, 128, new Vector2D(0, 0), true, true));
    handler.addObject(new Cloud(600, 300, 256, 128, new Vector2D(0, 0), true, true));
    handler.addObject(new Cloud(800, 510, 256, 128, new Vector2D(0, 0), true, true));
    handler.addObject(new Cloud(250, 430, 256, 128, new Vector2D(0, 0), true, true));
  }

  /**
   * Basically keyframes for events to occur in the animation
   */
  @Override
  public void tick()
  {
    hankXPosition -= 1;
    scriptProgCounter += 1;

    if (scriptProgCounter % 2 == 0 && charPtr < currentline.length())
      charPtr++;

    lineToPrint = currentline.substring(0, charPtr);

    if (scriptProgCounter % 75 == 0 && scriptIter.hasNext())
    {
      currentline = scriptIter.next();
      charPtr = 0;
    }

    if (scriptProgCounter % 225 == 0)
    {
      hankSize *= 1.5;
      heightShift++;
      hankYPosition = (Game.HEIGHT / heightShift);
    }
    if (scriptProgCounter % 600 == 0)
      Game.manager.pop();
    if (scriptProgCounter > 225)
    {
      redBoxHeight += 4;
    }

    try
    {
      Thread.sleep(10);
    } catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void render(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g.create();
    g.setColor(new Color(109, 136, 253));
    g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    handler.render(g);
    g2d.drawImage(art.getSpriteFrame(spriteID.HANK2, 0), (int) hankXPosition, (int) hankYPosition,
        hankSize, hankSize, null);
    renderHelp.outlinedText(g2d, animFont, lineToPrint, 1.25f, Color.white, Color.black, 150, 150);
    renderHelp.tintedBox(g2d, new Color(168, 5, 5).darker(), 0.3f, 0, Game.HEIGHT / 2 - redBoxHeight, Game.WIDTH, redBoxHeight * 2);
  }

  /**
   * parses a "sprite spec sheet" detailing the height, width and number of
   * columns and rows a sprite sheet contains
   * 
   * 
   */
  private void parseScript()
  {

    BufferedReader br;
    try
    {
      br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(scriptPath)));

      for (String line; (line = br.readLine()) != null;)
      {
        if (!line.isEmpty())
        {
          script.add(line);
        }
      }
      br.close();
    } catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
