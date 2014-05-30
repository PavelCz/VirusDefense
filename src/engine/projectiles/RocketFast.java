package engine.projectiles;

import towerDefense.Gameplay;
import engine.Drawable;
import engine.Enemy;
import engine.MyVector2f;
import engine.graphics.Sprite;

public class RocketFast extends Projectile implements Drawable {
	private Enemy enemy;

	public RocketFast(float x, float y, int bombRadius, float damage, Gameplay game, Enemy enemy) {
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

		enemy.setHealth(enemy.getHealth() - this.damage);
		if (enemy.getHealth() <= 0) {
			this.game.getPlayer().addMoney(enemy.getMoney());
			this.game.getPlayer().addScore(enemy.getMoney()*5);
		}

		this.game.getSoundHandler().play("explode");
		this.game.getProjectiles().remove(this);

	}

}
