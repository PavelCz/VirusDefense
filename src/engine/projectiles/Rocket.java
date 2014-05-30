package engine.projectiles;

import towerDefense.Gameplay;
import engine.Drawable;
import engine.Enemy;
import engine.MyVector2f;
import engine.graphics.Sprite;

public class Rocket extends Projectile implements Drawable {
	private Enemy enemy;

	public Rocket(float x, float y, int bombRadius, float damage, Gameplay game, Enemy enemy) {
		super(x, y);
		this.Radius = bombRadius;
		this.damage = damage;
		this.game = game;
		this.speed = 0.15f;
		this.velocity = new MyVector2f(enemy.getX() - x, enemy.getY() - y);
		this.velocity.setLength(speed);
		this.renderObject = new Sprite("shoot/Frame0010.png", 0.05f);
		this.enemy = enemy;
	}

	public void update(int delta) {
		this.velocity = new MyVector2f(enemy.getX() - x, enemy.getY() - y);
		this.velocity.setLength(speed);
		this.x += velocity.getX() * delta;
		this.y += velocity.getY() * delta;

		if (this.velocity.getX() >= 0 && this.velocity.getY() >= 0) {
			if (this.x >= enemy.getX() || this.y >= enemy.getY()) {
				fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() <= 0) {
			if (this.x <= enemy.getX() || this.y <= enemy.getY()) {
				fire();
			}
		} else if (this.velocity.getX() >= 0 && this.velocity.getY() <= 0) {
			if (this.x >= enemy.getX() || this.y <= enemy.getY()) {
				fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() >= 0) {
			if (this.x <= enemy.getX() || this.y >= enemy.getY()) {
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
					this.game.getPlayer().addScore(bombedEnemy.getMoney()*5);
				}
			}
		}
		this.game.getSoundHandler().play("explode");
		this.game.getProjectiles().remove(this);

		
	}

}
