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
public class Enemy extends GameObject {

	/**
	 * @param x
	 * @param y
	 * @param v
	 * @param id
	 * @param layer
	 */
	public Enemy(float x, float y, int width, int height, Vector2D v) {
		super(x, y, width, height, v, ObjectID.Enemy, ObjectLayer.middleground);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

}
