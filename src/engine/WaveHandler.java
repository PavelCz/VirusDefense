package engine;

import java.util.LinkedList;

public class WaveHandler {
	private LinkedList<Wave> waves;
	private Gameplay game;
	private int index = 0;
	private Wave currentWave;
	private int delta;
	private int timeBetweenWaves;
	private int timeBetweenEnemies = 500;
	private boolean done = false;

	public WaveHandler(Gameplay game, int timeBetweenWaves) {
		this.game = game;
		this.waves = new LinkedList<Wave>();
		this.timeBetweenWaves = timeBetweenWaves;
		this.delta = this.timeBetweenWaves;
	}

	public void addWave(Wave wave) {
		this.waves.add(wave);
	}

	private int calculateEnemy(Wave wave) {
		int n = (int) (Math.random() * 100);
		int[] waves = wave.getPercentages();
		int p = 100;
		for (int i = 0; i < waves.length; i++) {
			p = p - waves[i];
			if (n >= p) {
				return i;
			}
		}
		return -1;
	}

	public void update(int delta) {
		this.delta -= delta;
		if (this.waves.isEmpty() && this.done) {
			this.game.setMode(1);
		}
		if (this.game.getEnemies().isEmpty() && this.index <= 0) {

			if (!this.waves.isEmpty()) {
				this.index = this.waves.peek().getNumber();
				this.currentWave = this.waves.poll();
				this.delta = this.timeBetweenWaves;
			} else {
				this.done = true;
			}
		}
		if (this.delta <= 0) {
			this.delta = this.timeBetweenEnemies;
			if (this.index > 0) {
				// calculates the next enemy type, creates a new object with that type and adds the object to the enemies of the game
				this.game.getEnemies().add(this.game.getEnemyTypes().createEnemy(this.calculateEnemy(this.currentWave)));
				this.index--;
			}
		}
	}
}
