package commandimpl;

import dao.TaskDao;
import icommand.ICommandLogicUnit;
import model.Command;
import model.Deadline;
import model.Event;
import model.Task;
import model.ToDos;

import java.util.Map;

import static util.Display.printIndexedList;
import static util.Display.printSentence;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandLogicUnitImpl implements ICommandLogicUnit {
	/** Data access object for Task */
	private final TaskDao taskDao;
	
	/**
	 * Constructor of CommandLogicUnitImpl that processes all the available commands.
	 *
	 * @param taskDao TaskDao.
	 */
	public CommandLogicUnitImpl(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
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
	
	private void processBye() {
		printSentence(" Bye. Hope to see you again soon!");
		System.exit(0);
	}
	
	/**
	 * Prints the entire list of tasks whether its done or not done.
	 */
	private void processList() {
		printIndexedList(taskDao.getAll());
	}
	
	private void processAdd(Task task) {
		taskDao.addTask(task);
		
		printSentence(" Got it. I've added this task: \n" +
				"\t" + task.toString() + "\n" +
				" Now you have " + taskDao.getSize() + " tasks in the list.");
	}
	
	/**
	 * Processes the DONE Command.
	 *
	 * @param index 0-indexed integer
	 */
	private void processDone(int index) {
		taskDao.markDone(index);
		
		printSentence("Nice! I've marked this task as done: \n" +
				"\t [X] " + taskDao.getTask(index).getDesc());
	}
	
	/**
	 * Processes the DELETE Command.
	 *
	 * @param index 0-indexed integer
	 */
	private void processDelete(int index) {
		Task deletedTask = taskDao.deleteTask(index);
		
		printSentence(" Noted. I've removed this task: \n" +
				"\t" + deletedTask.toString() + "\n" +
				" Now you have " + taskDao.getSize() + " tasks in the list.");
	}
}
