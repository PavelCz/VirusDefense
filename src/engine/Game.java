package engine;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import engine.graphics.Text;
import engine.graphics.gui.StaticText;

public class Game extends BasicGame {
	
	
	private TestEntity t;
	private StaticText text;
	
	
	public Game() {
		super("TowerDefense");
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		t.draw();
		text.draw(graphics);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.t = new TestEntity(10, 10, 180, "./data/A.bmp");
		this.text = new StaticText(100, 100, "Hello World");
		
		container.setShowFPS(false);
		
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
			System.out.println(input.getMouseX() + ", " + input.getMouseY());
		}
		
		
//		if(Mouse.isButtonDown(0)) {
//			System.out.println(Mouse.getDX() + ", " + Mouse.getDY());
//		}
		
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
