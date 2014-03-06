package engine;

import engine.graphics.Sprite;

public abstract class Enemy extends Entity implements Drawable {
	private int health;
	private float speed;
	protected Sprite sprite;
	protected MyVector2f velocity;
	protected Waypoint waypoint;
	protected int direction;

	public Enemy(float x, float y, int health, float speed, Sprite sprite, Waypoint startingWaypoint, int direction) {
		super(x, y);
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.velocity = new MyVector2f(0, speed);
		this.waypoint = startingWaypoint;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void update(int delta) {
		this.x += this.velocity.getX() * delta;
		this.y += this.velocity.getY() * delta;
		if (this.waypoint.getNextWaypoint() == null) {

		}
		if (this.direction == Waypoint.DOWN && this.getY() >= this.waypoint.getY()) {
			this.newDirection();
		} else if(this.direction == Waypoint.RIGHT && this.getX() >= this.waypoint.getX()) {
			this.newDirection();
		} else if (this.direction == Waypoint.UP && this.getY() <= this.waypoint.getY()) {
			this.newDirection();
		} else if(this.direction == Waypoint.LEFT && this.getX() <= this.waypoint.getX()) {
			this.newDirection();
		}
	}

	private void newDirection() {
		if (this.waypoint.getDirection() == Waypoint.RIGHT) {
			this.velocity = new MyVector2f(speed, 0);
			this.direction = Waypoint.RIGHT;
		} else if (this.waypoint.getDirection() == Waypoint.UP) {
			this.velocity = new MyVector2f(0, -speed);
			this.direction = Waypoint.UP;
		} else if (this.waypoint.getDirection() == Waypoint.LEFT) {
			this.velocity = new MyVector2f(-speed, 0);
			this.direction = Waypoint.LEFT;
		} else if (this.waypoint.getDirection() == Waypoint.DOWN) {
			this.velocity = new MyVector2f(0, speed);
			this.direction = Waypoint.DOWN;
		}

		this.waypoint = this.waypoint.getNextWaypoint();
	}
}
