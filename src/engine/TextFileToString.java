package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileToString {
	private int[] bytes;
	private char[] chars;

	public static List<String> getLines(String path) {
		List<String> list = new ArrayList<String>();
		try {
			File f = new File(path);

			BufferedReader reader = new BufferedReader(new FileReader(f));

			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException x) {
			System.err.println(x);
		}
		return list;
	}

	public String getString() {

		return new String(this.chars);
	}

	public char[] getChars() {
		return this.chars;
	}
}
