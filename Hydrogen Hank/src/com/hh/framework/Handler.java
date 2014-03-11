package com.hh.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014 Last Updated : Feb. 8, 2014 Purpose: Helper class to handle to the
 * gameobject list
 * 
 * @author Mark Schlottke
 */
public class Handler
{
	public enum RemovalConditions
	{
		OffscreenLeft, OffscreenRight, OffscreenTop, OffscreenBottom, Dead
	}

	private Rectangle sceneWindow;
	private List<RemovalConditions> conditions;
	private LinkedGameObjects objects = new LinkedGameObjects();
	private ArrayList<GameObject> toRemove = new ArrayList<GameObject>();
	private GameObject tempObj;

	/**
	 * Advances all of the gameobjects in the list
	 */
	public void tick()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			tempObj = objects.get(i);
			tempObj.tick();
			if (sceneWindow != null && conditions != null)
			{
				float x = tempObj.X;
				float y = tempObj.Y;
				float xOffset = tempObj.WIDTH / 2;
				float yOffset = tempObj.HEIGHT / 2;

				if ((conditions.contains(RemovalConditions.Dead) && !tempObj.isAlive())
				    || (conditions.contains(RemovalConditions.OffscreenLeft) && x + xOffset < sceneWindow.x)
				    || (conditions.contains(RemovalConditions.OffscreenRight) && x - xOffset > sceneWindow.x
				        + sceneWindow.width)
				    || (conditions.contains(RemovalConditions.OffscreenTop) && y + yOffset < sceneWindow.y)
				    || (conditions.contains(RemovalConditions.OffscreenBottom) && y - yOffset > sceneWindow.y
				        + sceneWindow.height))
				{
					toRemove.add(tempObj);
				}
			}
		}
	}

	/**
	 * Renders and Draws all of the gameobjects in the list then removes dead gameobjects from the
	 * list
	 */
	public void render(Graphics g)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			tempObj = objects.get(i);
			tempObj.render(g);
		}

		for (GameObject go : toRemove)
		{
			objects.remove(go);
		}
		toRemove.clear();
	}

	/**
	 * Removes all of the gameobjects from the list
	 */
	public void clearObjects()
	{
		objects.clear();
	}

	/**
	 * Inserts the gameobject at the specified index of the list
	 * 
	 * @param object
	 *          - gameobject to insert
	 * @param index
	 *          - position to insert it at
	 */
	public void insertObject(GameObject object, int index)
	{
		objects.add(index, object);

	}

	/**
	 * Adds a gameobject to the end of the list
	 * 
	 * @param object
	 *          - gameobject to add
	 */
	public void addObject(GameObject object)
	{
		objects.add(object);

	}

	/**
	 * Removes a gameobject from the list
	 * 
	 * @param object
	 *          - gameobject to remove
	 */
	public void removeObject(GameObject object)
	{

		toRemove.add(object);
	}

	/**
	 * Gets the gameobject list Used for collision detection primarily
	 */
	public LinkedList<GameObject> getObjects()
	{
		return objects;
	}

	public void setSceneWindow(Rectangle sceneWindow)
	{
		this.sceneWindow = sceneWindow;
	}

	public void setRemovalConditions(List<RemovalConditions> conditions)
	{
		this.conditions = conditions;
	}
}
