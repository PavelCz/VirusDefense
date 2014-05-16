package engine;

import towerDefense.Gameplay;
import engine.graphics.Sprite;

public class RocketFast extends Projectile implements Drawable {
	private Enemy enemy;

	public RocketFast(float x, float y, int bombRadius, float damage, Gameplay game, Enemy enemy) {
		super(x, y);
		this.Radius = bombRadius;
		this.damage = damage;
		this.game = game;
		this.speed = 0.15f;
		this.velocity = new MyVector2f(enemy.x - x, enemy.y - y);
		this.velocity.setLength(speed);
		this.sprite = new Sprite("shoot/Frame0010.png", 0.05f);
		this.enemy = enemy;
		System.out.println("test");
	}

	public void update(int delta) {
		this.velocity = new MyVector2f(enemy.getX() - x, enemy.getY() - y);
		this.velocity.setLength(speed);
		this.x += velocity.getX() * delta;
		this.y += velocity.getY() * delta;
		System.out.println(enemy.getX());

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
		}

		this.game.getSoundHandler().play("explode");
		this.game.getProjectiles().remove(this);

	}

}
