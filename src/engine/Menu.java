package engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import engine.gui.StartClickable;

public class Menu extends GameComponent {

	public Menu(TowerDefense game) {
		super(game);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		super.init(container);
		StartClickable c = new StartClickable(0, 0, this.game);
		this.clickables.add(c);
		this.guiElements.add(c);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);

	}

}
