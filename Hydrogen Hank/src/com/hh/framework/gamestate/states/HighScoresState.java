package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.RenderHelper;
import com.hh.framework.Score;
import com.hh.framework.ScoreKeeper;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.objects.MenuButton;
import com.hh.objects.ScoreTab;
import com.hh.objects.MenuButton.ButtonID;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 Last Updated : Mar. 19, 2014 Purpose: Defines the
 * High Scores state for the game
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class HighScoresState extends GameState
{
  public static Handler handler = new Handler();
  @SuppressWarnings("unused")
  private RenderHelper renderHelp = new RenderHelper();
  @SuppressWarnings("unused")
  private Font font;
  private ArtAssets art;

  public HighScoresState()
  {
    int tabX = 250;
    ScoreKeeper scorekeeper = Game.getScoreKeeper();
    font = new Font("Arial", Font.PLAIN, 20);
    art = Game.getArtAssets();

    if (scorekeeper.getScores().size() == 0)
    {
      ScoreKeeper.newScore("Hank", 100, 50, 25);
      ScoreKeeper.newScore("Hank", 100, 50, 25);
      ScoreKeeper.newScore("Hank", 100, 50, 25);
      ScoreKeeper.newScore("Hank", 100, 50, 25);
      ScoreKeeper.newScore("Hank", 100, 50, 25);
      ScoreKeeper.newScore("Hank", 100, 50, 25);
      ScoreKeeper.newScore("Hank", 100, 50, 25);
      ScoreKeeper.newScore("Hank", 100, 50, 25);
    }

    handler.addObject(new ScoreTab("Overall", Score.ScoreType.OVERALL, tabX, 90, 150, 64, true));
    handler.addObject(new ScoreTab("Altitude", Score.ScoreType.ALTITUDE, tabX + 150, 90, 150, 64,
        false));
    handler.addObject(new ScoreTab("Flight Time", Score.ScoreType.TIME, tabX + 300, 90, 150, 64,
        false));

    handler.addObject(new MenuButton("Reset Scores", Game.width / 2, Game.height - 64 / 2,
        Game.width / 3 - 15, 64, ButtonID.RESETSCORES));
  }

  public void tick()
  {
    handler.tick();
  }

  public void render(Graphics g)
  {
    g.setColor(new Color(109, 136, 253));
    g.fillRect(0, 0, Game.width, Game.height);

    g.drawImage(art.highScoreBG, 100, 100, 600, 425, null);
    handler.render(g);
  }

  public void onDelete()
  {
    handler.clearObjects();
  }
}
