package engine.gui;

import towerDefense.Gameplay;
import engine.graphics.Sprite;

public class Button extends Clickable {
	private Sprite unclickedButton;
	private Sprite clickedButton;

	public Button(float x, float y, String unclickedButtonPath, String clickedButtonPath, Gameplay game) {
		this(x, y, new Sprite(unclickedButtonPath), new Sprite(clickedButtonPath), game);
	}

	public Button(float x, float y, Sprite unclickedButton, Sprite clickedButton, Gameplay game) {
		super(x, y, game);
		this.unclickedButton = unclickedButton;
		this.clickedButton = clickedButton;
		this.collisionWidth = this.unclickedButton.getWidth();
		this.collisionHeight = this.unclickedButton.getHeight();
	}

	@Override
	public void draw() {
		if (!this.clicked) {
			this.unclickedButton.draw(this.x, this.y, Gameplay.GLOBAL_GUI_SCALE);

		} else {
			this.clickedButton.draw(this.x, this.y, Gameplay.GLOBAL_GUI_SCALE);
		}

	}

	@Override
	public void onHover() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnHover() {
		// TODO Auto-generated method stub

	}

	public void setUnclickedButton(Sprite picture) {
		this.unclickedButton = picture;
	}

}
