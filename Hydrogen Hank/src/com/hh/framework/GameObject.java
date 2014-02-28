package com.hh.framework;

import java.awt.Graphics;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 11, 2014
 * Purpose: Defines the generic GameObject super class
 * 
 * @author Mark Schlottke, Charlie Beckwith
 */
public abstract class GameObject
{
  public enum ObjectID
  {
    Background, BackgroundElement, Tile, Button, Player, Enemy, Powerup
  }
  
  public enum ObjectLayer
  {
    background, middleground, foreground, hud, toplevel

  }

  protected float X, Y;
  protected int WIDTH, HEIGHT;
  protected Vector2D V;
  protected ObjectID ID;
  protected boolean ALIVE;
  protected ObjectLayer LAYER;

  /**
   * Creates a stationary gameobject
   * @param x - x position of the gameobject
   * @param y - y position of the gameobject
   * @param id - ObjectID of the gameobject
   */
  public GameObject(float x, float y, ObjectID id)
  {
    X = x;
    Y = y;
    V = new Vector2D();
    ID = id;
  }

  /**
   * Creates a stationary gameobject with an object layer
   * @param x - x position of the gameobject
   * @param y - y position of the gameobject
   * @param id - ObjectID of the gameobject
   * @param layer - ObjectLayer of the gameobject
   */
  public GameObject(float x, float y, ObjectID id, ObjectLayer layer)
  {
    X = x;
    Y = y;
    V = new Vector2D();
    ID = id;
  }

  /**
   * Creates a stationary gameobject
   * @param x - x position of the gameobject
   * @param y - y position of the gameobject
   * @param width - width of the gameobject
   * @param height - height of the gameobject
   * @param id - ObjectID of the gameobject
   */
  public GameObject(float x, float y, int width, int height, ObjectID id, ObjectLayer layer)
  {
    X = x;
    Y = y;
    WIDTH = width;
    HEIGHT = height;
    V = new Vector2D();
    ID = id;
    LAYER = layer;
  }

  /**
   * Creates a stationary gameobject
   * @param x - x position of the gameobject
   * @param y - y position of the gameobject
   * @param v - velocity vector of the gameobject
   * @param id - ObjectID of the gameobject
   */
  public GameObject(float x, float y, Vector2D v, ObjectID id, ObjectLayer layer)
  {
    X = x;
    Y = y;
    V = v;
    ID = id;
    LAYER = layer;
  }

  /**
   * Creates a stationary gameobject
   * @param x - x position of the gameobject
   * @param y - y position of the gameobject
   * @param width - width of the gameobject
   * @param height - height of the gameobject
   * @param v - velocity vector of the gameobject
   * @param id - ObjectID of the gameobject
   */
  public GameObject(float x, float y, int width, int height, Vector2D v, ObjectID id,
      ObjectLayer layer)
  {
    X = x;
    Y = y;
    WIDTH = width;
    HEIGHT = height;
    V = v;
    ID = id;
    LAYER = layer;
  }

  /**
   * Advances the gameobject position, rotation, and velocity
   */
  public abstract void tick();

  /**
   * Draws the gameobject with the given graphics object
   * @param g - Graphics object to draw with
   */
  public abstract void render(Graphics g);

  /**
   * Gets the current X position of the gameobject
   * @return - current x position
   */
  public float getX()
  {
    return X;
  }

  /**
   * Sets the current X position of the gameobject
   * @param x - the new x position
   */
  public void setX(float x)
  {
    X = x;
  }

  /**
   * Gets the current Y position of the gameobject
   * @return - current y position
   */
  public float getY()
  {
    return Y;
  }

  /**
   * Sets the current Y position of the gameobject
   * @param y - the new y position
   */
  public void setY(float y)
  {
    Y = y;
  }

  /**
   * Gets the width of the gameobject
   * @return
   */
  public int getWidth()
  {
    return WIDTH;
  }

  /**
   * Gets the height of the gameobject
   * @return
   */
  public int getHeight()
  {
    return HEIGHT;
  }

  /**
   * Gets the velocity vector of the gameobject
   * @return
   */
  public Vector2D getVelocity()
  {
    return V;
  }

  /**
   * Sets the width of the gameobject
   * @param v - new velocity vector
   */
  public void setVelocity(Vector2D v)
  {
    V = v;
  }

  /**
   * Kills the gameobject
   * Used to tag it for removal
   */
  public void Kill()
  {
    ALIVE = false;
  }

  /**
   * Determines if the gameobject is active
   * @return
   */
  public boolean isAlive()
  {
    return ALIVE;
  }

  /**
   * Gets the ObjectID of the gameobject
   * Used to determine the class of the object
   * @return
   */
  public ObjectID getID()
  {
    return ID;
  }

  /**
   * Getter for gameobject layer
   * @return returns layer of the gameobject
   */
  public ObjectLayer getLayer()
  {
    return LAYER;
  }
}
