package FileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOut {
	
	/*
	 * Example on how to use file input output in Java
	 * using buffered reader and buffered writter
	 * 
	 * 
	 * 
	 */
	
	public static void read() throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("hi.in"));
		
		while((line = in.readLine()) != null) {
			System.out.println(line);
			in.close();
		}
	}
	
	public static void print() {

		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("hi.in"));
			
			out.write("Hi This is the first line");
			out.newLine();
			out.write("New Line");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
