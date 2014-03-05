package engine;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
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
	private boolean showFPS;
	private boolean mouseWasClicked;

	private Background gameBackground;
	private Button button1, button2;
	private Tower t;
	private boolean[][] path;
	private Tower[][] towers;
	private TowerButton tb;
	private Tower currentTower;

	public Game() {
		super("TowerDefense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.t = new ShootingTower(1, 2, new Sprite("./data/roteBlutk_klein.png"));
		this.tb = new TowerButton(13 * 50, 0, "./data/button1.png",
				"./data/button2.png", new ShootingTower(0, 0, new Sprite(
						"./data/roteBlutk_klein.png")));
		this.path = new boolean[12][13];
		this.towers = new Tower[12][13];
		this.path[0] = new boolean[] { false, true, false, false, false, false,
				false, false, false, false, false, false, false };
		this.path[1] = new boolean[] { false, true, false, false, false, false,
				false, false, false, false, false, false, false };
		this.path[2] = new boolean[] { false, true, true, true, true, true,
				true, true, true, true, true, false, false };
		this.path[3] = new boolean[] { false, false, false, false, false,
				false, false, false, false, false, true, false, false };
		this.path[4] = new boolean[] { false, false, false, false, false,
				false, false, false, false, false, true, false, false };
		this.path[5] = new boolean[] { false, true, true, true, true, true,
				true, true, true, true, true, false, false };
		this.path[6] = new boolean[] { false, true, false, false, false, false,
				false, false, false, false, false, false, false };
		this.path[7] = new boolean[] { false, true, false, false, false, false,
				false, false, false, true, true, true, false };
		this.path[8] = new boolean[] { false, true, false, false, true, true,
				true, true, false, true, false, true, false };
		this.path[9] = new boolean[] { false, true, false, false, true, false,
				false, true, false, true, false, true, false };
		this.path[10] = new boolean[] { false, true, true, true, true, false,
				false, true, true, true, false, true, false };
		this.path[11] = new boolean[] { false, false, false, false, false,
				false, false, false, false, false, false, true, false };

		this.drawables = new ArrayList<Drawable>();
		this.guiElements = new ArrayList<GUI>();
		this.mouseWasClicked = false;
		this.showFPS = false;
		this.gameBackground = new Background(1f);

		button1 = new Button(300, 300, "./data/button1.png",
				"./data/button2.png");
		button2 = new Button(200, 300, "./data/button1.png",
				"./data/button2.png");

		// add all objects that need to be drawn to the respectable arrays
		// entities
		this.drawables.add(new TestEntity(10, 10, 180, "./data/A.bmp"));
		this.drawables.add(this.t);

		// GUI
		this.guiElements.add(this.button1);
		this.guiElements.add(this.button2);
		this.guiElements.add(new StaticText(100, 100, 10, Color.green,
				"Hello World"));
		this.guiElements.add(this.tb);

		container.setShowFPS(this.showFPS);

	}

	@Override
	public void render(GameContainer container, Graphics graphics)
			throws SlickException {
		this.gameBackground.draw();
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
	public void update(GameContainer container, int delta)
			throws SlickException {

		this.mouseEvents(container, delta);

	}

	private void mouseEvents(GameContainer container, int delta) {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			float x = input.getMouseX();
			float y = input.getMouseY();

			if (button1.checkCollision(x, y)) {
				button1.onClick();
				this.tb.onRelease();
				this.currentTower = null;
			} else if (button2.checkCollision(x, y)) {
				button2.onClick();
				this.tb.onRelease();
				this.currentTower = null;
			} else if (this.tb.checkCollision(x, y)) {
				this.currentTower = this.tb.getTower();
				this.tb.onClick();
			} else {
				int newX = (int) x / 50;
				int newY = (int) y / 50;
				if (!path[newY][newX] && towers[newY][newX] == null) {
					if (this.currentTower != null) {
						if (x < 650) {
							Tower bufferTower = this.currentTower.clone();
							bufferTower.setX(newX);
							bufferTower.setY(newY);
							this.towers[newY][newX] = bufferTower;
							this.tb.onRelease();
							this.currentTower = null;
						}
					}
				}
			}

			this.mouseWasClicked = true;

		}
		// checks if mouse button was released again after being pressed
		if (this.mouseWasClicked
				&& !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

			this.mouseWasClicked = false;

			button1.onRelease();
			button2.onRelease();

		}
	}

	private void debugPath() {
		Sprite s = new Sprite("./data/Unbenannt-2.png");
		;
		for (int i = 0; i < this.path.length; ++i) {
			for (int j = 0; j < this.path[0].length; ++j) {
				if (this.path[i][j]) {
					s.draw(j * 50, i * 50);
				}
			}

		}
	}

}
