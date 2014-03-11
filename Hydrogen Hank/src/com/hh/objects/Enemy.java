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
public class Enemy extends GameObject
{

	public enum EnemyType
	{
		Bird
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

	}

	public EnemyType getEnemyType()
	{
		return enemyType;
	}

}
