package engine.graphics;

public class BackgroundTiles extends Background {

	public BackgroundTiles(float scale, String backgroundPath) {
		super(scale, backgroundPath);
		
	}
	
	@Override
	public void draw() {
		for(int i = 0; i< 13; ++i) {
			for(int j = 0; j< 12; ++j) {
				this.picture.draw(i * 50, j*50);
			}
		}
		

	}

}
