package engine.gui;

import engine.TowerDefense;

public class StartClickable extends ClickableText {
	private TowerDefense towerDefense;

	public StartClickable(float x, float y, TowerDefense towerDefense) {
		super(x, y, "Start new Game");
		this.towerDefense = towerDefense;
	}

	@Override
	public void onRelease() {
		super.onRelease();
		this.towerDefense.setMode(TowerDefense.MODE_GAME);
	}
}
