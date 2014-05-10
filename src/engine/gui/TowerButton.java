package engine.gui;

import towerDefense.Gameplay;
import towerDefense.towers.Tower;
import engine.graphics.Sprite;

public class TowerButton extends Button {
	private Tower tower;
	private Gameplay game;

	public TowerButton(float x, float y, String unclickedButtonPath, String clickedButtonPath, Tower tower, Gameplay game) {
		super(x, y, unclickedButtonPath, clickedButtonPath);
		this.tower = tower;
		this.game = game;
	}

	@Override
	public void draw() {
		super.draw();
		Sprite s = this.tower.getSprite();
		s.setAlpha(0.8f);
		float scale = 0.9f;
		s.draw(this.x + (this.collisionWidth - s.getWidth() * scale) / 2 * Gameplay.GLOBAL_GUI_SCALE, this.y
				+ (this.collisionHeight - s.getHeight() * scale) / 2 * Gameplay.GLOBAL_GUI_SCALE, scale * Gameplay.GLOBAL_GUI_SCALE);
	}

	@Override
	public void onClick() {
		super.onClick();
		this.game.setCurrentTower(this.tower);
		this.game.getCurrentTower().getSprite().setAlpha(0.5f);

	}

	public Tower getTower() {
		return this.tower;
	}

}
