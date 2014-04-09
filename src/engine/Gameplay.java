package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import engine.graphics.SlickRectangle;
import engine.graphics.SlickUnfilledEllipse;
import engine.graphics.SlickUnfilledRectangle;
import engine.graphics.Sprite;
import engine.gui.Clickable;
import engine.gui.ClickableText;
import engine.gui.GUI;
import engine.gui.InterfaceBackground;
import engine.gui.SlickHealthbar;
import engine.gui.StaticText;
import engine.gui.TowerButton;

/**
 * @author Pavel
 */
public class Gameplay extends BasicGame {
	private List<Drawable> drawables;
	private List<GUI> guiElements;
	private List<Clickable> clickables;
	private ConcurrentLinkedQueue<Enemy> enemies;
	private WaveHandler waveHandler;
	private boolean mouseWasClicked;
	private boolean debugMode;
	private EnemyTypes enemyTypes;
	private int passedMilliseconds;
	private int mode;
	private MapLayout currentMapLayout;
	private int currentTileLength;
	private Tower[][] towers;
	private TowerButton towerButton1;
	private Tower currentTower;
	private Player player;
	private StaticText numberLives;
	private StaticText moneyAmount;
	private boolean currentTowerPlaceable;
	private int towerShadowX, towerShadowY;

	private StaticText passedTime;
	private InterfaceBackground interfaceBackground;
	// Constants:
	public static int INTERFACE_START_X;
	public static int STANDARD_TEXT_SCALE;
	private float speed;
	private ClickableText c;

	// Tests:

	//

	public Gameplay() {
		super("Tower Defense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.initDefaults();

		this.currentMapLayout = new MapLayout("maps/map.png", "maps/background.jpg", 50);
		this.currentTileLength = this.currentMapLayout.getTileLength();

		// Set Constants:

		Gameplay.INTERFACE_START_X = this.currentMapLayout.getNumberTilesWidth() * this.currentTileLength;
		//
		this.interfaceBackground = new InterfaceBackground("Interface1.png");

		this.towers = new Tower[12][13];
		this.initWaves();
		this.enemyTypes.add(new EnemyType(100, 0.1f, "enemy/v1.png", this, 25, 50));

		// add all objects that need to be drawn to the respectable arrays
		// entities

		// Buttons; this has nothing to do with the draw sequence
		this.towerButton1 = new TowerButton(Gameplay.INTERFACE_START_X, 200, "button1.png", "button2.png", new LongerShootingTower(0, 0,
				new Sprite("tower/t1.png", 0.05f), this, 400, 0.1f, 400), this);
		this.clickables.add(this.towerButton1);
		this.clickables.add(this.c);

		//
		this.initGUI();
		container.setShowFPS(this.debugMode);

	}

	private void initDefaults() {
		this.drawables = new ArrayList<Drawable>();
		this.guiElements = new ArrayList<GUI>();
		this.clickables = new ArrayList<Clickable>();
		this.enemies = new ConcurrentLinkedQueue<Enemy>();
		this.waveHandler = new WaveHandler(this, 2000);
		this.mouseWasClicked = false;
		this.debugMode = false;
		this.enemyTypes = new EnemyTypes();
		this.passedMilliseconds = 0;
		this.mode = 0;
		this.player = new Player();
		STANDARD_TEXT_SCALE = 15;
		this.speed = 1f;
		this.currentTowerPlaceable = true;

		this.c = new ClickableText(600, 100, "YOYOYOOY");
	}

	private void initWaves() {
		this.waveHandler.addWave(new Wave(1, new int[] { 100 }));
		this.waveHandler.addWave(new Wave(3, new int[] { 100 }));
		this.waveHandler.addWave(new Wave(4, new int[] { 100 }));
		this.waveHandler.addWave(new Wave(5, new int[] { 100 }));
		this.waveHandler.addWave(new Wave(6, new int[] { 100 }));
	}

	private void initGUI() {
		int guiX = 3;
		int livesY = 150;
		int moneyY = 165;
		this.numberLives = new StaticText(Gameplay.INTERFACE_START_X + 50, livesY, Color.white, "" + this.player.getLives());
		this.passedTime = new StaticText(Gameplay.INTERFACE_START_X + guiX, 580, Color.white, this.passedTimeToString());
		this.moneyAmount = new StaticText(Gameplay.INTERFACE_START_X + 65, moneyY, Color.white, "" + this.player.getMoney());

		this.guiElements.add(this.interfaceBackground);
		this.guiElements.add(this.numberLives);
		this.guiElements.add(this.towerButton1);
		this.guiElements.add(new StaticText(Gameplay.INTERFACE_START_X + guiX, livesY, Color.white, "Lives:"));
		this.guiElements.add(this.passedTime);
		this.guiElements.add(new StaticText(Gameplay.INTERFACE_START_X + guiX, moneyY, Color.white, "Money:"));
		this.guiElements.add(this.moneyAmount);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		this.currentMapLayout.drawBackground();
		this.renderEnemies();
		this.renderTowers();

		for (Drawable entity : this.drawables) {
			entity.draw();
		}

		this.renderTowerShadow(container, graphics);
		this.renderGUI();
		this.renderHealthBars(container, graphics);
		this.renderDebug(container, graphics);

		this.c.draw();

	}

	private void renderGUI() {
		for (GUI guiElement : this.guiElements) {
			guiElement.draw();
		}

		if (this.mode == 1) {
			new Sprite("You Win.png").draw(0, 0);
		} else if (this.mode == -1) {
			new Sprite("Game Over.png").draw(0, 0);
		}
	}

	private void renderHealthBars(GameContainer container, Graphics graphics) {
		for (Enemy enemy : this.enemies) {
			int barLength = 30;
			int barHeight = 7;
			SlickHealthbar h = new SlickHealthbar(graphics, enemy.getX() - barLength / 2, enemy.getY() - 25 - barHeight,
					enemy.getMaxHealth(), barLength, barHeight);
			h.setHealth(enemy.getHealth());
			h.setBordered(true);
			h.draw();
		}
	}

	private void renderTowers() {
		for (Tower[] towerArray : this.towers) {
			for (Tower tower : towerArray) {
				if (tower != null) {
					tower.draw();
				}
			}
		}
	}

	private void renderEnemies() {
		for (Enemy enemy : this.enemies) {
			if (enemy != null)
				enemy.draw();
		}
	}

	/**
	 * renders the transparent version of the tower's sprite when choosing a place
	 * 
	 * @param container
	 * @param graphics
	 */
	private void renderTowerShadow(GameContainer container, Graphics graphics) {

		if (this.currentTower != null && this.getMode() == 0) {
			Sprite sprite = this.currentTower.getSprite().clone();

			if (this.currentTowerPlaceable) {
				new SlickUnfilledRectangle(graphics, 50, 50, Color.green).draw(this.towerShadowX, this.towerShadowY);
			} else {
				new SlickUnfilledRectangle(graphics, 50, 50, Color.red).draw(this.towerShadowX, this.towerShadowY);
				sprite.setColor(1f, 0, 0);
			}

			sprite.draw(this.towerShadowX, this.towerShadowY);
		}
	}

	/**
	 * renders circles for tower radius and enemy radius and black box for FPS
	 * 
	 * @param container
	 * @param graphics
	 */
	private void renderDebug(GameContainer container, Graphics graphics) {
		if (this.debugMode) {
			for (Enemy enemy : this.enemies) {
				new SlickUnfilledEllipse(graphics, enemy.getRadius() * 2, enemy.getRadius() * 2, Color.blue).draw(enemy.getX(),
						enemy.getY());
			}
			for (int i = 0; i < this.towers.length; ++i) {
				for (int j = 0; j < this.towers[0].length; ++j) {
					if (this.towers[i][j] != null) {
						Tower currentTower = this.towers[i][j];
						new SlickUnfilledEllipse(graphics, currentTower.getRadius() * 2, currentTower.getRadius() * 2, Color.red)
								.draw(currentTower.getX() * this.currentTileLength + 25, currentTower.getY() * this.currentTileLength
										+ 25);
					}
				}
			}
			// create a black box that the FPS are visible
			new SlickRectangle(graphics, 100, 20, Color.black).draw(5, 10);
		}
	}

	@Override
	public void update(GameContainer container, int originalDelta) throws SlickException {

		if (originalDelta < 100) {
			this.passedMilliseconds += originalDelta;
			this.passedTime.setText(this.passedTimeToString());
			this.moneyAmount.setText("" + this.player.getMoney());
			int delta = (int) (originalDelta * this.speed);
			if (this.mode == 0) {
				for (Enemy enemy : this.enemies) {

					if (enemy != null)
						enemy.update(delta);
				}
				for (int i = 0; i < this.towers.length; ++i) {
					for (int j = 0; j < this.towers[0].length; ++j) {
						if (this.towers[i][j] != null) {
							this.towers[i][j].update(delta);
						}
					}
				}

				this.waveHandler.update(delta);
			}
			this.mouseEvents(container, delta);
			this.keyboardEvents(container, delta);

			if (this.player.getLives() <= 0) {
				this.mode = -1;

			}
			this.updateTowerShadow(container);
			for (Clickable clickable : this.clickables) {
				clickable.update(container);
			}
		}
	}

	private void updateTowerShadow(GameContainer container) {
		if (this.currentTower != null && this.getMode() == 0) {
			Input input = container.getInput();
			// old version of shadow Coordinates, with pixel accurate coordinates
			// this.towerShadowX = (int) (input.getMouseX() - this.currentTower.getSprite().getWidth() / 2);
			// this.towerShadowY = (int) (input.getMouseY() - this.currentTower.getSprite().getHeight() / 2);
			int x = input.getMouseX();
			int y = input.getMouseY();
			int newX = x / 50;
			int newY = y / 50;
			this.towerShadowX = newX * 50;
			this.towerShadowY = newY * 50;
			int[][] path = this.currentMapLayout.getPath();
			if (this.player.getMoney() < this.currentTower.getCost()) {
				this.currentTowerPlaceable = false;
			} else if (newY < 12 && x < Gameplay.INTERFACE_START_X && path[newY][newX] == 1 && this.towers[newY][newX] == null) {
				this.currentTowerPlaceable = true;
			} else {
				this.currentTowerPlaceable = false;
			}
		}
	}

	/**
	 * updates keyboard events i.e. button presses
	 * 
	 * @param container
	 * @param delta
	 */
	private void keyboardEvents(GameContainer container, int delta) {
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_I)) {
			this.debugMode = !this.debugMode;
			container.setShowFPS(this.debugMode);
			if (this.debugMode) {
				System.out.println("debug");
				this.player.setMoney(100000);
			} else {
				System.out.println("not debug");
				this.speed = 1f;
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
		if (input.isKeyPressed(Input.KEY_R)) {
			try {
				container.reinit();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (this.debugMode) {
			this.debugKeyboardEvents(container, delta);
		}

	}

	/**
	 * updates the keyboard events(button presse) only occuring in debug mode
	 * 
	 * @param container
	 * @param delta
	 */
	private void debugKeyboardEvents(GameContainer container, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ADD)) {
			this.speed *= 2f;
		}
		if (input.isKeyPressed(Input.KEY_SUBTRACT)) {
			this.speed /= 2f;
		}
		if (input.isKeyPressed(Input.KEY_L)) {
			this.enemies.add(this.enemyTypes.createEnemy(0));
		}
	}

	/**
	 * handles mouse events like clicks
	 * 
	 * @param container
	 * @param delta
	 */
	private void mouseEvents(GameContainer container, int delta) {
		if (this.mode == 0) {
			Input input = container.getInput();
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

				float x = input.getMouseX();
				float y = input.getMouseY();

				boolean buttonWasPressed = false;
				for (Clickable clickable : this.clickables) {
					if (clickable.collides((int) x, (int) y)) {
						buttonWasPressed = true;
						this.releaseAllClickables();
						clickable.onClick();

					}
				}
				if (!buttonWasPressed) {
					int newX = (int) x / this.currentTileLength;
					int newY = (int) y / this.currentTileLength;
					if (this.currentTower != null) {
						int[][] path = this.currentMapLayout.getPath();
						int cost = this.currentTower.getCost();
						if (x < Gameplay.INTERFACE_START_X && path[newY][newX] == 1 && this.towers[newY][newX] == null
								&& this.player.getMoney() - cost >= 0) {
							Tower bufferTower = this.currentTower.clone();
							bufferTower.setX(newX);
							bufferTower.setY(newY);
							bufferTower.getSprite().setAlpha(1f);
							this.towers[newY][newX] = bufferTower;
							this.player.reduceMoney(cost);
							this.currentTower = null;
							this.releaseAllClickables();

						}
					}
				}
				this.mouseWasClicked = true;

			} else if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				this.currentTower = null;
				this.releaseAllClickables();
			}
			// checks if mouse button was released again after being pressed
			if (this.mouseWasClicked && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

				this.mouseWasClicked = false;
				this.releaseAllClickablesNotTowerButtons();

			}
		}
	}

	/*
	 * private void debugPath() { Sprite s = new Sprite("Unbenannt-2.png"); int[][] path = this.currentMapLayout.getPath(); for (int i
	 * = 0; i < path.length; ++i) { for (int j = 0; j < path[0].length; ++j) { if (path[i][j] == 5) {// for now th epath has not the
	 * value 0 in // the array path, but 5 s.draw(j * this.currentTileLength, i * this.currentTileLength); } }
	 * 
	 * } }
	 */

	private void releaseAllClickables() {
		for (Clickable clickable : this.clickables) {
			clickable.onRelease();
		}
	}

	private void releaseAllClickablesNotTowerButtons() {
		for (Clickable clickable : this.clickables) {
			if (!(clickable.getClass() == this.towerButton1.getClass())) {
				clickable.onRelease();
			}
		}
	}

	public ConcurrentLinkedQueue<Enemy> getEnemies() {
		return this.enemies;
	}

	public Waypoint getWaypoints() {
		return this.currentMapLayout.getWaypoints();
	}

	public void reduceLives() {
		this.player.reduceLives();
		this.numberLives.setText("" + this.player.getLives());
	}

	public EnemyTypes getEnemyTypes() {
		return this.enemyTypes;
	}

	private String millisecondsToTimeString(int milliseconds) {
		int seconds = milliseconds / 1000;
		int minutes = seconds / 60;
		int hours = minutes / 60;
		seconds %= 100;
		minutes %= 60;
		String secondsString = this.makeTwoDecimals(seconds);
		String minutesString = this.makeTwoDecimals(minutes);
		String hoursString = this.makeTwoDecimals(hours);

		if (hours > 0) {
			return hoursString + ":" + minutesString + ":" + secondsString;
		} else {
			return minutesString + ":" + secondsString;
		}
	}

	private String makeTwoDecimals(int number) {
		if (number >= 10) {
			return "" + number;
		} else {
			return "0" + number;
		}
	}

	private String passedTimeToString() {
		return this.millisecondsToTimeString(this.passedMilliseconds);
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getMode() {
		return this.mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Tower getCurrentTower() {
		return this.currentTower;
	}

	public void setCurrentTower(Tower currentTower) {
		this.currentTower = currentTower;
	}

}
