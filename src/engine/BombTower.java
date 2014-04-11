package engine;

import engine.graphics.Sprite;

public class BombTower extends Tower {
	protected int delta;
	protected final int shootingInterval;

	public BombTower(float x, float y, Sprite sprite, Gameplay game, int shootingInterval, float damage) {
		super(x * 50, y * 50, 100, 100, damage, game);
		this.sprite = sprite;
		this.shootingInterval = shootingInterval;
		this.delta = this.shootingInterval;
	}
	
	@Override
	public void draw() {
		this.sprite.draw(this.x * 50, this.y * 50);

	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int delta) {
		this.delta -= delta;
		if (this.delta <= 0) {
			this.delta = this.shootingInterval;
			this.shoot();
		}
	}

	@Override
	public Tower clone() {
		return new BombTower(this.x, this.y, this.sprite.clone(), this.game, this.shootingInterval, this.damage);
	}

}
