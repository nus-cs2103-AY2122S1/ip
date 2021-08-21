package me.yukun99.ip.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
	private final String outpath;
	private Scanner scanner;

	public Storage(String filepath) throws IOException {
		File input = new File(filepath + "\\input.txt");
		this.outpath = filepath + "\\ACTUAL.txt";
		System.out.println(filepath);
		File previous = new File(filepath + "\\ACTUAL.txt");
		if (previous.exists()) {
			previous.delete();
		}
		previous.createNewFile();
		try {
			this.scanner = new Scanner(input);
		} catch (FileNotFoundException e) {
			this.scanner = new Scanner(System.in);
		}
	}

	public Scanner getInputs() {
		return scanner;
	}

	public void saveMessage(String message) throws IOException {
		FileWriter output = new FileWriter(this.outpath, true);
		output.write(message);
		output.close();
	}
}
