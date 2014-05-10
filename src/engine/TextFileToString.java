package engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class TextFileToString {
	private int[] bytes;
	private char[] chars;
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
		char[] chars = new char[bytes.length];
		for(int j = 0; j< bytes.length; ++j) {
			chars[j] =(char) bytes[j];
			
		}

	}
	
	public String getString() {
		
		
		
		return new String(chars);
	}
	
	public char[] getChars() {
		return this.chars;
	}
}
