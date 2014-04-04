package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.hh.Game;
import com.hh.framework.RenderHelper;
import com.hh.framework.ScoreKeeper;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.input.KeyInput;
import com.hh.objects.TextBox;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Apr. 4, 2014 
 * Last Updated : Apr. 4, 2014 
 * Purpose: Defines the Game Over state for the game
 * 
 * @author Mark Schlottke
 */
public class GameOverState extends GameState
{
  private ArtAssets art = Game.getArtAssets();
  private RenderHelper renderHelp = new RenderHelper();
  private float boxHeight = 0;
  private float boxWidth = 0;
  private TextBox nameEntry;
  private int[] scores = new int[3];
  private Font font = new Font("Arial", Font.BOLD, 100);
  private Font font2 = new Font("Arial", Font.BOLD, 34);
  private Font font3 = new Font("Arial", Font.BOLD, 20);
  
  private int balloonsUsed = 0;
  private int numAtoms = 0;
  private int closeCalls = 0;
  private int birdsObliterated =0;
  private int powerUps = 0;
  private int heliumUsed = 0;

  public GameOverState(int overall, int altitude, int time, int balloonsUsed, int numAtoms, int closeCalls, int birdsObliterated, int powerUps, int heliumUsed)
  {
    nameEntry = new TextBox("", 10, Game.width / 2, Game.height / 2, 300, 50);
    KeyInput.textEntry = nameEntry;
    scores[0] = overall;
    scores[1] = altitude;
    scores[2] = time;
    this.balloonsUsed = balloonsUsed;
    this. numAtoms = numAtoms;
    this.closeCalls = closeCalls;
    this.birdsObliterated = birdsObliterated;
    this.powerUps = powerUps;
    this.heliumUsed = heliumUsed;
  }

  public void tick()
  {
    if (boxHeight < Game.height / 2)
    {
      boxHeight += Game.height / 25;
    } else
    {
      boxHeight = Game.height;
    }

    if (boxWidth < Game.width / 2)
    {
      boxWidth += Game.width / 25;
    } else
    {
      boxWidth = Game.width;
    }

    nameEntry.tick();
  }

  public void render(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;

    renderHelp.tintedBox(g2d, Color.black, 0.7f, (int) (Game.width / 2 - boxWidth),
        (int) (Game.height / 2 - boxHeight), (int) (boxWidth * 2), (int) (boxHeight * 2));

    if (boxWidth >= Game.width / 2 && boxHeight >= Game.height / 2)
    {
      //g.drawImage(art.pauseScreen, (Game.width / 2 - art.pauseScreen.getWidth() / 2),
      //    (Game.height / 2 - 150), null);
      renderHelp.outlinedText((Graphics2D) g, font, "Game Over", 0.9f, Color.black, Color.red,
          (Game.width / 2)-260, (Game.height / 2)-120);
      renderHelp.outlinedText((Graphics2D) g, font2, "Enter Your Name", 0.9f, Color.black, Color.red,
          (Game.width / 2)-140, (Game.height / 2)-50);
      renderHelp.outlinedText((Graphics2D) g, font2, "Game Stats", .8f, Color.black, Color.white, (Game.width/2) -70, (Game.height/2) +80);
      renderHelp.outlinedText((Graphics2D) g, font3, "Max Altitude = " + scores[2], .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +120);
      renderHelp.outlinedText((Graphics2D) g, font3, "Time = " + scores[1] + " seconds", .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +140);
      renderHelp.outlinedText((Graphics2D) g, font3, "Overall Score = " + scores[0], .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +160);
      renderHelp.outlinedText((Graphics2D) g, font3, "Balloons Used = " + balloonsUsed, .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +180);
      renderHelp.outlinedText((Graphics2D) g, font3, "Atoms Picked Up = " + numAtoms, .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +200);
      renderHelp.outlinedText((Graphics2D) g, font3, "Close Calls = " + closeCalls, .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +220);
      renderHelp.outlinedText((Graphics2D) g, font3, "Birds Obliterated = " + birdsObliterated, .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +240);
      renderHelp.outlinedText((Graphics2D) g, font3, "Helium Used = " + heliumUsed, .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +260);
      renderHelp.outlinedText((Graphics2D) g, font3, "Power Ups Picked Up = " + powerUps, .4f, Color.black, Color.white, (Game.width/2) -100, (Game.height/2) +280);
      nameEntry.render(g);
    }
  }

  public void onDelete()
  {
    String name = nameEntry.getText();

    if (name.length() > 0)
    {
      ScoreKeeper.newScore(name, scores[0], scores[1], scores[2]);
    } else
    {
      ScoreKeeper.newScore("Player", scores[0], scores[1], scores[2]);
    }
  }
}
