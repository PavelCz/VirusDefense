package engine;

import java.util.ArrayList;
import java.util.List;

public class MapHandler {
	private List<MapLayout> maps;
	
	public MapHandler() {
		this.maps = new ArrayList<>();
	}
	
	public void add(String path) {
		this.maps.add(new MapLayout(path, "veins/bg.png", 64));
	}
}
