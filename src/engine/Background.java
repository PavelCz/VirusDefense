package engine;

import engine.graphics.Sprite;

public class Background implements Drawable{
	private Sprite picture;
	
	Background(float scale) {
		this.picture = new Sprite("./data/background.jpg", scale);
	}
	@Override
	public void draw() {
		this.picture.draw(0, 0);
		
	}
	
}
