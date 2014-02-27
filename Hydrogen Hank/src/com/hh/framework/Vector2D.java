package com.hh.framework;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 7, 2014
 * Purpose: Defines a Vector2D object
 * Primarily used for gameobject velocities
 * 
 * @author Mark Schlottke
 */
public class Vector2D
{
  public float DX, DY;

  /**
   * Initializes an zero vector
   */
  public Vector2D()
  {
    DX = 0;
    DY = 0;
  }

  /**
   * Initilizes a general 2D vector
   * @param dx
   * @param dy
   */
  public Vector2D(float dx, float dy)
  {
    DX = dx;
    DY = dy;
  }

  /**
   * Gets the magnitude of the vector
   * @return
   */
  public float magnitude()
  {
    return (float) (Math.sqrt(DX * DX + DY * DY));
  }

  /**
   * Adds the given vector to the class' vector
   * @param v
   */
  public void add(Vector2D v)
  {
    DX += v.DX;
    DY += v.DY;
  }

  /**
   * Subtracts the given vector to the class' vector
   * @param v
   */
  public void subtract(Vector2D v)
  {
    DX -= v.DX;
    DY -= v.DY;
  }

  /**
   * Sets the vector to its unit vector
   */
  public void unitVector()
  {
    float mag = magnitude();
    DX = DX / mag;
    DY = DY / mag;
  }

  /**
   * Scales to vector by the given amount
   * @param scale
   */
  public void scale(float scale)
  {
    DX = DX * scale;
    DY = DY * scale;
  }
}
