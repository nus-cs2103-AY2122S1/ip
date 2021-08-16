package Implementation;

import Interface.CommandProcessor;
import Model.Command;

import java.util.List;

import static Util.Display.printSentence;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandProcessorImpl implements CommandProcessor {
	@Override
	public void processCommand(Command command, List<String> arguments) {
		switch (command) {
			case BYE:
				processBye();
			case INVALID:
				processInvalid(arguments.get(0));
		}
	}
	
	public void processBye() {
		printSentence(" Bye. Hope to see you again soon!\n");
		System.exit(0);
	}
	
	public void processInvalid(String sentence) {
		printSentence(" " + sentence + "\n");
	}
}
