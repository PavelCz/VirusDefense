package engine;

import java.util.ArrayList;
import java.util.List;

public class MapHandler {
	private List<MapLayout> maps;
	
	public MapHandler() {
		this.maps = new ArrayList<>();
	}
	
	public void add(String path, String picture) {
		this.maps.add(new MapLayout("maps/" + path, "veins/bg.png", 64, picture));
	}
	
	public MapLayout get(int i) {
		return this.maps.get(i);
	}
	
	public int getLength() {
		return this.maps.size();
	}
}
