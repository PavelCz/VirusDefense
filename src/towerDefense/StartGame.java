package towerDefense;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class StartGame {
	public static void main(final String[] args) throws SlickException {

		final TowerDefense game = new TowerDefense();
		AppGameContainer appGameContainer;
		appGameContainer = new AppGameContainer(game, 1024, 768, false);
		appGameContainer.start();

	}
}
