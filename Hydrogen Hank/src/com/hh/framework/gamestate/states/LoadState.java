package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.hh.Game;
import com.hh.framework.RenderHelper;
import com.hh.framework.gamestate.GameState;

public class LoadState extends GameState
{
  private RenderHelper renderHelp;
  private boolean loading;
  private Font font;

  public LoadState()
  {
    renderHelp = new RenderHelper();
    loading = false;
    font = new Font("Arial", Font.BOLD, 32);
  }

  public void tick()
  {
  }

  public void render(Graphics g)
  {
    g.setColor(new Color(109, 136, 253));
    g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    FontMetrics metrics = g.getFontMetrics(font);
    int textX = (int) (Game.WIDTH/2 - (metrics.stringWidth("Loading") / 2));
    int textY = (int) Game.HEIGHT/2 + (metrics.getHeight());
    renderHelp.outlinedText((Graphics2D)g, font, "Loading", 1.25f, Color.black, Color.white, textX, textY);

    if (!loading)
    {
      Game.getArtAssets().load();
      loading = true;
    }
  }

}
