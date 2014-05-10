package towerDefense;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import engine.TextFileToString;

public class StartGame {
	public static void main(String[] args) {
		try {
			TextFileToString tfts = new TextFileToString("./src/data/files/test.txt");
			System.out.println(tfts.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		TowerDefense game = new TowerDefense();
		AppGameContainer appGameContainer;
		try {
			appGameContainer = new AppGameContainer(game, 1024, 768, false);
			appGameContainer.start();

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
