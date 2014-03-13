package com.hh.framework.gamestate;

import java.awt.Graphics;

public abstract class GameState
{
	public boolean blockTick = true;
	public boolean blockRender = true;

	public abstract void tick();

	public abstract void render(Graphics g);
}
