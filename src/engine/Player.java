package engine;

public class Player {
	private int lives;
	private int money;
	public Player() {
		this.lives = 10;
		this.money = 100;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void reduceMoney(int money) {
		this.money -= money;
	}
	public int getLives(){
		return lives;
	}
	public void reduceLives(){
		this.lives--;
	}
	
}
