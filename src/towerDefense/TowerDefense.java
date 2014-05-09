package towerDefense;

import org.lwjgl.openal.AL;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import engine.GameComponent;
import engine.SoundHandler;

public class TowerDefense extends BasicGame {

	protected SoundHandler soundHandler = new SoundHandler();
	public static final int MODE_MENU = 0;
	public static final int MODE_GAME = 1;
	public static float GLOBAL_GAME_SCALE = 0.5f;
	public static float GLOBAL_GUI_SCALE = 1f;
	private int height;
	public int width;

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
		this.initSounds();
		this.height = container.getHeight();
		this.width = container.getWidth();
		this.gameplay = new Gameplay(this);
		this.menu = new Menu(this);
		this.menu.init(container);
		this.gameplay.init(container);
		this.mode = TowerDefense.MODE_MENU;
		this.currentGameComponent = this.menu;
	}

	private void initSounds() {
		this.soundHandler.addWav("press");
		this.soundHandler.add("place", "place.wav");
		this.soundHandler.addWav("bad");
		this.soundHandler.addWav("death");
		this.soundHandler.addWav("spawn");

		this.soundHandler.addWav("explode");
		this.soundHandler.addWav("shotT1");
		this.soundHandler.addWav("shotT2");
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (this.quitGame) {
			container.exit();
			AL.destroy();
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

	public SoundHandler getSoundHandler() {
		return this.soundHandler;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}
}
