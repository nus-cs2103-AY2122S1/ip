package Implementation;

import Interface.CommandProcessor;
import Model.Command;
import Model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static Util.Display.printSentence;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandProcessorImpl implements CommandProcessor {
	private final ArrayList<Task> taskList = new ArrayList<>();
	
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
			case DONE:
				processDone(Integer.parseInt(arguments.get(0)));
				break;
			case INVALID:
				processInvalid(arguments.get(0));
				break;
		}
	}
	
	private void processAdd(String desc) {
		Task newTask = new Task(desc);
		taskList.add(newTask);
		
		printSentence(" added: " + desc);
	}
	
	/**
	 * mark the task in the list done
	 *
	 * @param index 0-indexed integer
	 */
	public void processDone(int index) {
		if (index >= taskList.size()) {
			printSentence(" Non-existent task");
			return;
		}
		
		taskList.get(index).checkDone();
		printSentence("Nice! I've marked this task as done: \n" +
				"\t [X] " + taskList.get(index));
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
					return " " + index + ". " + (task.isDone() ? "[X] " : "[ ] ") + task + "\n";
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
