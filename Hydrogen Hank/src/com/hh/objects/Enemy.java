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
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 28, 2014 
 * Last Updated : Feb. 28, 2014 
 * Purpose: Defines an enemy
 * 
 * @author Charlie Beckwith
 */
public class Enemy extends GameObject
{

	public enum EnemyType
	{
		Bird, Plane
	}

	protected EnemyType enemyType;

	/**
	 * @param x
	 * @param y
	 * @param v
	 * @param id
	 * @param layer
	 */
	public Enemy(float x, float y, int width, int height, Vector2D v, EnemyType enemyType)
	{
		super(x, y, width, height, v, ObjectID.Enemy, ObjectLayer.middleground);
		this.enemyType = enemyType;
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
		if (Game.debugOptions().contains("enemy"))
		{
			g2d.setColor(Color.black);
			g2d.draw(this.boundingBox());
		}
	}

	@Override
	public Rectangle boundingBox()
	{
		return new Rectangle((int) center.getX(), (int) center.getY(), (int) width, (int) height);
	}

	public EnemyType getEnemyType()
	{
		return enemyType;
	}

}
