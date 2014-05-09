package engine.gui;

import towerDefense.Gameplay;
import towerDefense.TowerDefense;
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
			this.unclickedButton.draw(this.x, this.y, TowerDefense.GLOBAL_GUI_SCALE);

		} else {
			this.clickedButton.draw(this.x, this.y, TowerDefense.GLOBAL_GUI_SCALE);
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

}
