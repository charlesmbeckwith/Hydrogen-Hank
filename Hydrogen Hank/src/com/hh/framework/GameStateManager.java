package com.hh.framework;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

public class GameStateManager
{
  public LinkedList<GameState> STATES = new LinkedList<GameState>();

  public void tick()
  {
    ListIterator<GameState> iter = STATES.listIterator();

    if (iter.hasNext())
    {
      tick(iter, iter.next());
    }
  }

  private void tick(ListIterator<GameState> iter, GameState state)
  {
    state.tick();

    if (iter.hasNext() && !state.BLOCK_TICK)
    {
      tick(iter, iter.next());
    }
  }

  public void render(Graphics g)
  {
    ListIterator<GameState> iter = STATES.listIterator();

    if (iter.hasNext())
    {
      render(iter, iter.next(), g);
    }
  }

  private void render(ListIterator<GameState> iter, GameState state, Graphics g)
  {
    state.render(g);

    if (iter.hasNext() && !state.BLOCK_RENDER)
    {
      render(iter, iter.next(), g);
    }
  }
}
