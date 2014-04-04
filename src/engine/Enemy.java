package engine;

import engine.graphics.Sprite;

public class Enemy extends Entity implements Drawable {
	private float radius;
	private int health, maxHealth;
	private float speed;
	private Game game;
	protected Sprite sprite;
	protected MyVector2f velocity;
	protected Waypoint waypoint;
	protected int direction;

	public Enemy(int health, float speed, Sprite sprite, Waypoint startingWaypoint, int direction, Game game) {
		super(startingWaypoint.getX(), startingWaypoint.getY());
		this.health = health;
		maxHealth = this.health;
		this.speed = speed;
		this.sprite = sprite;
		velocity = new MyVector2f(0, speed);
		waypoint = startingWaypoint;
		this.direction = direction;
		radius = sprite.getWidth() / 2;
		this.game = game;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void update(int delta) {
		if (health > 0) {
			// System.out.println(this.direction);
			x += velocity.getX() * delta;
			y += velocity.getY() * delta;
			if (waypoint == null) {
				health = 0;
				Game.reduceLives();
			} else if (direction == Waypoint.DOWN && getY() >= waypoint.getY()) {
				newDirection();
			} else if (direction == Waypoint.RIGHT && getX() >= waypoint.getX()) {
				newDirection();
			} else if (direction == Waypoint.UP && getY() <= waypoint.getY()) {
				newDirection();
			} else if (direction == Waypoint.LEFT && getX() <= waypoint.getX()) {
				newDirection();
			}
		} else {
			game.getEnemies().remove(this);
		}
	}

	private void newDirection() {
		if (waypoint.getDirection() == Waypoint.RIGHT) {
			velocity = new MyVector2f(speed, 0);
			direction = Waypoint.RIGHT;
		} else if (waypoint.getDirection() == Waypoint.UP) {
			velocity = new MyVector2f(0, -speed);
			direction = Waypoint.UP;
		} else if (waypoint.getDirection() == Waypoint.LEFT) {
			velocity = new MyVector2f(-speed, 0);
			direction = Waypoint.LEFT;
		} else if (waypoint.getDirection() == Waypoint.DOWN) {
			velocity = new MyVector2f(0, speed);
			direction = Waypoint.DOWN;
		}

		waypoint = waypoint.getNextWaypoint();
	}

	@Override
	public void draw() {
		if (health > 0) {
			sprite.draw(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
		}
	}

	public float getRadius() {
		return radius;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {

		return maxHealth;
	}

}
