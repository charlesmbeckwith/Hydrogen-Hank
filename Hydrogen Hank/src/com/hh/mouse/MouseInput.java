package com.hh.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.hh.Game;
import com.hh.framework.GameObject;
import com.hh.objects.MenuButton;
import com.hh.states.TitleMenuState;

public class MouseInput extends MouseAdapter {
	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		if (Game.manager.STATES.getFirst().getClass() == TitleMenuState.class) {
			for (GameObject go : TitleMenuState.handler.getObjects()) {
				if (go.getClass() == MenuButton.class) {
					MenuButton button = (MenuButton) go;
					if (button.SELECTED) {
						switchMenuButton(button);
					}
				}
			}
		}
	}

	public void switchMenuButton(MenuButton button) {

		switch (button.getButtonID()) {
		case NEWGAME:
			System.out.println("Newgame");
			break;
		case HIGHSCORE:
			System.out.println("Highscore");
			break;
		case CREDITS:
			System.out.println("Credits");
			break;

		}
	}

}
