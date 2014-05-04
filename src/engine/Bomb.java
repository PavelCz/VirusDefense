package engine;

import engine.graphics.Sprite;

public class Bomb extends Entity implements Drawable {
	protected int bombRadius;
	protected float damage;
	protected Gameplay game;
	protected float targetX;
	protected float targetY;
	protected float speed;
	protected MyVector2f velocity;
	protected Sprite sprite;

	public Bomb(float x, float y, int bombRadius, float damage, Gameplay game,
			float enemyX, float enemyY) {
		super(x, y);
		this.bombRadius = bombRadius;
		this.damage = damage;
		this.game = game;
		this.targetX = enemyX;
		this.targetY = enemyY;
		this.speed = 0.1f;
		this.velocity = new MyVector2f(enemyX - x - 25, enemyY - y - 25);
		this.velocity.setLength(speed);
		this.sprite = new Sprite("shoot/Frame0001.png", 0.05f);
	}

	@Override
	public void draw() {
		this.sprite.draw(this.x, this.y);

	}

	public void update(int delta) {
		this.x += velocity.getX() * delta;
		this.y += velocity.getY() * delta;

		if (this.velocity.getX() >= 0 && this.velocity.getY() >= 0) {
			if (this.x >= targetX && this.y >= targetY) {
				fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() <= 0) {
			if (this.x <= targetX && this.y <= targetY) {
				fire();
			}
		} else if (this.velocity.getX() >= 0 && this.velocity.getY() <= 0) {
			if (this.x >= targetX && this.y <= targetY) {
				fire();
			}
		} else if (this.velocity.getX() <= 0 && this.velocity.getY() >= 0) {
			if (this.x <= targetX && this.y >= targetY) {
				fire();
			}
		}

	}

	public void fire() {

		for (Enemy bombedEnemy : this.game.getEnemies()) {
			float bombedEnemyX = bombedEnemy.getX();
			float bombedEnemyY = bombedEnemy.getY();
			float bombedDeltaX = bombedEnemyX - (this.velocity.getX());
			float bombedDeltaY = bombedEnemyY - (this.velocity.getY());

			float bombDistance = (float) Math.sqrt(bombedDeltaX * bombedDeltaX
					+ bombedDeltaY * bombedDeltaY);
			System.out.println(bombDistance);
			if (bombDistance < this.bombRadius + bombedEnemy.getRadius()) {
				System.out.println("t");
				bombedEnemy.setHealth(bombedEnemy.getHealth() - this.damage);
				if (bombedEnemy.getHealth() <= 0) {
					this.game.getPlayer().addMoney(bombedEnemy.getMoney());
				}
			}
		}
		this.game.bombs.remove(this);
	}

}
