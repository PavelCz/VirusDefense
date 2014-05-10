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

	
	public static List<String> getString(String path) {
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

	// FileInputStream file = new FileInputStream(path);
	// int size = file.available();
	// bytes = new int[size];
	// int t;
	// int i = 0;
	// while (-1 != (t = file.read())) {
	// bytes[i] = t;
	// ++i;
	// }
	// this.chars = new char[bytes.length];
	// for (int j = 0; j < bytes.length; ++j) {
	// this.chars[j] = (char) bytes[j];
	//
	// }

	public String getString() {

		return new String(this.chars);
	}

	public char[] getChars() {
		return this.chars;
	}
}
