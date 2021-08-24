import dao.TaskDao;
import dao.TaskDaoImpl;
import logic.CommandLogicUnitImpl;
import logic.ICommandLogicUnit;
import parser.IParser;
import parser.ParserImpl;
import ui.IUi;
import ui.UiImpl;

import java.util.Scanner;

/**
 * main driver program
 */
public class Dude {
	/** Scanner to take input from the console */
	private final Scanner scanner = new Scanner(System.in);
	
	private final ICommandLogicUnit commandLogicUnit;
	private final IUi ui;
	
	public Dude(ICommandLogicUnit commandLogicUnit, IUi ui) {
		this.commandLogicUnit = commandLogicUnit;
		this.ui = ui;
	}
	
	public static void main(String[] args) {
		TaskDao taskDao = new TaskDaoImpl();
		IUi ui = new UiImpl();
		ICommandLogicUnit commandLogicUnit = new CommandLogicUnitImpl(taskDao, ui);
		
		new Dude(commandLogicUnit, ui).run();
	}
	
	/**
	 * Runs Dude chatterbot.
	 */
	@SuppressWarnings("InfiniteLoopStatement")
	public void run() {
		String logo = " ____        ____      \n"
				+ "|  _ \\ _   _|  _ \\____\n"
				+ "| | | | | | | | |/ __ \\\n"
				+ "| |_| | |_| | |_|   __/\n"
				+ "|____/ \\__,_|____\\____|\n";
		System.out.println("Hello from\n" + logo + "by Simon - CS2103T/2122S1");
		
		starting();
		
		try {
			IParser commandProcessor = new ParserImpl(commandLogicUnit, ui);
			while (true) {
				String input = scanner.nextLine();
				commandProcessor.processInput(input);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void starting() {
		ui.printSentence(" Hello! I'm Dude\n" +
				" What can I do for you?");
	}
}
