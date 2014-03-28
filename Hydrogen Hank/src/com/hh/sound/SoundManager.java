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
	private AudioClip themesong, explosion,fuse;
	
	public enum SoundFile{
		Theme, explosion, fuse
	}

	public SoundManager()
	{
		try
		{
			themesong = new AudioClip(SoundFile.Theme, "/sound/music/themesong.wav");
			explosion= new AudioClip(SoundFile.explosion, "/sound/fx/explosion.wav");
			fuse = new AudioClip(SoundFile.fuse, "/sound/fx/fuse.wav");
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
		case explosion:
			explosion.playClip();
			break;
		case fuse:
			fuse.playClip();
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
			load();
		}
		
		@SuppressWarnings("restriction")
		public void playClip(){
			 AudioPlayer.player.start(audioStream);
			 load();
		}
		
		public void load()
		{
			InputStream in = getClass().getResourceAsStream(path);
			try
			{
				audioStream = new AudioStream(in);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
