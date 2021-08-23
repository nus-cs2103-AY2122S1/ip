package bloom.app;

import bloom.constant.Drawing;
import bloom.exception.command.BloomUnknownCommandException;

import java.util.Scanner;

public class Ui {
	
	public static boolean isRunning = true;
	
	private final Parser parser = new Parser();
	
	private final Scanner scanner = new Scanner(System.in);
	
	public void start() {
		run("greet");
		String userInput;
		while (isRunning) {
			userInput = scanner.nextLine();
			run(userInput);
		}
	}
	
	public void run(String userInput) {
		System.out.println(Drawing.HORIZONTAL_LINE.getDrawing());
		try {
			parser.parse(userInput).run();
		} catch (BloomUnknownCommandException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(Drawing.HORIZONTAL_LINE.getDrawing());
	}
}
