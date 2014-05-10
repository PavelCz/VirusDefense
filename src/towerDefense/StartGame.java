package towerDefense;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class StartGame {
	public static void main(String[] args) {
		TowerDefense game = new TowerDefense();
		AppGameContainer appGameContainer;
		try {
			appGameContainer = new AppGameContainer(game, 1024, 768, false);
			appGameContainer.start();

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
