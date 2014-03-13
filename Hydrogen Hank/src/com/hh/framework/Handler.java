package com.hh.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
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
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<GameObject> toRemove = new ArrayList<GameObject>();
	private GameObject tempObj;
	private int[] counts;

	public Handler()
	{
		counts = new int[] { 0, 0, 0, 0, 0 };
	}

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
				float x = tempObj.getX();
				float y = tempObj.getY();
				float xOffset = tempObj.getWidth() / 2;
				float yOffset = tempObj.getHeight() / 2;

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
			switch (go.getLayer())
			{
			case background:
				counts[0] -= 1;
				break;
			case middleground:
				counts[1] -= 1;
				break;
			case foreground:
				counts[2] -= 1;
				break;
			case hud:
				counts[3] -= 1;
				break;
			case toplevel:
				counts[4] -= 1;
				break;
			}

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
		counts = new int[] { 0, 0, 0, 0, 0 };
	}

	/**
	 * Adds a gameobject to the list
	 * 
	 * @param object
	 *          - gameobject to add
	 */
	public void addObject(GameObject object)
	{
		int index = objects.size();

		switch (object.getLayer())
		{
		case background:
			index = counts[0];
			counts[0] += 1;
			break;
		case middleground:
			index = counts[0] + counts[1];
			counts[1] += 1;
			break;
		case foreground:
			index = counts[0] + counts[1] + counts[2];
			counts[2] += 1;
			break;
		case hud:
			index = counts[0] + counts[1] + counts[2] + counts[3];
			counts[3] += 1;
			break;
		case toplevel:
			index = counts[0] + counts[1] + counts[2] + counts[3] + counts[4];
			counts[4] += 1;
			break;
		}

		objects.add(index, object);
	}

	/**
	 * Removes a gameobject from the list
	 * 
	 * @param object
	 *          - gameobject to remove
	 */
	public void removeObject(GameObject object)
	{
		this.toRemove.add(object);
	}

	/**
	 * Gets the gameobject list Used for collision detection primarily
	 */
	public ArrayList<GameObject> getObjects()
	{
		return this.objects;
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
