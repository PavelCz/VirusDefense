package engine;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import engine.graphics.gui.StaticText;

public class Game extends BasicGame {
	private boolean showFPS = false;
	Font font;
	TrueTypeFont overallFont;
	private TestEntity t;
	private StaticText text;

	public Game() {
		super("TowerDefense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// setting the font for Text
		font = new Font("Verdana", Font.PLAIN, 20);
		overallFont = new TrueTypeFont(font, true);

		// initializing all game objects
		this.t = new TestEntity(10, 10, 180, "./data/A.bmp");
		this.text = new StaticText(overallFont, 100, 100, Color.green, "Hello World");

		container.setShowFPS(this.showFPS);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		t.draw();
		text.draw();

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			System.out.println(input.getMouseX() + ", " + input.getMouseY());
		}

		// if(Mouse.isButtonDown(0)) {
		// System.out.println(Mouse.getDX() + ", " + Mouse.getDY());
		// }

	}

	public static void main(String[] args) {
		Game game = new Game();
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
