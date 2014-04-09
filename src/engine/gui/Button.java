package engine.gui;

import engine.Gameplay;
import engine.graphics.Sprite;

public class Button extends Clickable {
	private Sprite unclickedButton;
	private Sprite clickedButton;
	protected Gameplay game;

	public Button(float x, float y, String unclickedButtonPath, String clickedButtonPath, Gameplay game) {
		super(x, y);
		this.unclickedButton = new Sprite(unclickedButtonPath);
		this.clickedButton = new Sprite(clickedButtonPath);
		this.collisionWidth = this.unclickedButton.getWidth();
		this.collisionHeight = this.unclickedButton.getHeight();
		this.game = game;
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
	public void onHover() {
		// TODO Auto-generated method stub

	}

}
