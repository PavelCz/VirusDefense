package towerDefense;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.AppletGameContainer;
import org.newdawn.slick.SlickException;

public class TowerDefenseApplet extends AppletGameContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4460920747922375285L;

	public TowerDefenseApplet() {
		final TowerDefense game = new TowerDefense();
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
