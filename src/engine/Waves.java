package engine;

public class Waves {
	private int number;
	private int[] enemy;

	public Waves(int number, int[] enemy) {
		this.number = number;
		this.enemy = enemy;
	}

	public int getNumber() {
		return number;

	}

	public int getPercentage(int index) {
		return enemy[index];
		
	}
	public int[] getEnemy(){
		return enemy;
	}

	private Enemy1 e1;
}
