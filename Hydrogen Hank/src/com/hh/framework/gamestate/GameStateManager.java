package com.hh.framework.gamestate;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Feb. 25, 2014 
 * Purpose: Manages a linked list of GameState objects' ticks and renders 
 * 
 * @author Mark Schlottke
 */
public class GameStateManager
{
	private LinkedList<GameState> states = new LinkedList<GameState>();
	private int removeCount = 0;
	private ArrayList<GameState> toAdd = new ArrayList<GameState>();

	public GameStateManager()
	{
	}

	public void tick()
	{
		ListIterator<GameState> iter = states.listIterator();

		if (iter.hasNext())
		{
			tick(iter, iter.next());
		}
	}

	private void tick(ListIterator<GameState> iter, GameState state)
	{
		state.tick();

		if (iter.hasNext() && !state.blockTick)
		{
			tick(iter, iter.next());
		}
	}

	public void render(Graphics g)
	{
		ListIterator<GameState> iter = states.listIterator();

		if (iter.hasNext())
		{
			render(iter, iter.next(), g);
		}
	}

	private void render(ListIterator<GameState> iter, GameState state, Graphics g)
	{
		state.render(g);

		if (iter.hasNext() && !state.blockRender)
		{
			render(iter, iter.next(), g);
		}

		while (removeCount > 0)
		{
		  iter = states.listIterator();
	    if (iter.hasNext())
	    {
	      iter.next().onDelete();
	    }
		  
			states.pop();
			removeCount--;
		}

		for (GameState gs : toAdd)
		{
			states.push(gs);
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

	public void forcePush(GameState state)
	{
		states.push(state);
	}

	public GameState getFirstState()
	{
		return states.size() > 0 ? states.getFirst() : null;
	}

	@SuppressWarnings("rawtypes")
	public Class getFirstClass()
	{
		return states.size() > 0 ? states.getFirst().getClass() : null;
	}
}
