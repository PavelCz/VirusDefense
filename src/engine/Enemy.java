package engine;

import engine.graphics.Sprite;

public class Enemy extends Entity implements Drawable {
	private float radius;
	private float health;
	private int maxHealth;
	private float speed;
	private Gameplay game;
	protected Sprite sprite;
	protected MyVector2f velocity;
	protected Waypoint waypoint;
	protected int direction;
	private int worth;
	private boolean dead = false;

	private Enemy(int maxHealth, float speed, Sprite sprite, Waypoint startingWaypoint, int direction, Gameplay game, float radius,
			int worth) {
		super(startingWaypoint.getX(), startingWaypoint.getY());
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.speed = speed;
		this.sprite = sprite;
		this.velocity = new MyVector2f(0, speed);
		this.waypoint = startingWaypoint;
		this.direction = direction;
		this.radius = radius;
		this.game = game;
		this.worth = worth;
		this.game.getSoundHandler().play("spawn");
	}

	public Enemy(EnemyType enemyType) {
		this(enemyType.getHealth(), enemyType.getSpeed(), enemyType.getSprite(), enemyType.getGame().getWaypoints(), enemyType
				.getGame().getWaypoints().getDirection(), enemyType.getGame(), enemyType.getRadius(), enemyType.getWorth());

	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void update(int delta) {
		if (this.health > 0) {
			this.x += this.velocity.getX() * delta;
			this.y += this.velocity.getY() * delta;
			if (this.waypoint == null) {
				this.health = 0;
				this.game.reduceLives();
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
			this.game.getSoundHandler().play("death");
			this.game.getEnemies().remove(this);
		}
	}

	private void newDirection() {
		if (this.waypoint.getDirection() == Waypoint.RIGHT) {
			this.velocity = new MyVector2f(this.speed, 0);
			this.direction = Waypoint.RIGHT;
		} else if (this.waypoint.getDirection() == Waypoint.UP) {
			this.velocity = new MyVector2f(0, -this.speed);
			this.direction = Waypoint.UP;
		} else if (this.waypoint.getDirection() == Waypoint.LEFT) {
			this.velocity = new MyVector2f(-this.speed, 0);
			this.direction = Waypoint.LEFT;
		} else if (this.waypoint.getDirection() == Waypoint.DOWN) {
			this.velocity = new MyVector2f(0, this.speed);
			this.direction = Waypoint.DOWN;
		}

		this.waypoint = this.waypoint.getNextWaypoint();
	}

	@Override
	public void draw() {
		if (this.health > 0) {
			this.sprite.draw(this.x - this.sprite.getWidth() / 2, this.y - this.sprite.getHeight() / 2);
		}
	}

	public float getRadius() {
		return this.radius;
	}

	public float getHealth() {
		return this.health;
	}

	public int getMoney() {
		return this.worth;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getMaxHealth() {

		return this.maxHealth;
	}

	public boolean isDead() {
		return this.dead;
	}

	public void setDead() {
		this.dead = true;
	}
}
