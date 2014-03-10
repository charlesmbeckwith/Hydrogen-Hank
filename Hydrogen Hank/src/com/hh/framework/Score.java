package com.hh.framework;

import java.io.Serializable;

public class Score implements Serializable{
	private static final long serialVersionUID = 8040673039007411017L;
	
	public enum ScoreType{
		OVERALL, ALTITUDE, TIME
	}
	
	private int value;
	private ScoreType type;

	public Score(int value, ScoreType type) {
		this.value = value;
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public ScoreType getType() {
		return type;
	}
}
