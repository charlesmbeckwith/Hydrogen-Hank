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
import java.util.LinkedList;
import java.util.List;

import com.hh.framework.Score.ScoreType;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Mar. 11, 2014 Last Updated : Mar. 19, 2014 Purpose: Loads, saves,
 * and sorts the scores for display
 * 
 * @author Mark Schlottke, Charlie Beckwith
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
		sortSweep();
	}

	public static void sortSweep()
	{
		if (scores.isEmpty())
			return;
		int length = scores.size();
		sortList(ScoreType.OVERALL, 0, length - 1);
		sortList(ScoreType.ALTITUDE, 0, length - 1);
		sortList(ScoreType.TIME, 0, length - 1);
	}

	public void removeScore(Score score)
	{
		scores.remove(score);
	}

	public List<Score> getScores()
	{
		return scores;
	}

	/* quick-sort implementation for sorting high-scores */
	public static void sortList(ScoreType type, int low, int high)
	{
		int i = low, j = high;
		Score pivot = scores.get(low + (high - low) / 2);
		while (i <= j)
		{
			while (scores.get(i).getValue() > pivot.getValue())
			{
				i++;
			}

			while (scores.get(j).getValue() < pivot.getValue())
			{
				j--;
			}
			if (i <= j)
			{
				swapScore(i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			sortList(type, low, j);
		if (i < high)
			sortList(type, i, high);
	}

	/* Element swap method used for quick-sort implementation */
	public static void swapScore(int i, int j)
	{
		Score temp = scores.get(i);
		scores.set(i, scores.get(j));
		scores.set(j, temp);
	}

	public List<Score> getScores(ScoreType type)
	{
		List<Score> scoreList = new ArrayList<Score>();

		for (Score score : scores)
		{
			if (score.getType() == type)
			{
				scoreList.add(score);
			}
		}
		return scoreList;
	}

	public static void printLinkedList(LinkedList<Score> scores)
	{
		System.out.print("{ ");
		for (Score score : scores)
		{
			System.out.print(score.getValue() + ",");
		}
		System.out.print("}\n");
	}

	public static void resetScores()
	{
		scores.clear();
		System.out.println("Score size = " + scores.size());
		saveScores();

	}

	public static void saveScores()
	{
		if (scores == null)
			scores = new ArrayList<Score>();

		// serialize the List
		try (OutputStream file = new FileOutputStream(path);
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);)
		{
			output.writeObject(scores);
		} catch (IOException e)
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

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			saveScores();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return scores;
	}
}
