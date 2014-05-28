package engine.gui;

import towerDefense.Gameplay;

public class GoToSettingsButton extends ClickableText {

	public GoToSettingsButton(float x, float y, String text, Gameplay game) {
		super(x, y, text, Gameplay.GLOBAL_GUI_SCALE, game, false);
	}

}
