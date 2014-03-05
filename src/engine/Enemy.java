package engine;

import engine.graphics.Sprite;

public abstract class Enemy extends Entity implements Drawable {
	private int health;
	private int speed;
	protected Sprite sprite;

	public Enemy(float x, float y, int health, int speed, Sprite sprite) {
		super(x, y);

		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}
