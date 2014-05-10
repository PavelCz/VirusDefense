package towerDefense;

import org.lwjgl.openal.AL;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import engine.GameComponent;
import engine.MapLayout;
import engine.SoundHandler;

public class TowerDefense extends BasicGame {

	protected SoundHandler soundHandler = new SoundHandler();
	public static final int MODE_MENU = 0;
	public static final int MODE_GAME = 1;
	public static final int MODE_MAPS = 2;
	private static int HEIGHT;
	private static int WIDTH;

	private Gameplay gameplay;
	private Menu menu;
	private ChooseMaps maps;
	private GameComponent currentGameComponent;
	private boolean quitGame = false;

	private int mode;

	public TowerDefense() {
		super("Tower Defense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.initSounds();
		TowerDefense.HEIGHT = container.getHeight();
		TowerDefense.WIDTH = container.getWidth();
		this.gameplay = new Gameplay(this);
		this.menu = new Menu(this);
		this.menu.init(container);
		this.maps = new ChooseMaps(this);
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
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (this.quitGame) {
			container.exit();
			AL.destroy();
		}
		if (this.mode == TowerDefense.MODE_GAME) {
			this.currentGameComponent = this.gameplay;
		} else if (this.mode == TowerDefense.MODE_MAPS) {
			this.currentGameComponent = this.maps;
		} else {
			this.currentGameComponent = this.menu;
		}
		this.currentGameComponent.update(container, delta);

	}

	@Override
	public void render(GameContainer container, Graphics graphics)
			throws SlickException {
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

	public static int getHeight() {
		return TowerDefense.HEIGHT;
	}

	public static int getWidth() {
		return TowerDefense.WIDTH;
	}

	public void setMapLayout(MapLayout mapLayout) {
		this.gameplay.setMapLayout(mapLayout);
	}

	public void initGameplay(GameContainer container) {

		try {
			this.gameplay.init(container);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.currentGameComponent = this.gameplay;
	}
}
