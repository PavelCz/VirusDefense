package towerDefense.towers;

import towerDefense.Gameplay;
import engine.Bomb;
import engine.Enemy;
import engine.graphics.Sprite;

public class BombTower extends Tower {
	protected int delta;
	protected final int shootingInterval;
	protected int bombRadius;

	public BombTower(float x, float y, Sprite sprite, Gameplay game, int shootingInterval, float damage, int bombRadius) {
		super(x * Gameplay.SIZE, y * Gameplay.SIZE, 100, 150, damage, game);
		this.sprite = sprite;
		this.shootingInterval = shootingInterval;
		this.delta = this.shootingInterval;
		this.bombRadius = bombRadius;
	}

	@Override
	public void draw() {
		this.sprite.draw(this.x * Gameplay.SIZE, this.y * Gameplay.SIZE, Gameplay.GLOBAL_GAME_SCALE);

	}

	@Override
	public void shoot() {
		boolean done = false;
		for (Enemy enemy : this.game.getEnemies()) {
			if (enemy != null && !done) {
				float enemyX = enemy.getX();
				float enemyY = enemy.getY();
				float deltaX = enemyX - (this.getX() * Gameplay.SIZE + Gameplay.SIZE / 2);
				float deltaY = enemyY - (this.getY() * Gameplay.SIZE + Gameplay.SIZE / 2);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (distance < this.radius + enemy.getRadius()) {
					Bomb b = new Bomb(this.x * Gameplay.SIZE + Gameplay.SIZE / 2, this.y * Gameplay.SIZE + Gameplay.SIZE / 2,
							this.bombRadius, this.damage, this.game, enemyX, enemyY);
					this.game.getProjectiles().add(b);

					done = true;
					this.game.getSoundHandler().play("shotT2");

				}
			}
		}

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
		return new BombTower(this.x, this.y, this.sprite.clone(), this.game, this.shootingInterval, this.damage, this.bombRadius);
	}

}
