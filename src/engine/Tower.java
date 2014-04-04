package engine;

import engine.graphics.Sprite;

public abstract class Tower extends Entity implements Drawable {
	private int cost;
	protected int radius;
	private Weapon weapon;
	protected Game game;
	protected Sprite sprite;

	public Tower(float x, float y, int cost, int radius, Weapon weapon, Game game) {
		super(x, y);

		this.cost = cost;
		this.radius = radius;
		this.weapon = weapon;
		this.game = game;

	}

	public abstract void shoot();

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
