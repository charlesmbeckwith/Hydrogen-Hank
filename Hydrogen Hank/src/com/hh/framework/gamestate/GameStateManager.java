package com.hh.framework.gamestate;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class GameStateManager
{
  private LinkedList<GameState> STATES = new LinkedList<GameState>();
  private int removeCount = 0;
  private ArrayList<GameState> toAdd = new ArrayList<GameState>();

  public GameStateManager(GameState state)
  {
    STATES.push(state);
  }

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

    while (removeCount > 0)
    {
      STATES.pop();
      removeCount--;
    }

    for (GameState gs : toAdd)
    {
      STATES.push(gs);
    }

    toAdd.clear();
  }

  public void pop()
  {
    removeCount++;
  }

  public void push(GameState state)
  {
    toAdd.add(state);
  }

  public GameState getFirstState()
  {
    return STATES.getFirst();
  }

  @SuppressWarnings("rawtypes")
  public Class getFirstClass()
  {
    return STATES.getFirst().getClass();
  }
}
