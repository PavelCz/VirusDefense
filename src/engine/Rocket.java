package engine;

import towerDefense.Gameplay;
import engine.graphics.Sprite;

public class Rocket extends Projectile implements Drawable {
	private Enemy enemy;

	public Rocket(float x, float y, int bombRadius, float damage, Gameplay game,
			float enemyX, float enemyY, Enemy enemy) {
		super(x, y);
		this.Radius = bombRadius;
		this.damage = damage;
		this.game = game;
		this.targetX = enemyX;
		this.targetY = enemyY;
		this.speed = 0.1f;
		this.velocity = new MyVector2f(enemyX - x, enemyY - y);
		this.velocity.setLength(speed);
		this.sprite = new Sprite("shoot/Frame0001.png", 0.05f);
		this.enemy = enemy;
	}

	public void update(int delta) {
		this.x += velocity.getX() * delta;
		this.y += velocity.getY() * delta;

		if (this.velocity.getX() >= 0 && this.velocity.getY() >= 0) {
			if (this.x >= targetX || this.y >= targetY) {
				fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() <= 0) {
			if (this.x <= targetX || this.y <= targetY) {
				fire();
			}
		} else if (this.velocity.getX() >= 0 && this.velocity.getY() <= 0) {
			if (this.x >= targetX || this.y <= targetY) {
				fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() >= 0) {
			if (this.x <= targetX || this.y >= targetY) {
				fire();
			}
		}

	}


	public void fire() {

		for (Enemy bombedEnemy : this.game.getEnemies()) {
			float bombedEnemyX = bombedEnemy.getX();
			float bombedEnemyY = bombedEnemy.getY();
			float bombedDeltaX = bombedEnemyX - this.x;
			float bombedDeltaY = bombedEnemyY - this.y;

			float bombDistance = (float) Math.sqrt(bombedDeltaX * bombedDeltaX
					+ bombedDeltaY * bombedDeltaY);
			if (bombDistance < this.Radius + bombedEnemy.getRadius()) {
				bombedEnemy.setHealth(bombedEnemy.getHealth() - this.damage);
				if (bombedEnemy.getHealth() <= 0) {
					this.game.getPlayer().addMoney(bombedEnemy.getMoney());
				}
			}
		}
		this.game.getSoundHandler().play("explode");
		this.game.getProjectiles().remove(this);

		
	}

}
