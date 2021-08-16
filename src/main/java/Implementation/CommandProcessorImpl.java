package Implementation;

import Interface.CommandProcessor;
import Model.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static Util.Display.printSentence;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandProcessorImpl implements CommandProcessor {
	private ArrayList<String> taskList = new ArrayList<>();
	
	@Override
	public void processCommand(Command command, List<String> arguments) {
		switch (command) {
			case BYE:
				processBye();
				break;
			case LIST:
				processList();
				break;
			case ADD:
				processAdd(arguments.get(0));
				break;
			case INVALID:
				processInvalid(arguments.get(0));
				break;
		}
	}
	
	private void processAdd(String sentence) {
		taskList.add(sentence);
		printSentence(" added: " + sentence);
	}
	
	public void processBye() {
		printSentence(" Bye. Hope to see you again soon!");
		System.exit(0);
	}
	
	public void processList() {
		StringBuilder stringBuilder = new StringBuilder();
		
		IntStream.range(1, taskList.size() + 1)
				.mapToObj(index -> " " + index + ". " + taskList.get(index - 1) + "\n")
				.forEach(stringBuilder::append);
		
		// remove the last extra \n
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		
		printSentence(stringBuilder.toString());
	}
	
	public void processInvalid(String sentence) {
		printSentence(" " + sentence);
	}
}
