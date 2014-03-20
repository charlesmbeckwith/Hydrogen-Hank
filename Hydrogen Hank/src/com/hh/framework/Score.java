package com.hh.framework;

import java.io.Serializable;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Mar. 11, 2014 
 * Last Updated : Mar. 19, 2014 
 * Purpose: Defines a score 
 * 
 * @author Mark Schlottke
 */
public class Score implements Serializable
{
	private static final long serialVersionUID = 8040673039007411017L;

	public enum ScoreType
	{
		OVERALL, ALTITUDE, TIME
	}

	private int value;
	private ScoreType type;

	public Score(int value, ScoreType type)
	{
		this.value = value;
		this.type = type;
	}

	public int getValue()
	{
		return this.value;
	}

	public ScoreType getType()
	{
		return this.type;
	}
}
