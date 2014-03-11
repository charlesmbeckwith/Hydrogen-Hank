package com.hh.framework.gamestate;

import java.awt.Graphics;

public abstract class GameState
{
	public boolean BLOCK_TICK = true;
	public boolean BLOCK_RENDER = true;

	public abstract void tick();

	public abstract void render(Graphics g);
}
