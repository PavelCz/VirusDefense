package engine;

public class Player {
	private int lives;
	private int money;
	private int score;

	public Player(int lives, int money, int score) {
		this.lives = lives;
		this.money = money;
		this.score = score;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void reduceMoney(int money) {
		this.money -= money;
	}

	public void addMoney(int money) {
		this.money += money;
	}

	public int getLives() {
		return this.lives;
	}

	public void reduceLives() {
		this.lives--;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

}
