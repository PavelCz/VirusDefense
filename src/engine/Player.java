package engine;

public class Player {
	private int lives;
	public Player() {
		this.lives = 10;
	}
	public int getLives(){
		return lives;
	}
	public void reduceLives(){
		this.lives--;
	}
	
}
