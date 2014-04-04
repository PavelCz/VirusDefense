package engine;

public class Wave {
	private int number;
	private int[] percentages;

	public Wave(int number, int[] enemy) {
		this.number = number;
		this.percentages = enemy;
	}

	public int getNumber() {
		return this.number;

	}

	public int getPercentage(int index) {
		return this.percentages[index];

	}

	public int[] getPercentages() {
		return this.percentages;
	}

	private Enemy1 e1;
}
