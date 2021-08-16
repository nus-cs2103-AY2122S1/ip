package commandImpl;

import commandInterface.CommandLogicUnit;
import model.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.IntStream;

import static util.Display.printSentence;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandLogicUnitImpl implements CommandLogicUnit {
	private final ArrayList<Task> taskList = new ArrayList<>();
	
	/**
	 * command processing functions, in this implementation it accepts all the commands
	 *
	 * @param command   one of the command from Command enum class
	 * @param arguments corresponding arguments for each command
	 */
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
			case DELETE:
				processDelete(Integer.parseInt(arguments.getOrDefault("index", "-1")) - 1);
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
			throw new IllegalArgumentException("non valid index for marking the task done");
		}
		
		taskList.get(index).checkDone();
		printSentence("Nice! I've marked this task as done: \n" +
				"\t [X] " + taskList.get(index).getDesc());
	}
	
	/**
	 * delete the task in the list
	 *
	 * @param index 0-indexed integer
	 */
	private void processDelete(int index) {
		if (index >= taskList.size() || index < 0) {
			throw new IllegalArgumentException("non valid index for deletion");
		}
		
		Task removedTask = taskList.remove(index);
		printSentence(" Noted. I've removed this task: \n" +
				"\t" + removedTask.toString() + "\n" +
				" Now you have " + taskList.size() + " tasks in the list.");
	}
	
	private void processBye() {
		printSentence(" Bye. Hope to see you again soon!");
		System.exit(0);
	}
	
	private void processList() {
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
}
