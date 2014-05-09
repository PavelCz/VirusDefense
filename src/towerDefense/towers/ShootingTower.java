package towerDefense.towers;

import towerDefense.Gameplay;
import engine.Enemy;
import engine.graphics.Sprite;

public class ShootingTower extends Tower {
	protected int delta;
	protected final int shootingInterval;

	public ShootingTower(float x, float y, Sprite sprite, Gameplay game,
			int shootingInterval, float damage) {
		super(x * Gameplay.SIZE, y * Gameplay.SIZE, 100, 100, damage, game);
		this.sprite = sprite;
		this.shootingInterval = shootingInterval;
		this.delta = this.shootingInterval;
	}

	@Override
	public Tower clone() {
		return new ShootingTower(this.x, this.y, this.sprite.clone(),
				this.game, this.shootingInterval, this.damage);
	}

	@Override
	public void draw() {
		this.sprite.draw(this.x * Gameplay.SIZE, this.y * Gameplay.SIZE);

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
	public void shoot() {
		boolean done = false;
		for (Enemy enemy : this.game.getEnemies()) {
			if (enemy != null && !done) {
				float enemyX = enemy.getX();
				float enemyY = enemy.getY();
				float deltaX = enemyX - (this.getX() * Gameplay.SIZE + Gameplay.SIZE/2);
				float deltaY = enemyY - (this.getY() * Gameplay.SIZE + Gameplay.SIZE/2);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY
						* deltaY);

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
