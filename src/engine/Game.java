package engine;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import engine.graphics.Sprite;
import engine.gui.Button;
import engine.gui.GUI;
import engine.gui.StaticText;
import engine.gui.TowerButton;

public class Game extends BasicGame {
	private List<Drawable> drawables;
	private List<GUI> guiElements;
	private List<Button> buttons;
	private boolean showFPS;
	private boolean mouseWasClicked;

	private Background gameBackground;
	private Button button1, button2;
	private Tower t;
	private int[][] path;
	private Tower[][] towers;
	private TowerButton towerButton1;
	private Tower currentTower;
	private Enemy1 e;
	private Waypoint startingWaypoint;
	private static Player player;
	private static StaticText lives;

	public Game() {
		super("TowerDefense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.t = new ShootingTower(1, 2, new Sprite("roteBlutk_klein.png"));
		this.towerButton1 = new TowerButton(13 * 50, 0, "button1.png", "button2.png", new ShootingTower(0, 0, new Sprite(
				"roteBlutk_klein.png")));

		this.player = new Player();
		MapLayoutFromImage mapLayout = new MapLayoutFromImage("/maps/map.png");
		this.path = mapLayout.getPath();
		this.startingWaypoint = mapLayout.getStartingPoint();

		this.towers = new Tower[12][13];

		this.drawables = new ArrayList<Drawable>();

		

		this.e = new Enemy1(this.startingWaypoint);

		this.mouseWasClicked = false;
		this.showFPS = false;
		this.gameBackground = new Background(1f);

		button1 = new Button(300, 300, "button1.png", "button2.png");
		button2 = new Button(200, 300, "button1.png", "button2.png");

		// add all objects that need to be drawn to the respectable arrays
		// entities
		this.drawables.add(new TestEntity(10, 10, 180, "A.bmp"));
		this.drawables.add(this.t);

		// GUI
		this.guiElements = new ArrayList<GUI>();
		lives = new StaticText(700, 200, 10, Color.black, "" + player.getLives());
		this.guiElements.add(this.button1);
		this.guiElements.add(this.button2);
		this.guiElements.add(lives);
		this.guiElements.add(this.towerButton1);

		// Buttons; this has nothing to do with the draw sequence
		this.buttons = new ArrayList<Button>();
		this.buttons.add(this.button1);
		this.buttons.add(this.button2);
		this.buttons.add(this.towerButton1);

		//

		container.setShowFPS(this.showFPS);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		this.gameBackground.draw();

		this.e.draw();
		for (Tower[] towerArray : this.towers) {
			for (Tower tower : towerArray) {
				if (tower != null) {
					tower.draw();
				}
			}
		}
		for (Drawable entity : this.drawables) {
			entity.draw();
		}
		for (GUI guiElement : this.guiElements) {
			guiElement.draw();
		}
		

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

		this.e.update(delta);

		this.mouseEvents(container, delta);

		if (Game.player.getLives() <= 0) {
			System.out.println("Game Over!");
		}

	}

	private void mouseEvents(GameContainer container, int delta) {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			float x = input.getMouseX();
			float y = input.getMouseY();

			boolean buttonWasPressed = false;
			for (Button button : this.buttons) {
				if (button.checkCollision(x, y)) {
					buttonWasPressed = true;
					this.releaseAllButtons();
					button.onClick();
					this.currentTower = button.getTower();
					if (this.currentTower != null) {
						this.towerButton1.onClick();
					}
				}
			}
			if (!buttonWasPressed) {
				int newX = (int) x / 50;
				int newY = (int) y / 50;
				if (this.currentTower != null) {
					if (path[newY][newX] == 1 && towers[newY][newX] == null) {
						if (x < 650) {
							Tower bufferTower = this.currentTower.clone();
							bufferTower.setX(newX);
							bufferTower.setY(newY);
							this.towers[newY][newX] = bufferTower;

							this.currentTower = null;
							this.releaseAllButtons();
						}
					}
				}
			}

			this.mouseWasClicked = true;

		}
		// checks if mouse button was released again after being pressed
		if (this.mouseWasClicked && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

			this.mouseWasClicked = false;
			this.releaseAllButtonsNotTowerButton();

		}
	}

	private void debugPath() {
		Sprite s = new Sprite("Unbenannt-2.png");
		;
		for (int i = 0; i < this.path.length; ++i) {
			for (int j = 0; j < this.path[0].length; ++j) {
				if (this.path[i][j] == 5) {// for now th epath has not the value 0 in the array path, but 5
					s.draw(j * 50, i * 50);
				}
			}

		}
	}

	private void releaseAllButtons() {
		for (Button button : this.buttons) {
			button.onRelease();
		}
	}

	private void releaseAllButtonsNotTowerButton() {
		for (Button button : this.buttons) {
			if (button.getTower() == null) {
				button.onRelease();
			}
		}
	}

	public static void reduceLives() {
		Game.player.reduceLives();
		lives.setText("" + Game.player.getLives());
	}

}
