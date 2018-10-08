package com.hh.framework;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 7, 2014 
 * Last Updated : Feb. 7, 2014 
 * Purpose: Defines a Vector2D object Primarily
 * used for gameobject velocities
 * 
 * @author Mark Schlottke
 */
public class Vector2D
{
	public float dx, dy;

	/**
	 * Initializes an zero vector
	 */
	public Vector2D()
	{
		this.dx = 0;
		this.dy = 0;
	}

	/**
	 * Initilizes a general 2D vector
	 * 
	 * @param dx
	 * @param dy
	 */
	public Vector2D(float dx, float dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	/**
	 * Gets the magnitude of the vector
	 * 
	 * @return
	 */
	public float magnitude()
	{
		return (float) (Math.sqrt(this.dx * this.dx + this.dy * this.dy));
	}

	/**
	 * Adds the given vector to the class' vector
	 * 
	 * @param v
	 */
	public void add(Vector2D v)
	{
		this.dx += v.dx;
		this.dy += v.dy;
	}

	/**
	 * Subtracts the given vector to the class' vector
	 * 
	 * @param v
	 */
	public void subtract(Vector2D v)
	{
		this.dx -= v.dx;
		this.dy -= v.dy;
	}

	/**
	 * Sets the vector to its unit vector
	 */
	public void unitVector()
	{
		float mag = magnitude();
		this.dx = this.dx / mag;
		this.dy = this.dy / mag;
	}

	/**
	 * Scales to vector by the given amount
	 * 
	 * @param scale
	 */
	public void scale(float scale)
	{
		this.dx = this.dx * scale;
		this.dy = this.dy * scale;
	}
}
