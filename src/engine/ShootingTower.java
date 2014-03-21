package engine;

import engine.graphics.Sprite;

public class ShootingTower extends Tower {
	public ShootingTower(float x, float y, Sprite sprite, Game game) {
		super(x * 50, y * 50, 200, 100, new ShootingWeapon(), game);
		this.sprite = sprite;
	}

	@Override
	public Tower clone() {
		return new ShootingTower(this.x, this.y, this.sprite.clone(), this.game);
	}

	@Override
	public void draw() {
		this.sprite.draw(x * 50, y * 50);

	}
	public void shoot() {
		Enemy enemy = this.game.getEnemy();
		float enemyX = enemy.getX();
		float enemyY = enemy.getY();
		float deltaX = enemyX - this.getX()*50;
		float deltaY = enemyY - this.getY()*50;
		
		float distance = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		if(distance < this.radius + enemy.getRadius()) {
			enemy.setHealth(enemy.getHealth() - 10);
		}
		
	}
}
