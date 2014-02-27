package com.hh.keyboard;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public enum KeyBinding {
	INFLATE(KeyEvent.VK_W), DEFLATE(KeyEvent.VK_S), FANON(KeyEvent.VK_SPACE), PAUSE(
			KeyEvent.VK_P), RESTART(KeyEvent.VK_R);

	private int value;
	private static File controls = new File(System.getProperty("user.dir")
			+ "/res/config/controls.config");

	private KeyBinding(final int value) {
		this.value = value;
	}

	public void SET(int value) {
		this.value = value;
	}

	public int VALUE() {
		return value;
	}

	public static void LOAD_BINDINGS() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(controls));
			for (String line; (line = br.readLine()) != null;) {
				int newVal = -1;
				
				if (!line.isEmpty()) {
					if (line.charAt(0) != '#') {
						try {
							newVal = parseKeyCode(line.substring(line
									.indexOf('=') + 1));

						} catch (Exception e) {
							e.printStackTrace();
							break;
						}

						if (newVal != -1) {
							if (line.contains("INFLATE_KEY")) {
								INFLATE.SET(newVal);
							} else if (line.contains("DEFLATE_KEY")) {
								DEFLATE.SET(newVal);
							} else if (line.contains("FANON_KEY")) {
								FANON.SET(newVal);
							} else if (line.contains("PAUSE_KEY")) {
								PAUSE.SET(newVal);
							} else if (line.contains("RESTART_KEY")) {
								RESTART.SET(newVal);
							}
						}
					} 
				}

			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int parseKeyCode(String keyStr) {

		if (keyStr.length() == 1) {
			return keyStr.charAt(0);
		} else if (keyStr.equals("spacebar")) {
			return KeyEvent.VK_SPACE;
		} else if (keyStr.equals("up")) {
			return KeyEvent.VK_UP;
		} else if (keyStr.equals("down")) {
			return KeyEvent.VK_DOWN;
		} else if (keyStr.equals("left")) {
			return KeyEvent.VK_LEFT;
		} else if (keyStr.equals("right")) {
			return KeyEvent.VK_RIGHT;
		} else if (keyStr.equalsIgnoreCase("enter")) {
			return KeyEvent.VK_ENTER;
		} else {
			return -1;
		}
	}
}
