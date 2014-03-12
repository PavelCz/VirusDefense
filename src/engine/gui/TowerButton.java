package engine.gui;

import engine.Tower;

public class TowerButton extends Button{
	private Tower tower;

	public TowerButton(float x, float y, String unclickedButtonPath, String clickedButtonPath, Tower tower) {
		super(x, y, unclickedButtonPath, clickedButtonPath);
		this.tower = tower;
	}
	
	public void draw() {
		super.draw();
	}
	
	public Tower getTower() {
		return this.tower;
	}

}
