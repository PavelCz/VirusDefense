package engine;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TowerDefense extends BasicGame {
	private Gameplay gameplay;

	public TowerDefense() {
		super("Tower Defense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.gameplay = new Gameplay();
		this.gameplay.init(container);

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		this.gameplay.update(container, delta);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		this.gameplay.render(container, graphics);

	}
}
