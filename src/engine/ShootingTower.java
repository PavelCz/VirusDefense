package engine;

import engine.graphics.Sprite;

public class ShootingTower extends Tower {
	public ShootingTower(float x, float y, Sprite sprite) {
		super(x * 50, y * 50, 200, 200, new ShootingWeapon());
	this.sprite = sprite;
	}

	@Override
	public void draw() {
		this.sprite.draw(this.x, this.y);

	}
}
