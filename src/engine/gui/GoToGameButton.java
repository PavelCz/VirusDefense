package engine.gui;

import towerDefense.Gameplay;
import towerDefense.TowerDefense;

public class GoToGameButton extends ClickableText {
	private TowerDefense towerDefense;

	public GoToGameButton(float x, float y, String text, TowerDefense towerDefense) {
		super(x, y, text, Gameplay.GLOBAL_GUI_SCALE, towerDefense.getGameplay(), false);
		this.towerDefense = towerDefense;

	}

	@Override
	public void onRelease() {
		super.onRelease();
		this.towerDefense.setMode(TowerDefense.MODE_GAME);
	}
}
