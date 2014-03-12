package engine;

import engine.graphics.Sprite;

public class ShootingTower extends Tower {
	public ShootingTower(float x, float y, Sprite sprite) {
		super(x * 50, y * 50, 200, 200, new ShootingWeapon());
		this.sprite = sprite;
	}

	@Override
	public Tower clone() {
		return new ShootingTower(this.x, this.y, this.sprite.clone());
	}

	@Override
	public void draw() {
		this.sprite.draw(x * 50, y * 50);

	}
}
