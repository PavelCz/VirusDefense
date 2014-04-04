package engine;

import engine.graphics.Sprite;

public class ShootingTower extends Tower {
	protected int delta;
	protected final int shootingInterval;

	public ShootingTower(float x, float y, Sprite sprite, Game game, int shootingInterval, float damage) {
		super(x * 50, y * 50, 100, 100, damage, game);
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
		this.sprite.draw(this.x * 50, this.y * 50);

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
				float deltaX = enemyX - (this.getX() * 50 + 25);
				float deltaY = enemyY - (this.getY() * 50 + 25);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (distance < this.radius + enemy.getRadius()) {
					enemy.setHealth(enemy.getHealth() - this.damage);
					if (enemy.getHealth() <= 0) {
						this.game.getPlayer().addMoney(enemy.getMoney());
					}
					done = true;
				}
			}
		}

	}

	protected boolean inRangeOfAnyEnemy() {
		boolean done = false;
		for (Enemy enemy : this.game.getEnemies()) {
			if (enemy != null && !done) {
				float enemyX = enemy.getX();
				float enemyY = enemy.getY();
				float deltaX = enemyX - (this.getX() * 50 + 25);
				float deltaY = enemyY - (this.getY() * 50 + 25);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (distance < this.radius + enemy.getRadius()) {
					return true;
				}
			}
		}
		return false;
	}
}
