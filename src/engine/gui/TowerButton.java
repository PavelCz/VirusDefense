package engine.gui;

import engine.Tower;
import engine.graphics.Sprite;

public class TowerButton extends Button {
	private Tower tower;

	public TowerButton(float x, float y, String unclickedButtonPath, String clickedButtonPath, Tower tower) {
		super(x, y, unclickedButtonPath, clickedButtonPath);
		this.tower = tower;
	}

	@Override
	public void draw() {
		super.draw();
		Sprite s = this.tower.getSprite();
		s.setAlpha(0.8f);
		float scale = 0.4f;
		s.draw(this.x + (this.collisionWidth - s.getWidth() * scale) / 2, this.y + (this.collisionHeight - s.getHeight() * scale) / 2,
				0, scale);
	}

	@Override
	public Tower getTower() {
		return this.tower;
	}

}
