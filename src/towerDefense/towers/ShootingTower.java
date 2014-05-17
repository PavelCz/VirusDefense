package towerDefense.towers;

import towerDefense.Gameplay;
import engine.Enemy;
import engine.graphics.Sprite;

public class ShootingTower extends Tower {
	protected int delta;
	protected final int shootingInterval;

	public ShootingTower(float x, float y, Sprite sprite, Gameplay game, int shootingInterval, float damage) {
		super(x, y, 100, 128, damage, game);

		this.sprite = sprite;
		this.shootingInterval = shootingInterval;
		this.delta = this.shootingInterval;
	}

	@Override
	public Tower clone() {
		return new ShootingTower(this.x, this.y, this.sprite.clone(), this.game, this.shootingInterval, this.damage);
	}

	@Override
	public void draw() {
		if (this.building) {
			float scale = (this.buildingTime - this.buildingTimer) / buildingTime;
			float size = (Gameplay.DEFAULT_SIZE - this.sprite.getWidth() * scale) / 2;
			this.sprite.draw((this.x * Gameplay.DEFAULT_SIZE + size - Gameplay.getCameraX()) * Gameplay.GLOBAL_GAME_SCALE, (this.y
					* Gameplay.DEFAULT_SIZE + size - Gameplay.getCameraY())
					* Gameplay.GLOBAL_GAME_SCALE, Gameplay.GLOBAL_GAME_SCALE * scale);
		} else {
			this.sprite.draw(this.x * Gameplay.SIZE - Gameplay.getCameraX(), this.y * Gameplay.SIZE - Gameplay.getCameraY(),
					Gameplay.GLOBAL_GAME_SCALE);
		}
	}

	@Override
	public void update(int delta) {
		super.update(delta);
		this.delta -= delta;
		if (this.delta <= 0) {
			this.delta = this.shootingInterval;
			this.shoot();
		}
	}

	@Override
	public void shoot() {
		boolean done = false;
		for (Enemy enemy : this.game.getEnemies()) {
			if (enemy != null && !done) {
				float enemyX = enemy.getX();
				float enemyY = enemy.getY();
				float deltaX = enemyX - (this.getX() * Gameplay.DEFAULT_SIZE + Gameplay.DEFAULT_SIZE / 2);
				float deltaY = enemyY - (this.getY() * Gameplay.DEFAULT_SIZE + Gameplay.DEFAULT_SIZE / 2);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (distance < this.radius + enemy.getRadius()) {
					enemy.setHealth(enemy.getHealth() - this.damage);
					if (enemy.getHealth() <= 0) {
						this.game.getPlayer().addMoney(enemy.getMoney());
					}
					done = true;
					this.game.getSoundHandler().play("shotT1");
				}
			}
		}

	}

}
