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
	private AudioClip themesong, explosion, fuse, blow, pop, scream, caww,
			caww2, helicopter, hankstartsound;

	public enum SoundFile
	{
		Theme, explosion, fuse, blow, pop, scream, caww, helicopter, hank;
	}

	public SoundManager()
	{
		try
		{
			themesong = new AudioClip(SoundFile.Theme,
					"/sound/music/themesong.wav");
			themesong.setLooping(true);
			
			explosion = new AudioClip(SoundFile.explosion,
					"/sound/fx/explosion.wav");
			fuse = new AudioClip(SoundFile.fuse, "/sound/fx/fuse.wav");
			
			//Create balloon blow up soundclip
			blow = new AudioClip(SoundFile.blow,
					"/sound/fx/balloonblowingup.wav");
			blow.setLength(2);
			//Create pop soundclip
			pop = new AudioClip(SoundFile.pop, "/sound/fx/balloonpop.wav");
			
			//Create scream soundclip
			scream = new AudioClip(SoundFile.scream, "/sound/fx/scream.wav");
			
			//Create two bird sound audio clips
			caww = new AudioClip(SoundFile.caww, "/sound/fx/caww.wav");
			caww2 = new AudioClip(SoundFile.caww, "/sound/fx/caww2.wav");
			//Create helicopter sound and set length to 1
			helicopter = new AudioClip(SoundFile.helicopter,
					"/sound/fx/helicopter.wav");
			helicopter.setLength(1);
			
			hankstartsound = new AudioClip(SoundFile.hank, "/sound/fx/bitchin.wav");
			

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playAudioClip(SoundFile sound)
	{
		switch (sound)
		{
		case Theme:
			themesong.playClip();
			break;
		case explosion:
			explosion.playClip();
			break;
		case fuse:
			fuse.playClip();
			break;
		case blow:
			blow.playClip();
			break;
		case pop:
			pop.playClip();
			break;
		case scream:
			scream.playClip();
			break;
		case caww:
			//Randomly choose between playing the first or second bird sound
			if (Game.Rand.nextBoolean())
			{
				if (!caww2.isPlaying())
					caww.playClip();
			} else
			{
				if (!caww.isPlaying())
					caww2.playClip();
			}
			break;
		case helicopter:
			helicopter.playClip();
			break;
		case hank:
			hankstartsound.playClip();
		default:
			break;

		}
	}

	public class AudioClip implements Runnable
	{
		@SuppressWarnings("unused")
		private SoundFile sound;
		private String path;
		@SuppressWarnings("unused")
		private boolean fileLoaded;
		private boolean loop = false;
		private boolean lengthSet = false;
		private int length;
		private boolean playing = false;

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
				Thread t = new Thread(this);
				t.start();
				playing = true;
			}

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
				while (nBytesRead != -1)
				{
					try
					{
						nBytesRead = audioInputStream.read(abData, 0,
								abData.length);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					if (nBytesRead >= 0)
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
					counter++;
					try
					{
						nBytesRead = audioInputStream.read(abData, 0,
								abData.length);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					if (nBytesRead >= 0)
					{
						@SuppressWarnings("unused")
						int nBytesWritten = line.write(abData, 0,
								nBytesRead);
					}
				}
			}
			line.drain();
			line.close();
			if (loop)
				playSound();
			else
				playing = false;
		}

	}

}
