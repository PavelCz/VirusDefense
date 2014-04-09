package engine.gui;

import engine.Tower;
import engine.graphics.Sprite;

public class Button extends Clickable {
	private Sprite unclickedButton;
	private Sprite clickedButton;
	private boolean clicked;

	public Button(float x, float y, String unclickedButtonPath, String clickedButtonPath) {
		super(x, y);
		this.clicked = false;
		this.unclickedButton = new Sprite(unclickedButtonPath);
		this.clickedButton = new Sprite(clickedButtonPath);
		this.collisionWidth = this.unclickedButton.getWidth();
		this.collisionHeight = this.unclickedButton.getHeight();
	}

	@Override
	public void draw() {
		if (!this.clicked) {
			this.unclickedButton.draw(this.x, this.y);

		} else {
			this.clickedButton.draw(this.x, this.y);
		}

	}

	@Override
	public void onClick() {
		this.clicked = true;
	}

	@Override
	public void onRelease() {
		this.clicked = false;
	}

	public Tower getTower() {
		return null;
	}

}
