package engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextFileToString {
	private int[] bytes;
	public TextFileToString(String path) throws IOException {
		FileInputStream file = new FileInputStream(path);
		int size = file.available();
		bytes = new int[size];
		int t;
		int i = 0;
		while (-1 != (t = file.read())) {
			bytes[i] = t;
			++i;
		}

	}
	
	public String toString() {
		char[] chars = new char[bytes.length];
		for(int i = 0; i< bytes.length; ++i) {
			chars[i] =(char) bytes[i];
			
		}
		
		return new String(chars);
	}
}
