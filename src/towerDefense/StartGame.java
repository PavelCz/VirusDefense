package towerDefense;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class StartGame {
	public static void main(final String[] args) throws SlickException {
		StartGame.start(1024, 768);

	}

	public static void start(int width, int height) throws SlickException {
		final TowerDefense game = new TowerDefense();
		AppGameContainer appGameContainer;
		appGameContainer = new AppGameContainer(game, width, height, false);
		appGameContainer.start();
	}

}
