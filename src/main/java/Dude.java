import dao.TaskDao;
import dao.TaskDaoImpl;
import logic.CommandLogicUnitImpl;
import logic.ICommandLogicUnit;
import parser.CommandParserImpl;
import parser.ICommandParser;

import static util.Display.printSentence;

/**
 * main driver program
 */
public class Dude {
	// initialize the command processor from logic processing, use the commandParse to process the console input
	private static final TaskDao taskDao = new TaskDaoImpl();
	private static final ICommandLogicUnit commandLogicUnit = new CommandLogicUnitImpl(taskDao);
	
	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) {
		String logo = " ____        ____      \n"
				+ "|  _ \\ _   _|  _ \\____\n"
				+ "| | | | | | | | |/ __ \\\n"
				+ "| |_| | |_| | |_|   __/\n"
				+ "|____/ \\__,_|____\\____|\n";
		System.out.println("Hello from\n" + logo + "by Simon - CS2103T/2122S1");
		
		starting();
		
		try (ICommandParser commandProcessor = new CommandParserImpl(commandLogicUnit)) {
			while (true) {
				commandProcessor.processInput();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void starting() {
		printSentence(" Hello! I'm Dude\n" +
				" What can I do for you?");
	}
}
