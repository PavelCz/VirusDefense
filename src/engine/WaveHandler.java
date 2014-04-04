package engine;

import java.util.LinkedList;

public class WaveHandler {
	private LinkedList<Wave> waves;
	private Game game;
	private int index = 0;
	private Wave currentWave;
	private int delta;
	private int timeBetweenWaves;
	private int timeBetweenEnemies = 500;

	public WaveHandler(Game game, int timeBetweenWaves) {
		this.game = game;
		waves = new LinkedList<Wave>();
		this.timeBetweenWaves = timeBetweenWaves;
		this.delta = this.timeBetweenWaves;
	}

	public void addWave(Wave wave) {
		waves.add(wave);
	}

	private int calculateEnemy(Wave wave) {
		int n = (int) (Math.random() * 100);
		int[] waves = wave.getPercentages();
		int p = 100;
		for (int i = 0; i < waves.length; i++) {
			p = p - waves[i];
			if (n > p) {
				return i;
			}
		}
		return -1;
	}

	public void update(int delta) {
		this.delta -= delta;
		if (game.getEnemies().isEmpty() && index <= 0) {
			
			if (!waves.isEmpty()) {
				index = waves.peek().getNumber();
				currentWave = waves.poll();
				this.delta = this.timeBetweenWaves;
			}
		}
		if (this.delta <= 0) {
			this.delta = this.timeBetweenEnemies;
			if (index > 0) {
				if (this.calculateEnemy(currentWave) == 0) {
					game.getEnemies().add(new Enemy1(game.getWaypoints(), game));
				}
				index--;
			}
		}
	}
}
