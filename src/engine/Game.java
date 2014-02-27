package engine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	
	private TestEntity t;
	
	
	public Game() {
		super("TowerDefense");
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		t.draw();
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.t = new TestEntity(10, 10, 180, "./data/A.bmp");
		
		container.setShowFPS(false);
		
		
	}

	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
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
