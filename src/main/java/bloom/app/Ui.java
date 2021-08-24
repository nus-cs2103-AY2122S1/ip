package bloom.app;

import bloom.constant.Drawing;
import bloom.exception.command.BloomUnknownCommandException;

import java.util.Scanner;

public class Ui {
	
	private static boolean isRunning = true;
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		run("greet");
		String userInput;
		while (Ui.isRunning) {
			userInput = scanner.nextLine();
			run(userInput);
		}
	}
	
	public void run(String userInput) {
		Parser parser = new Parser();
		System.out.println(Drawing.HORIZONTAL_LINE.getDrawing());
		try {
			parser.parse(userInput).run();
		} catch (BloomUnknownCommandException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(Drawing.HORIZONTAL_LINE.getDrawing());
	}
	
	public void stop() {
		Ui.isRunning = false;
	}
}
