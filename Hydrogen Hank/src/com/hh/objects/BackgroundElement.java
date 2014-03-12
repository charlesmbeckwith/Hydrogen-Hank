/**
 * 
 */
package com.hh.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hh.Game;
import com.hh.framework.GameObject;
import com.hh.framework.Vector2D;

/**
 * @author blinginbeckwith
 * 
 */
public class BackgroundElement extends GameObject
{

	public enum BackgroundElementType
	{
		Cloud,
		Ground
	}

	protected BackgroundElementType BgElementType;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param layer
	 */
	public BackgroundElement(float x, float y, int width, int height,
	    BackgroundElementType BgElementType, ObjectLayer layer)
	{
		super(x, y, width, height, ObjectID.BackgroundElement, layer);
		this.BgElementType = BgElementType;

	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param v
	 * @param cloud
	 * @param objectLayer
	 */
	public BackgroundElement(float x, float y, int width, int height, Vector2D v,
	    BackgroundElementType BgElementType, ObjectLayer layer)
	{
		super(x, y, width, height, v, ObjectID.BackgroundElement, layer);
		this.BgElementType = BgElementType;
	}

	@Override
	public void tick()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g)
	{
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		if (Game.debugOptions().contains("bg"))
		{
			g2d.setColor(Color.black);
			g2d.draw(this.boundingBox());
		}
	}
	
	@Override
	public Rectangle boundingBox(){
		return new Rectangle((int) (X), (int) (Y), WIDTH, HEIGHT);
	}

}
