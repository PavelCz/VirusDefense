package towerDefense.towers;

import towerDefense.Gameplay;
import engine.Enemy;
import engine.graphics.Sprite;

public class LongerShootingTower extends ShootingTower {
	private final int shootingDuration;
	private Enemy currentlyAttacking = null;

	public LongerShootingTower(float x, float y, Sprite sprite, Gameplay game, int shootingInterval, float damage, int shootingDuration) {
		super(x, y, sprite, game, shootingInterval, damage);
		this.shootingDuration = shootingDuration;
		this.delta = 0;
	}

	@Override
	public void update(int delta) {
		this.delta -= delta;
		if (this.currentlyAttacking != null) {
			this.shootAt(this.currentlyAttacking);
			if (this.currentlyAttacking.getHealth() <= 0) {
				this.currentlyAttacking = null;
				this.delta = this.shootingInterval;
			}
		}

		if (this.delta <= 0) {
			Enemy enemy = this.getEnemyInRange();
			if (enemy != null) {
				if (this.currentlyAttacking == null) {
					this.currentlyAttacking = enemy;
					this.delta = this.shootingDuration;
					this.game.getSoundHandler().play("shotT1");
				} else {
					this.currentlyAttacking = null;
					this.delta = this.shootingInterval;
				}
			}
		}

	}

	@Override
	public Tower clone() {
		return new LongerShootingTower(this.x, this.y, this.sprite.clone(), this.game, this.shootingInterval, this.damage,
				this.shootingDuration);
	}

	protected void shootAt(Enemy enemy) {
		if (this.inRange(enemy)) {
			enemy.setHealth(enemy.getHealth() - this.damage);
			if (enemy.getHealth() <= 0) {
				if (!enemy.isDead()) {
					this.game.getPlayer().addMoney(enemy.getMoney());
					enemy.setDead();
				}
			}

		}
	}

}
