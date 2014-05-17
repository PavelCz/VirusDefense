package engine.graphics;

import towerDefense.Gameplay;
import towerDefense.TowerDefense;

public class BackgroundTiles extends Background {
	private int horizontalTiles, verticalTiles;
	private Sprite pictureOutOfBounds;

	public BackgroundTiles(float scale, String backgroundPath, int horizontalTiles, int verticalTiles) {
		super(scale, backgroundPath);
		this.horizontalTiles = horizontalTiles;
		this.verticalTiles = verticalTiles;
		this.pictureOutOfBounds = new Sprite(backgroundPath);
		// this.pictureOutOfBounds.setAlpha(0.8f);

		this.pictureOutOfBounds.setColor(0.7f, 0.7f, 0.7f);

	}

	@Override
	public void draw() {
		for (int i = 0; i < TowerDefense.getWidth() / Gameplay.SIZE; ++i) {
			for (int j = 0; j < TowerDefense.getHeight() / Gameplay.SIZE; ++j) {
				if (i < this.horizontalTiles && j < this.verticalTiles) {
					this.picture.draw(i * Gameplay.SIZE, j * Gameplay.SIZE, Gameplay.CURRENT_GAME_SCALE);
				} else {
					this.pictureOutOfBounds.draw(i * Gameplay.SIZE, j * Gameplay.SIZE, Gameplay.CURRENT_GAME_SCALE);
				}
			}
		}

	}
}
