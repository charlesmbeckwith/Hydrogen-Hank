package com.hh.framework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.hh.framework.Score.ScoreType;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Mar. 11, 2014 
 * Last Updated : Mar. 19, 2014 
 * Purpose: Loads, saves, and sorts the scores for display
 * 
 * @author Mark Schlottke
 */
public class ScoreKeeper
{
	private static String path = "highscores.sav";
	private static List<Score> scores;

	public ScoreKeeper()
	{
		scores = loadScores();
	}

	public static void addScore(Score score)
	{
		scores.add(score);
	}

	public void removeScore(Score score)
	{
		scores.remove(score);
	}

	public List<Score> getScores()
	{
		return scores;
	}

	public List<Score> getScores(ScoreType type)
	{
		List<Score> scoreList = new ArrayList<Score>();
		for (Score score : scores)
		{
			if (score.getType() == type)
				scoreList.add(score);
		}

		return scoreList;
	}

	public void saveScores()
	{
		if (scores == null)
			scores = new ArrayList<Score>();

		// serialize the List
		try (OutputStream file = new FileOutputStream(path);
		    OutputStream buffer = new BufferedOutputStream(file);
		    ObjectOutput output = new ObjectOutputStream(buffer);)
		{
			output.writeObject(scores);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void newScore(int overall, int altitude, int time)
	{
		addScore(new Score(overall, ScoreType.OVERALL));
		addScore(new Score(altitude, ScoreType.ALTITUDE));
		addScore(new Score(time, ScoreType.TIME));
	}

	@SuppressWarnings("unchecked")
	public List<Score> loadScores()
	{
		List<Score> scores = new ArrayList<Score>();

		// deserialize the file
		try (InputStream file = new FileInputStream(path);
		    InputStream buffer = new BufferedInputStream(file);
		    ObjectInput input = new ObjectInputStream(buffer);)
		{
			// deserialize the List
			scores = (List<Score>) input.readObject();

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			saveScores();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return scores;
	}
}
