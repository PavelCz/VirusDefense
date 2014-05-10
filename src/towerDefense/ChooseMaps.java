package towerDefense;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import towerDefense.towers.Tower;
import engine.GameComponent;
import engine.MapHandler;
import engine.MapLayout;
import engine.gui.Button;
import engine.gui.Clickable;

public class ChooseMaps extends GameComponent {

	private Button button, left, right;
	private int page, lastPage;

	private MapLayout currentMap;

	private MapHandler mapHandler = new MapHandler();

	public ChooseMaps(TowerDefense game) {
		super(game);
		this.page = 0;
		this.mapHandler.add("map.png", "previews/Blutkreislauf-rachen.png");
		this.mapHandler.add("testMapHor.png", "previews/Blutkreislauf-Herz.jpg");
		this.mapHandler.add("testMapVer.png", "previews/Blutkreislauf-Niere.jpg");
		this.currentMap = this.mapHandler.get(page);
		this.button = new Button(100, 150, currentMap.getPicture(),
				currentMap.getPicture());
		this.left = new Button(50, 200, "left.png", "left.png");

		this.right = new Button(200, 200, "right.png", "right.png");

		this.clickables.add(this.button);
		this.clickables.add(this.left);
		this.clickables.add(this.right);
		this.guiElements.add(this.button);
		this.guiElements.add(this.left);
		this.guiElements.add(this.right);
		this.lastPage = mapHandler.getLength()-1;
	}

	public void update(GameContainer container, int delta) {
		this.mouseEvents(container, delta);
		this.button.setUnclickedButton(this.currentMap.getPicture());
	}

	private void mouseEvents(GameContainer container, int delta) {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			float x = input.getMouseX();
			float y = input.getMouseY();

			for (Clickable clickable : this.clickables) {
				if (clickable.collides((int) x, (int) y, 1f)) {
					if (clickable == this.left) {
						--this.page;
						if (this.page < 0) {
							this.page = this.lastPage;
						}

						this.currentMap = mapHandler.get(this.page);
					} else if (clickable == this.right) {
						this.page += 1;
						if (this.page > this.lastPage) {
							this.page = 0;
						}
						this.currentMap = mapHandler.get(this.page);
					} else if (clickable == this.button) {
						this.game.setMapLayout(currentMap);
						this.game.initGameplay(container);

						this.game.setMode(TowerDefense.MODE_GAME);
					}

				}
			}

		}
	}

}
