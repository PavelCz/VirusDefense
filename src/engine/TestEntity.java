package engine;

import org.newdawn.slick.SlickException;

import engine.graphics.Sprite;

public class TestEntity extends RotatableEntity implements Drawable {
	
	private Sprite sprite;
	
	public TestEntity(float x, float y, float rotation, String spritePath) {
		super(x,y, rotation);
		
		try {
			this.sprite = new Sprite(spritePath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw() {
		sprite.draw(this.x, this.y, this.rotation, 1);

	}

	@Override
	public void rotate(float degrees) {
		this.rotation += rotation;
		
	}

}
