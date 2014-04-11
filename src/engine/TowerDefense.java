package engine;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TowerDefense extends BasicGame {
	public static final int MODE_MENU = 0;
	public static final int MODE_GAME = 1;

	private Gameplay gameplay;
	private Menu menu;
	private GameComponent currentGameComponent;
	private boolean quitGame = false;

	private int mode;

	public TowerDefense() {
		super("Tower Defense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.gameplay = new Gameplay(this);
		this.menu = new Menu(this);
		this.menu.init(container);
		this.gameplay.init(container);
		this.mode = TowerDefense.MODE_MENU;
		this.currentGameComponent = this.menu;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (this.quitGame) {
			container.exit();
			System.out.println("act");
		}
		if (this.mode == TowerDefense.MODE_GAME) {
			this.currentGameComponent = this.gameplay;
		} else {
			this.currentGameComponent = this.menu;
		}
		this.currentGameComponent.update(container, delta);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		this.currentGameComponent.render(container, graphics);

	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void quitGame() {
		this.quitGame = true;
	}
}
