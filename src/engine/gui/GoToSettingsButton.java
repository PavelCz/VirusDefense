package engine.gui;

import towerDefense.Gameplay;
import towerDefense.TowerDefense;

public class GoToSettingsButton extends ClickableText {

	public GoToSettingsButton(float x, float y, String text, TowerDefense towerDefense) {
		super(x, y, text, Gameplay.GLOBAL_GUI_SCALE, towerDefense.getGameplay(), false);
	}

}
