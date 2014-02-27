package engine.gui;

import engine.graphics.Sprite;

public class Button extends GUI implements Clickable {
	private Sprite unclickedButton;
	private Sprite clickedButton;
	private boolean clicked;
	private float width;
	private float height;
	
	public Button(float x, float y, String unclickedButtonPath, String clickedButtonPath) {
		super(x, y);
		this.clicked = false;
		this.unclickedButton = new Sprite(unclickedButtonPath);
		this.clickedButton = new Sprite(clickedButtonPath);
		this.width = this.unclickedButton.getWidth();
		this.height = this.unclickedButton.getHeight();
	}

	@Override
	public void draw() {
		if(!this.clicked) {
			this.unclickedButton.draw(this.x, this.y);
			
		} else {
			this.clickedButton.draw(this.x, this.y);
		}

	}
	
	public void onClick() {
		this.clicked = true;
	}
	public void onRelease() {
		this.clicked = false;
	}
	
	public boolean checkCollision(float x, float y) {
		return (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y
				+ this.height);
	}

}
