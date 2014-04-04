package engine;

import engine.graphics.Sprite;

public class Background implements Drawable {
	private Sprite picture;

	public Background(float scale, String backgroundPath) {
		this.picture = new Sprite(backgroundPath, scale);
	}

	@Override
	public void draw() {
		this.picture.draw(0, 0);

	}

}
