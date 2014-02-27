package engine.graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends RotatableRenderObject {
	private Image image;
	private float defaultScale;

	public Sprite(String imagePath) {

		try {
			this.image = new Image(imagePath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.defaultScale = 1f;

	}

	public Sprite(String imagePath, float scale) {

		this(imagePath);

		this.defaultScale = scale;

	}

	public void draw(float x, float y) {
		this.draw(x, y, 0, 1);
	}

	public void draw(float xCoordinate, float yCoordinate, float rotation, float scale) {
		this.image.setRotation(rotation);
		this.image.draw(xCoordinate, yCoordinate, scale * this.defaultScale);
	}

	public int getWidth() {
		return this.image.getWidth();
	}

	public int getHeight() {
		return this.image.getHeight();
	}

}
