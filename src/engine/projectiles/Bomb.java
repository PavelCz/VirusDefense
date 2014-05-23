package engine.projectiles;

import towerDefense.Gameplay;
import engine.Drawable;
import engine.Enemy;
import engine.MyVector2f;
import engine.graphics.Sprite;

public class Bomb extends Projectile implements Drawable {

	public Bomb(float x, float y, int bombRadius, float damage, Gameplay game, float enemyX, float enemyY) {
		super(x, y);
		this.Radius = bombRadius;
		this.damage = damage;
		this.game = game;
		this.targetX = enemyX;
		this.targetY = enemyY;
		this.speed = 0.2f;
		this.velocity = new MyVector2f(enemyX - x, enemyY - y);
		this.velocity.setLength(this.speed);
		this.sprite = new Sprite("shoot/Frame0001.png", 0.064f);
	}

	@Override
	public void update(int delta) {
		this.x += this.velocity.getX() * delta;
		this.y += this.velocity.getY() * delta;

		if (this.velocity.getX() >= 0 && this.velocity.getY() >= 0) {
			if (this.x >= this.targetX || this.y >= this.targetY) {
				this.fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() <= 0) {
			if (this.x <= this.targetX || this.y <= this.targetY) {
				this.fire();
			}
		} else if (this.velocity.getX() >= 0 && this.velocity.getY() <= 0) {
			if (this.x >= this.targetX || this.y <= this.targetY) {
				this.fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() >= 0) {
			if (this.x <= this.targetX || this.y >= this.targetY) {
				this.fire();
			}
		}

	}

	public void fire() {

		for (Enemy bombedEnemy : this.game.getEnemies()) {
			float bombedEnemyX = bombedEnemy.getX();
			float bombedEnemyY = bombedEnemy.getY();
			float bombedDeltaX = bombedEnemyX - this.x;
			float bombedDeltaY = bombedEnemyY - this.y;

			float bombDistance = (float) Math.sqrt(bombedDeltaX * bombedDeltaX + bombedDeltaY * bombedDeltaY);
			if (bombDistance < this.Radius + bombedEnemy.getRadius()) {
				bombedEnemy.setHealth(bombedEnemy.getHealth() - this.damage);
				if (bombedEnemy.getHealth() <= 0) {
					this.game.getPlayer().addMoney(bombedEnemy.getMoney());
					this.game.getPlayer().addScore(bombedEnemy.getMoney()*5);
				}
			}
		}
		this.game.getSoundHandler().play("explode");
		this.game.getProjectiles().remove(this);

	}

}
