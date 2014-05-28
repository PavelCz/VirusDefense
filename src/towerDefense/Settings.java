package towerDefense;

import engine.GameComponent;
import engine.gui.GoToMenuButton;

public class Settings extends GameComponent {

	public Settings(TowerDefense game) {
		super(game);

		GoToMenuButton settings = new GoToMenuButton(0, 0, "Back", this.game);
		this.clickables.add(settings);
		this.guiElements.add(settings);
		settings.setX(0);
		settings.setY(TowerDefense.getHeight() - settings.getHeight() * 2);
	}

}
