/**
 * COSC3550 Spring 2014 Created : March 27, 2014 Last Updated : Mar. 31, 2014
 * Purpose: Manages Sound Files and plays sound clips
 * 
 * @author Charlie Beckwith
 */
package com.hh.sound;

import java.io.*;

import javax.sound.sampled.*;

import com.hh.Game;
import com.hh.framework.DebugManager;

public class SoundManager
{
	private static AudioClip themesong, explosion, fuse, blow, pop, scream,
			caww, caww2, helicopter, hankstartsound, hydrogen, hydrogentank;

	public enum SoundFile
	{
		Theme, explosion, fuse, blow, pop, scream, caww, helicopter, hank, hydrogen, tank;
	}

	public SoundManager()
	{
		try
		{
			themesong = new AudioClip(SoundFile.Theme,
					"/sound/music/theme2.wav");
			themesong.setLooping(true);

			explosion = new AudioClip(SoundFile.explosion,
					"/sound/fx/explosion.wav");
			fuse = new AudioClip(SoundFile.fuse, "/sound/fx/fuse.wav");

			// Create balloon blow up soundclip
			blow = new AudioClip(SoundFile.blow,
					"/sound/fx/balloonblowingup.wav");
			blow.setLength(2);
			// Create pop soundclip
			pop = new AudioClip(SoundFile.pop, "/sound/fx/balloonpop.wav");

			// Create scream soundclip
			scream = new AudioClip(SoundFile.scream, "/sound/fx/scream.wav");

			// Create two bird sound audio clips
			caww = new AudioClip(SoundFile.caww, "/sound/fx/caww.wav");
			caww2 = new AudioClip(SoundFile.caww, "/sound/fx/caww2.wav");
			// Create helicopter sound and set length to 1
			helicopter = new AudioClip(SoundFile.helicopter,
					"/sound/fx/helicopter.wav");
			helicopter.setLength(1);

			hankstartsound = new AudioClip(SoundFile.hank,
					"/sound/fx/bitchin.wav");
			
			hydrogentank = new AudioClip(SoundFile.tank, "/sound/fx/hydrogentank.wav");

			hydrogen = new AudioClip(SoundFile.hydrogen,
					"/sound/fx/collecthydrogen2.wav");
			hydrogen.setOverlap(true);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void playAudioClip(SoundFile sound)
	{
		AudioClip aC = getAudioClip(sound);
		if (aC != null)
		{
			aC.playClip();
		} else
		{
			if (DebugManager.debugMode)
				System.out.println("AudioClip " + sound + " returned null");
		}
	}

	public void stopAudioClip(SoundFile sound)
	{

		AudioClip aC = getAudioClip(sound);
		if (aC != null)
		{
			aC.stopClip();
		}
	}

	private AudioClip getAudioClip(SoundFile sound)
	{
		switch (sound)
		{
		case Theme:
			return themesong;

		case explosion:
			return explosion;

		case fuse:
			return fuse;

		case blow:
			return blow;

		case pop:
			return pop;

		case scream:
			return scream;

		case caww:
			// Randomly choose between playing the first or second bird sound
			if (Game.Rand.nextBoolean())
			{
				if (!caww2.isPlaying())
					return caww;
			} else
			{
				if (!caww.isPlaying())
					return caww2;
			}
			break;
		case helicopter:
			return helicopter;

		case hank:
			return hankstartsound;
		case hydrogen:
			return hydrogen;
		case tank:
			return hydrogentank;
		default:
			return null;

		}
		return null;
	}

	public class AudioClip implements Runnable
	{
		private SoundFile sound;
		private String path;
		@SuppressWarnings("unused")
		private boolean fileLoaded;
		private boolean loop = false;
		private boolean lengthSet = false;
		private int length;
		private boolean playing = false;
		private Thread t;
		private boolean interrupt = false;
		private boolean overlap = false;

		public AudioClip(SoundFile sound, String path) throws IOException
		{
			this.sound = sound;
			this.path = path;
		}

		public void setLength(int length)
		{
			lengthSet = true;
			this.length = length;

		}

		public void setLooping(boolean loop)
		{
			this.loop = loop;
		}

		public void playClip()
		{
			if (!playing && !DebugManager.muteSound)
			{
				t = new Thread(this);
				t.start();
				if (!overlap)
					playing = true;
			}

		}

		public void setOverlap(boolean bool)
		{
			overlap = bool;
		}

		public void stopClip()
		{
			interrupt = true;
			// t.interrupt();
		}

		public boolean isPlaying()
		{
			return playing;
		}

		@Override
		public void run()
		{
			playSound();

		}

		public SoundFile getSoundType()
		{
			return sound;
		}

		private void playSound()
		{

			AudioInputStream audioInputStream = null;
			try
			{
				audioInputStream = AudioSystem
						.getAudioInputStream(getClass().getResource(path));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			AudioFormat audioFormat = audioInputStream.getFormat();
			SourceDataLine line = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					audioFormat);
			try
			{
				line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(audioFormat);
			} catch (LineUnavailableException e)
			{
				e.printStackTrace();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			line.start();
			int nBytesRead = 0;
			byte[] abData = new byte[128000];

			if (!lengthSet)
			{
				// int counter = 0;
				while (nBytesRead != -1)
				{
					// System.out.println(sound + " " + counter);
					// counter++;
					try
					{
						nBytesRead = audioInputStream.read(abData, 0,
								abData.length);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					if (nBytesRead >= 0 && !interrupt)
					{
						@SuppressWarnings("unused")
						int nBytesWritten = line.write(abData, 0,
								nBytesRead);
					}
				}
			} else if (lengthSet)
			{
				int counter = 0;
				while (nBytesRead != -1 && counter < length)
				{
					// System.out.println(sound + " " + counter);
					counter++;
					try
					{

						nBytesRead = audioInputStream.read(abData, 0,
								abData.length);

					} catch (IOException e)
					{
						e.printStackTrace();
					}

					if (nBytesRead >= 0 && !interrupt)
					{
						@SuppressWarnings("unused")
						int nBytesWritten = line.write(abData, 0,
								nBytesRead);
					}
				}
			}
			line.drain();
			line.close();
			if (loop && !interrupt)
				playSound();
			else
			{
				playing = false;
				interrupt = false;
			}
		}

	}

}
