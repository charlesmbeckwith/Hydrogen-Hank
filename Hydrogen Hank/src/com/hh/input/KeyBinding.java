package com.hh.input;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Feb. 28, 2014 
 * Purpose: Defines and loads the keybindings
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public enum KeyBinding
{
	INFLATE(KeyEvent.VK_W), DEFLATE(KeyEvent.VK_S), PAN_RIGHT(KeyEvent.VK_A), PAN_LEFT(KeyEvent.VK_D), FANON(
	    KeyEvent.VK_SPACE), PAUSE(KeyEvent.VK_P), RESTART(KeyEvent.VK_R), BLOWUP_BALLOON(
	    KeyEvent.VK_SPACE), KILL(KeyEvent.VK_K), POPALL(KeyEvent.VK_P);

	private static String controlsPath = "/config/controls.config";
	private int value;

	private KeyBinding(final int value)
	{
		this.value = value;
	}

	public void SET(int value)
	{
		this.value = value;
	}

	public int VALUE()
	{
		return value;
	}

	public static void LOAD_BINDINGS()
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new InputStreamReader(
			    KeyBinding.class.getResourceAsStream(controlsPath)));
			for (String line; (line = br.readLine()) != null;)
			{
				int newVal = -1;

				if (!line.isEmpty())
				{
					if (line.charAt(0) != '#')
					{
						try
						{
							newVal = parseKeyCode(line.substring(line.indexOf('=') + 1));

						}
						catch (Exception e)
						{
							e.printStackTrace();
							break;
						}

						if (newVal != -1)
						{
							if (line.contains("INFLATE_KEY"))
							{
								INFLATE.SET(newVal);
							}
							else if (line.contains("DEFLATE_KEY"))
							{
								DEFLATE.SET(newVal);
							}
							else if (line.contains("PAN_RIGHT_KEY"))
							{
								PAN_RIGHT.SET(newVal);
							}
							else if (line.contains("PAN_LEFT_KEY"))
							{
								PAN_LEFT.SET(newVal);
							}
							else if (line.contains("FANON_KEY"))
							{
								FANON.SET(newVal);
							}
							else if (line.contains("PAUSE_KEY"))
							{
								PAUSE.SET(newVal);
							}
							else if (line.contains("RESTART_KEY"))
							{
								RESTART.SET(newVal);
							}
							else if (line.contains("BLOWUP_BALLOON"))
							{
								BLOWUP_BALLOON.SET(newVal);
							}
						}
					}
				}

			}
			br.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static int parseKeyCode(String keyStr)
	{

		if (keyStr.length() == 1)
		{
			return keyStr.charAt(0);
		}
		else if (keyStr.equalsIgnoreCase("spacebar"))
		{
			return KeyEvent.VK_SPACE;
		}
		else if (keyStr.equalsIgnoreCase("up"))
		{
			return KeyEvent.VK_UP;
		}
		else if (keyStr.equalsIgnoreCase("down"))
		{
			return KeyEvent.VK_DOWN;
		}
		else if (keyStr.equalsIgnoreCase("left"))
		{
			return KeyEvent.VK_LEFT;
		}
		else if (keyStr.equalsIgnoreCase("right"))
		{
			return KeyEvent.VK_RIGHT;
		}
		else if (keyStr.equalsIgnoreCase("enter"))
		{
			return KeyEvent.VK_ENTER;
		}
		else
		{
			return -1;
		}
	}
}
