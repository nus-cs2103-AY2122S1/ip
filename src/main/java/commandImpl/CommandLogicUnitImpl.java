package commandImpl;

import commandInterface.ICommandLogicUnit;
import dao.TaskDao;
import model.*;

import java.util.Map;

import static util.Display.printIndexedList;
import static util.Display.printSentence;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandLogicUnitImpl implements ICommandLogicUnit {
	
	// temporarily, the database is stored within dao, hence no need for DI
	private final TaskDao taskDao;
	
	public CommandLogicUnitImpl(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
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
		Task addedTask = taskDao.add(task);
		
		printSentence(" Got it. I've added this task: \n" +
				"\t" + addedTask.toString() + "\n" +
				" Now you have " + taskDao.size() + " tasks in the list.");
	}
	
	/**
	 * @param index 0-indexed integer
	 */
	private void processDone(int index) {
		Task markedTask = taskDao.markDone(index);
		
		printSentence("Nice! I've marked this task as done: \n" +
				"\t [X] " + markedTask.getDesc());
	}
	
	/**
	 * @param index 0-indexed integer
	 */
	private void processDelete(int index) {
		Task deletedTask = taskDao.delete(index);
		
		printSentence(" Noted. I've removed this task: \n" +
				"\t" + deletedTask.toString() + "\n" +
				" Now you have " + taskDao.size() + " tasks in the list.");
	}
	
	private void processBye() {
		printSentence(" Bye. Hope to see you again soon!");
		System.exit(0);
	}
	
	/**
	 * print the entire list of tasks whether its done or not done
	 */
	private void processList() {
		printIndexedList(taskDao.getAll());
	}
}
