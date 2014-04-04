package engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WaveHandler {
	private LinkedList<Waves> waves;
	private Game game;
	private int index = 0;
	private Waves currentWave;
	private int delta = 1000;

	public WaveHandler(Game game) {
		this.game = game;
		waves = new LinkedList<Waves>();

	}

	public void addWave(Waves wave) {
		waves.add(wave);
	}

	private int calculateEnemy(Waves wave) {
		int n = (int) (Math.random() * 100);
		int[] waves = wave.getEnemy();
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
		if (game.getEnemy().isEmpty() && index <= 0) {

			if (!waves.isEmpty()) {
				index = waves.peek().getNumber();
				currentWave = waves.poll();
			}
		}
		if (this.delta <= 0) {
			this.delta = 1000;
			if (index != 0) {
				if (this.calculateEnemy(currentWave) == 0) {
					game.getEnemy().add(new Enemy1(game.getWaypoints(), game));
				}
				index--;
			}
		}
	}
}
