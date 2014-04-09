package engine.gui;

import engine.TowerDefense;

public class StartClickable extends ClickableText {
	private TowerDefense towerDefense;

	public StartClickable(float x, float y, TowerDefense towerDefense) {
		super(x, y, "Start Game");
		this.towerDefense = towerDefense;
	}

	@Override
	public void onClick() {
		super.onClick();
		this.towerDefense.setMode(TowerDefense.MODE_GAME);
	}
}
