package engine.graphics;

import towerDefense.Gameplay;
import engine.Drawable;

public class Background implements Drawable {
	protected Sprite picture;

	public Background(float scale, String backgroundPath) {
		this.picture = new Sprite(backgroundPath, scale);
	}

	@Override
	public void draw() {
		this.picture.draw(0, 0, Gameplay.CURRENT_GAME_SCALE);

	}

}
