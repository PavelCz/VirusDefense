package engine;

import engine.graphics.Sprite;

public class LongerShootingTower extends ShootingTower {
	private final int shootingDuration;
	private boolean betweenShots = true;

	public LongerShootingTower(float x, float y, Sprite sprite, Game game, int shootingInterval, float damage, int shootingDuration) {
		super(x, y, sprite, game, shootingInterval, damage);
		this.shootingDuration = shootingDuration;
	}

	@Override
	public void update(int delta) {
		this.delta -= delta;
		if (this.delta <= 0) {
			if (this.inRangeOfAnyEnemy()) {
				if (this.betweenShots) {
					this.betweenShots = false;
					this.delta = this.shootingDuration;
				} else {
					this.betweenShots = true;
					this.delta = this.shootingInterval;
				}
			}
		}
		if (!this.betweenShots) {
			this.shoot();
		}

	}

	@Override
	public Tower clone() {
		return new LongerShootingTower(this.x, this.y, this.sprite.clone(), this.game, this.shootingInterval, this.damage,
				this.shootingDuration);
	}

}
