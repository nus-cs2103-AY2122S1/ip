import commandImpl.CommandLogicUnitImpl;
import commandImpl.CommandProcessorImpl;
import commandInterface.CommandLogicUnit;
import commandInterface.CommandProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static util.Display.printSentence;

/**
 * main driver program
 */
public class Duke {
	// initialize the buffered reader to take input from the console
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	// initialize the command processor from logic processing, use the commandParse to process the console input
	private static final CommandLogicUnit commandLogicUnit = new CommandLogicUnitImpl();
	private static final CommandProcessor commandProcessor = new CommandProcessorImpl(commandLogicUnit);
	
	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) throws IOException {
		String logo = " ____        ____      \n"
				+ "|  _ \\ _   _|  _ \\____\n"
				+ "| | | | | | | | |/ __ \\\n"
				+ "| |_| | |_| | |_|   __/\n"
				+ "|____/ \\__,_|____\\____|\n";
		System.out.println("Hello from\n" + logo + "by Simon - CS2103T/2122S1");
		
		starting();
		
		while (true) {
			String str = reader.readLine();
			commandProcessor.processInput(str);
		}
	}
	
	public static void starting() {
		printSentence(" Hello! I'm Dude\n" +
				" What can I do for you?");
	}
}
