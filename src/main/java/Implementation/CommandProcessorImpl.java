package Implementation;

import Interface.CommandProcessor;
import Model.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.IntStream;

import static Util.Display.printSentence;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandProcessorImpl implements CommandProcessor {
	private final ArrayList<Task> taskList = new ArrayList<>();
	
	@Override
	public void processCommand(Command command, Map<String, String> arguments) {
		switch (command) {
			case BYE:
				processBye();
				break;
			case LIST:
				processList();
				break;
			case DEADLINE:
				processAdd(new Deadline(
						arguments.getOrDefault("description", "default deadline"),
						arguments.getOrDefault("timing", "default timing")
				));
				break;
			case EVENT:
				processAdd(new Event(
						arguments.getOrDefault("description", "default event"),
						arguments.getOrDefault("timing", "default timing")
				));
				break;
			case TODOS:
				processAdd(new ToDos(arguments.getOrDefault("description", "default todo")));
				break;
			case DONE:
				// convert 1-indexing to 0-indexing
				processDone(Integer.parseInt(arguments.getOrDefault("index", "-1")) - 1);
				break;
			case INVALID:
				processInvalid("invalid command: " + arguments.getOrDefault("message", "default message"));
				break;
			default:
				printSentence("command not recognized by processor");
		}
	}
	
	private void processAdd(Task task) {
		taskList.add(task);
		
		printSentence(" Got it. I've added this task: \n" +
				"\t" + task.toString() + "\n" +
				" Now you have " + taskList.size() + " tasks in the list.");
	}
	
	/**
	 * mark the task in the list done
	 *
	 * @param index 0-indexed integer
	 */
	public void processDone(int index) {
		if (index >= taskList.size() || index < 0) {
			printSentence(" Non-existent task");
			return;
		}
		
		taskList.get(index).checkDone();
		printSentence("Nice! I've marked this task as done: \n" +
				"\t [X] " + taskList.get(index).getDesc());
	}
	
	public void processBye() {
		printSentence(" Bye. Hope to see you again soon!");
		System.exit(0);
	}
	
	public void processList() {
		StringBuilder stringBuilder = new StringBuilder();
		
		IntStream.range(1, taskList.size() + 1)
				.mapToObj(index -> {
					Task task = taskList.get(index - 1);
					return " " + index + ". " + task + "\n";
				})
				.forEach(stringBuilder::append);
		
		// remove the last extra \n if there is at least an item in the list
		if (this.taskList.size() > 0) stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		
		printSentence(stringBuilder.toString());
	}
	
	public void processInvalid(String sentence) {
		printSentence(" " + sentence);
	}
}
