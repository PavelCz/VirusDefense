package engine;

import java.util.List;

import engine.graphics.BackgroundTiles;

import towerDefense.Gameplay;

public class Level {
	private MapLayout map;
	private BackgroundTiles mapBackgound;
	private WaveHandler waves;
	private EnemyTypeHandler enemies;

	public Level(String levelPath, Gameplay game) {
		this.initLevel(TextFileToString.getLines(levelPath), game);
	}

	private void initLevel(List<String> lines, Gameplay game) {

		this.map = new MapLayout(lines.get(0), Gameplay.DEFAULT_SIZE, lines.get(1));
		this.mapBackgound = new BackgroundTiles(0.5f, lines.get(2), this.map.getNumberTilesWidth(), this.map.getNumberTilesWidth());
		this.waves = new WaveHandler(game, 2000, lines.get(3));
		this.enemies = new EnemyTypeHandler(game, lines.get(4));

	}
}
