package engine;

import engine.graphics.Sprite;

public class BombTower extends Tower {
	protected int delta;
	protected final int shootingInterval;
	protected int bombRadius;

	public BombTower(float x, float y, Sprite sprite, Gameplay game, int shootingInterval, float damage, int bombRadius) {
		super(x * 50, y * 50, 100, 200, damage, game);
		this.sprite = sprite;
		this.shootingInterval = shootingInterval;
		this.delta = this.shootingInterval;
		this.bombRadius = bombRadius;
	}

	@Override
	public void draw() {
		this.sprite.draw(this.x * 50, this.y * 50);

	}

	@Override
	public void shoot() {
		boolean done = false;
		for (Enemy enemy : this.game.getEnemies()) {
			if (enemy != null && !done) {
				float enemyX = enemy.getX();
				float enemyY = enemy.getY();
				float deltaX = enemyX - (this.getX() * 50 + 25);
				float deltaY = enemyY - (this.getY() * 50 + 25);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (distance < this.radius + enemy.getRadius()) {
					Bomb b = new Bomb(x*50 +25,y*50+25, bombRadius, damage, game, enemyX, enemyY);
					game.bombs.add(b);
//					for (Enemy bombedEnemy : this.game.getEnemies()) {
//						float bombedEnemyX = bombedEnemy.getX();
//						float bombedEnemyY = bombedEnemy.getY();
//						float bombedDeltaX = bombedEnemyX - (enemyX);
//						float bombedDeltaY = bombedEnemyY - (enemyY);
//
//						float bombDistance = (float) Math.sqrt(bombedDeltaX * bombedDeltaX + bombedDeltaY * bombedDeltaY);
//						System.out.println(bombDistance);
//						if (bombDistance < this.bombRadius + bombedEnemy.getRadius()) {
//							System.out.println("t");
//							bombedEnemy.setHealth(bombedEnemy.getHealth() - this.damage);
//							if (bombedEnemy.getHealth() <= 0) {
//								this.game.getPlayer().addMoney(bombedEnemy.getMoney());
//							}
//						}
						done = true;
//					}
				}
			}
		}

	}

	@Override
	public void update(int delta) {
		this.delta -= delta;
		if (this.delta <= 0) {
			this.delta = this.shootingInterval;
			this.shoot();
		}
	}

	@Override
	public Tower clone() {
		return new BombTower(this.x, this.y, this.sprite.clone(), this.game, this.shootingInterval, this.damage, this.bombRadius);
	}

}
