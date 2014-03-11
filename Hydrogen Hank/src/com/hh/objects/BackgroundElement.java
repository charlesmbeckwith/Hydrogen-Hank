/**
 * 
 */
package com.hh.objects;

import java.awt.Graphics;

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
		Cloud
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

	}

}
