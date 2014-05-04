package engine.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundHandler {
	private Map<String,Sound> sounds = new HashMap<String,Sound>();
	
	public void add(String name, String location) {
		try {
			this.sounds.put(name, new Sound(location));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
