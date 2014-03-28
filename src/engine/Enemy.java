package engine;

import engine.graphics.Sprite;

public abstract class Enemy extends Entity implements Drawable {
	private float radius;
	private int health;
	private float speed;
	private Game game;
	protected Sprite sprite;
	protected MyVector2f velocity;
	protected Waypoint waypoint;
	protected int direction;

	public Enemy(int health, float speed, Sprite sprite, Waypoint startingWaypoint, int direction, Game game) {
		super(startingWaypoint.getX(), startingWaypoint.getY());
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.velocity = new MyVector2f(0, speed);
		this.waypoint = startingWaypoint;
		this.direction = direction;
		this.radius = sprite.getWidth();
		this.game = game;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void update(int delta) {
		if (this.health > 0) {
			//System.out.println(this.direction);
			this.x += this.velocity.getX() * delta;
			this.y += this.velocity.getY() * delta;
			if (this.waypoint == null) {
				this.health = 0;
				Game.reduceLives();
			} else if (this.direction == Waypoint.DOWN && this.getY() >= this.waypoint.getY()) {
				this.newDirection();
			} else if (this.direction == Waypoint.RIGHT && this.getX() >= this.waypoint.getX()) {
				this.newDirection();
			} else if (this.direction == Waypoint.UP && this.getY() <= this.waypoint.getY()) {
				this.newDirection();
			} else if (this.direction == Waypoint.LEFT && this.getX() <= this.waypoint.getX()) {
				this.newDirection();
			}
		} else {
			game.getEnemy().remove(this);
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

	public void draw() {
		if (this.health > 0) {
			this.sprite.draw(this.x - this.sprite.getWidth() / 2, this.y - this.sprite.getHeight() / 2);
		}
	}
	
	public float getRadius() {
		return this.radius;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
