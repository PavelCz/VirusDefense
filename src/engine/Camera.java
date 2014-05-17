package engine;

import towerDefense.Gameplay;
import towerDefense.TowerDefense;

public class Camera extends Entity {
	private Gameplay game;

	public Camera(float x, float y, Gameplay game) {
		super(x, y);
		this.game = game;
	}

	public void addX(float amount) {
		float cameraWidth = Gameplay.INTERFACE_START_X;

		this.x += amount;
		if (Gameplay.getCameraX() < 0) {
			this.setX(0);
		} else if ((Gameplay.getCameraX() + cameraWidth) / Gameplay.CURRENT_GAME_SCALE > this.game.getHorizontalTiles()
				* Gameplay.DEFAULT_SIZE) {
			this.setX((this.game.getHorizontalTiles() * Gameplay.DEFAULT_SIZE) * Gameplay.CURRENT_GAME_SCALE - cameraWidth);

		}
	}

	public void addY(float amount) {
		float cameraHeight = TowerDefense.getHeight();
		this.y += amount;
		if (Gameplay.getCameraY() < 0) {
			this.setY(0);
		} else if ((Gameplay.getCameraY() + cameraHeight) / Gameplay.CURRENT_GAME_SCALE > this.game.getVerticalTiles()
				* Gameplay.DEFAULT_SIZE) {
			this.setY((this.game.getVerticalTiles() * Gameplay.DEFAULT_SIZE) * Gameplay.CURRENT_GAME_SCALE - cameraHeight);

		}
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

}
