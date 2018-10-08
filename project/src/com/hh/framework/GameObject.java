package com.hh.framework;

import java.awt.Graphics;
import java.awt.geom.Area;

import com.hh.Game;
import com.hh.framework.gamestate.states.PlayState;

/**
 * COSC3550 Spring 2014 Created : Feb. 7, 2014 Last Updated : Mar. 19, 2014
 * Purpose: Generic Game Object
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public abstract class GameObject
{
	public enum ObjectID
	{
		Background, BackgroundElement, Tile, Ground, Button, Player, Enemy, Powerup, Explosion, VFX
	}

	public enum ObjectLayer
	{
		background, middleground, foreground, hud, toplevel
	}

	/**
	 * Simple class that returns the center x and y coords.
	 */
	public class Center
	{

		public float getX()
		{
			return x - width / 2;
		}

		public float getY()
		{
			return y - width / 2;
		}

	}

	protected float x, y;

	protected float width, height;

	protected Center center = new Center();

	protected Vector2D v;

	protected ObjectID id;

	protected boolean alive;

	protected ObjectLayer layer;

	/**
	 * Creates a stationary gameobject
	 * 
	 * @param x
	 *            - x position of the gameobject
	 * @param y
	 *            - y position of the gameobject
	 * @param id
	 *            - ObjectID of the gameobject
	 */
	public GameObject(float x, float y, ObjectID id)
	{
		this.x = x;
		this.y = y;
		this.v = new Vector2D();

		this.id = id;
	}

	/**
	 * Creates a stationary gameobject with an object layer
	 * 
	 * @param x
	 *            - x position of the gameobject
	 * @param y
	 *            - y position of the gameobject
	 * @param id
	 *            - ObjectID of the gameobject
	 * @param layer
	 *            - ObjectLayer of the gameobject
	 */
	public GameObject(float x, float y, ObjectID id, ObjectLayer layer)
	{
		this.x = x;
		this.y = y;
		this.v = new Vector2D();
		this.id = id;
	}

	/**
	 * Creates a stationary gameobject
	 * 
	 * @param x
	 *            x position of the gameobject
	 * @param y
	 *            y position of the gameobject
	 * @param width
	 *            width of the gameobject
	 * @param height
	 *            height of the gameobject
	 * @param id
	 *            ObjectID of the gameobject
	 */
	public GameObject(float x, float y, int width, int height, ObjectID id,
			ObjectLayer layer)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.v = new Vector2D();
		this.id = id;
		this.layer = layer;
	}

	/**
	 * Creates a stationary gameobject
	 * 
	 * @param x
	 *            - x position of the gameobject
	 * @param y
	 *            - y position of the gameobject
	 * @param v
	 *            - velocity vector of the gameobject
	 * @param id
	 *            - ObjectID of the gameobject
	 */
	public GameObject(float x, float y, Vector2D v, ObjectID id,
			ObjectLayer layer)
	{
		this.x = x;
		this.y = y;
		this.v = v;
		this.id = id;
		this.layer = layer;
	}

	/**
	 * Creates a stationary gameobject
	 * 
	 * @param x
	 *            - x position of the gameobject
	 * @param y
	 *            - y position of the gameobject
	 * @param width
	 *            - width of the gameobject
	 * @param height
	 *            - height of the gameobject
	 * @param v
	 *            - velocity vector of the gameobject
	 * @param id
	 *            - ObjectID of the gameobject
	 */
	public GameObject(float x, float y, int width, int height, Vector2D v,
			ObjectID id, ObjectLayer layer)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.v = v;
		this.id = id;
		this.layer = layer;
	}

	/**
	 * Advances the gameobject position, rotation, and velocity
	 */
	public abstract void tick();

	/**
	 * Draws the gameobject with the given graphics object
	 * 
	 * @param g
	 *            - Graphics object to draw with
	 */
	public abstract void render(Graphics g);

	/**
	 * Gets the object's bounding box
	 * 
	 * @return
	 */
	public abstract Area boundingBox();

	/**
	 * Kills the gameobject Used to tag it for removal
	 */
	public void kill()
	{
		this.alive = false;
	}

	/**
	 * Gets the GameObject's X postion
	 * 
	 * @return
	 */
	public float getX()
	{
		return this.x;
	}

	/**
	 * Sets the GameObject's X postion
	 * 
	 * @return
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	/**
	 * Gets the GameObject's Y postion
	 * 
	 * @return
	 */
	public float getY()
	{
		return this.y;
	}

	/**
	 * Sets the GameObject's Y postion
	 * 
	 * @return
	 */
	public void setY(float y)
	{
		this.y = y;
	}

	/**
	 * Gets the GameObject's width
	 * 
	 * @return
	 */
	public float getWidth()
	{
		return this.width;
	}

	/**
	 * Sets the GameObject's width
	 * 
	 * @return
	 */
	public void setWidth(float width)
	{
		this.width = width;
	}

	/**
	 * Gets the GameObject's height
	 * 
	 * @return
	 */
	public float getHeight()
	{
		return this.height;
	}

	/**
	 * Sets the GameObject's height
	 * 
	 * @return
	 */
	public void setHeight(float height)
	{
		this.height = height;
	}

	/**
	 * Gets the GameObject's velocity
	 * 
	 * @return
	 */
	public Vector2D getVelocity()
	{
		return this.v;
	}

	/**
	 * Sets the GameObject's velocity
	 * 
	 * @return
	 */
	public void setVelocity(Vector2D v)
	{
		this.v = v;
	}

	/**
	 * Gets if the GameObject is alive
	 * 
	 * @return
	 */
	public boolean isAlive()
	{
		return this.alive;
	}

	/**
	 * Sets if the GameObject is alive
	 * 
	 * @return
	 */
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	/**
	 * Gets the GameObject's id
	 * 
	 * @return
	 */
	public ObjectID getId()
	{
		return this.id;
	}

	/**
	 * Sets the GameObject's object id
	 * 
	 * @return
	 */
	public void setId(ObjectID id)
	{
		this.id = id;
	}

	/**
	 * Gets the GameObject's render layer
	 * 
	 * @return
	 */
	public ObjectLayer getLayer()
	{
		return this.layer;
	}

	/**
	 * Sets the GameObject's render layer
	 * 
	 * @return
	 */
	public void setLayer(ObjectLayer layer)
	{
		this.layer = layer;
	}

	/**
	 * Checks if the object is currently in the game window
	 * 
	 * @return boolean - if visible or not
	 */
	public boolean isVisible()
	{
		if (-PlayState.cam.getX() < (x )
				&& (-PlayState.cam.getX() + Game.width) > (x )
				&& (-PlayState.cam.getY() < y )
				&& (-PlayState.cam.getY() + Game.height > y ))
			return true;
		else
			return false;
	}
}
