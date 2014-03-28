/**
 * 
 */
package com.hh.sound;

import java.io.*;

import sun.audio.*;

/**
 * @author blinginbeckwith
 *
 */
public class SoundManager
{
	@SuppressWarnings("restriction")
	private AudioClip themesong;
	
	public enum SoundFile{
		Theme
	}

	public SoundManager()
	{
		try
		{
			themesong = new AudioClip(SoundFile.Theme, "/sound/music/themesong.wav");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void playAudioClip(SoundFile sound)
	{
		switch(sound)
		{
		case Theme:
			themesong.playClip();
			break;
			
		}
	}
	
	public class AudioClip
	{
		private SoundFile sound;
		private String path;
		private boolean fileLoaded;
		private AudioStream audioStream;
		@SuppressWarnings("restriction")
		public AudioClip(SoundFile sound, String path) throws IOException
		{
			this.sound = sound;
			this.path = path;
			try
			{
				InputStream in = getClass().getResourceAsStream(path);
				audioStream = new AudioStream(in);
				
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("restriction")
		public void playClip(){
			 AudioPlayer.player.start(audioStream);
			 
		}
	}
	
}
