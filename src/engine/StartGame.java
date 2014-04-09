package engine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class StartGame {
	public static void main(String[] args) {
		Gameplay game = new Gameplay();
		AppGameContainer appGameContainer;
		try {
			appGameContainer = new AppGameContainer(game, 800, 600, false);
			appGameContainer.start();

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
