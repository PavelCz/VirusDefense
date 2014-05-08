package engine;

import towerDefense.Gameplay;
import engine.graphics.Sprite;

/**
 * lets you make a preset for a specific enemy type
 * 
 * @author Pavel
 */
public class EnemyType {
	private float radius;
	private int health;
	private float speed;
	protected Sprite sprite;
	private Gameplay game;
	private int worth;

	/**
	 * @param health
	 *            the starting and maximum health
	 * @param speed
	 * @param sprite
	 *            the location of the image of the sprite in the folder /data
	 * @param game
	 * @param radius
	 *            the collision radius for hitting the enemy
	 */
	public EnemyType(int health, float speed, String sprite, Gameplay game, float radius, int worth, float size) {
		this.health = health;
		this.speed = speed;
		this.radius = radius;
		this.sprite = new Sprite(sprite, size);
		this.game = game;
		this.worth = worth;
	}

	/**
	 * @return an instance of Enemy with the properties specified in this EnemyType
	 */
	public Enemy createEnemy() {
		return new Enemy(this);
	}

	public float getRadius() {
		return this.radius;
	}

	public int getHealth() {
		return this.health;
	}

	public float getSpeed() {
		return this.speed;
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public Gameplay getGame() {
		return this.game;
	}

	public int getWorth() {
		return this.worth;
	}
}
