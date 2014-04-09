package engine;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import engine.gui.GUI;

public abstract class GameComponent {

	protected List<GUI> guiElements;

	public abstract void init(GameContainer container) throws SlickException;

	public abstract void update(GameContainer container, int delta) throws SlickException;

	public abstract void render(GameContainer container, Graphics graphics) throws SlickException;
}
