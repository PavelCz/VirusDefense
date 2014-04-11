package engine;

import engine.graphics.Sprite;

public abstract class Tower extends Entity implements Drawable {
	private int cost;
	protected int radius;
	protected float damage;
	protected Gameplay game;
	protected Sprite sprite;

	public Tower(float x, float y, int cost, int radius, float damage,
			Gameplay game) {
		super(x, y);

		this.cost = cost;
		this.radius = radius;
		this.damage = damage;
		this.game = game;

	}

	public abstract void shoot();

	public abstract void update(int delta);

	protected boolean inRange(Enemy enemy) {
		if (enemy != null) {
			float enemyX = enemy.getX();
			float enemyY = enemy.getY();
			float deltaX = enemyX - (this.getX() * 50 + 25);
			float deltaY = enemyY - (this.getY() * 50 + 25);

			float distance = (float) Math.sqrt(deltaX * deltaX + deltaY
					* deltaY);

			if (distance < this.radius + enemy.getRadius()) {
				return true;
			}

		}
		return false;

	}

	protected boolean inRangeOfAnyEnemy() {
		if (this.getEnemyInRange() != null) {
			return true;
		} else {
			return false;
		}
	}

	protected Enemy getEnemyInRange() {
		boolean done = false;
		for (Enemy enemy : this.game.getEnemies()) {
			if (enemy != null && !done) {
				float enemyX = enemy.getX();
				float enemyY = enemy.getY();
				float deltaX = enemyX - (this.getX() * 50 + 25);
				float deltaY = enemyY - (this.getY() * 50 + 25);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY
						* deltaY);

				if (distance < this.radius + enemy.getRadius()) {
					return enemy;
				}
			}
		}
		return null;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public abstract Tower clone();

	public Sprite getSprite() {
		return this.sprite;
	}

	public int getRadius() {
		return this.radius;
	}

	public int getCost() {
		return this.cost;
	}

}
