package com.hh.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.hh.Game;
import com.hh.framework.GameObject;
import com.hh.framework.gamestate.states.TitleMenuAnimState;
import com.hh.framework.gamestate.states.TitleMenuState;
import com.hh.objects.MenuButton;

public class MouseInput extends MouseAdapter {
	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		if (Game.manager.STATES.getFirst().getClass() == TitleMenuAnimState.class) {
			for (GameObject go : TitleMenuAnimState.handler.getObjects()) {
				if (go.getClass() == MenuButton.class) {
					MenuButton button = (MenuButton) go;
					if (button.SELECTED) {
						switchMenuButton(button);
					}
				}
			}
		}
		else if(Game.manager.STATES.getFirst().getClass() == TitleMenuState.class) {
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

	/**
	 * Switches game state depending on which button was pushed
	 * @param button
	 */
	public void switchMenuButton(MenuButton button) {
		switch (button.getButtonID()) {
		case NEWGAME:
			Game.manager.STATES.push(Game.playState);
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
