import Implementation.CommandParserImpl;
import Implementation.CommandProcessorImpl;
import Interface.CommandParser;
import Interface.CommandProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Util.Display.printSentence;

/**
 * main driver program
 */
public class Duke {
    // initialize the buffered reader to take input from the console
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    // initialize the command processor from logic processing, use the commandParse to process the console input
    private static CommandProcessor commandProcessor = new CommandProcessorImpl();
    private static CommandParser commandParser = new CommandParserImpl(commandProcessor);
    
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
			commandParser.processInput(str);
		}
	}
	
	public static void starting() {
		printSentence(" Hello! I'm Dude\n" +
				" What can I do for you?\n");
	}
}
