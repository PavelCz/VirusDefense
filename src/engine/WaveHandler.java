package engine;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import towerDefense.Gameplay;

public class WaveHandler {
	private LinkedList<Wave> waves;
	private Gameplay game;
	private int numberEnemies;
	private int index = 0;
	private Wave currentWave;
	private int delta;
	private int timeBetweenWaves;
	private int timeBetweenEnemies = 500;
	private boolean done = false;

	public WaveHandler(Gameplay game, int timeBetweenWaves, String path) {
		this.game = game;
		this.waves = new LinkedList<Wave>();
		this.timeBetweenWaves = timeBetweenWaves;
		this.delta = this.timeBetweenWaves;
		List<String> lines = TextFileToString.getString("./src/data/files/waves/" + path);
		this.initWaves(lines);
		
		
	}
	
	private void initWaves(List<String> lines) {
		int i = 0;
		
		this.numberEnemies = Integer.parseInt(lines.get(i));
		++i;
		System.out.println(this.numberEnemies);
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
				// calculates the next enemy type, creates a new object with
				// that type and adds the object to the enemies of the game
				this.game.getEnemies().add(
						this.game.getEnemyTypes().createEnemy(
								this.calculateEnemy(this.currentWave)));
				this.index--;
			}
		}
	}
}
