package duke.operation;

import duke.Parser.Parser;
import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * This is the TaskList class to contain task list and handle operations.
 */
public class TaskList {
	protected ArrayList<Task> taskList;

	public TaskList() {
		this.taskList = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> list) {
		this.taskList = list;
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	/**
	 * Adds tasks to the storeroom.
	 *
	 * @param command   command name
	 * @param inputLine input string
	 * @throws DukeException
	 */
	public void addTask(Command command, String inputLine) throws DukeException {
		String message;
		Task task;
		switch (command) {
		case TODO: {
			Parser.checkIfFirstWordValid(inputLine, "todo");
			if (Parser.isDescriptionEmpty(inputLine)) {
				message = "____________________________________________________________\n"
						+ "OOPS!!! The description of a "
						+ "todo"
						+ " cannot be empty.\n"
						+ "____________________________________________________________\n";
				throw new DukeException(message);
			}
			task = new ToDo(inputLine.substring(5));
			this.taskList.add(task);
			printAddTask(taskList.size(), task);
			break;
		}
		case DEADLINE: {
			Parser.checkIfFirstWordValid(inputLine, "deadline");
			if (Parser.isDescriptionEmpty(inputLine)) {
				message = "____________________________________________________________\n"
						+ "OOPS!!! The description of a "
						+ "deadline"
						+ " cannot be empty.\n"
						+ "____________________________________________________________\n";
				throw new DukeException(message);
			}
			task = Deadline.splitDeadline(inputLine);
			taskList.add(task);
			printAddTask(taskList.size(), task);
			break;
		}
		case EVENT: {
			Parser.checkIfFirstWordValid(inputLine, "event");
			if (Parser.isDescriptionEmpty(inputLine)) {
				message = "____________________________________________________________\n"
						+ "OOPS!!! The description of a "
						+ "event"
						+ " cannot be empty.\n"
						+ "____________________________________________________________\n";
				throw new DukeException(message);
			}
			task = Event.splitEvent(inputLine);
			taskList.add(task);
			printAddTask(taskList.size(), task);
			break;
		}
		default: {
			System.out.println("TaskList");
			message = "____________________________________________________________\n"
					+ "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
					+ "____________________________________________________________\n";
			throw new DukeException(message);
		}
		}
	}


	/**
	 * Marks a task as finished.
	 *
	 * @param nextLine input string
	 */
	public void finishTask(String nextLine) {
		int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
		Task doneTask = this.taskList.get(intValue - 1);
		doneTask.doneTask();
		this.taskList.set(intValue - 1, doneTask);
		printDoneTask(doneTask);
	}

	/**
	 * Prints task list.
	 */
	public void printList() {
		System.out.println("____________________________________________________________\n"
				+ "Here are the tasks in your list:");
		int counter = 1;
		for (Task taskForLoop : taskList) {
			System.out.println(counter
					+ "."
					+ taskForLoop);
			counter++;
		}
		System.out.println("____________________________________________________________\n");
	}

	/**
	 * Prints filtered task list after FIND command.
	 */
	public void printFilteredList() {
		System.out.println("____________________________________________________________\n"
				+ "Here are the tasks found by your keyword in your list:");
		int counter = 1;
		for (Task taskForLoop : taskList) {
			System.out.println(counter
					+ "."
					+ taskForLoop);
			counter++;
		}
		System.out.println("____________________________________________________________\n");
	}

	/**
	 * Deletes a task from the storeroom.
	 *
	 * @param nextLine input string
	 */
	public void deleteTask(String nextLine) {
		int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
		Task taskToDelete = taskList.get(intValue - 1);
		taskList.remove(intValue - 1);
		printDeleteTask(taskList.size(), taskToDelete);
	}

	/**
	 * Prints the task that is marked done.
	 */
	public void printDoneTask(Task doneTask) {
		System.out.println("____________________________________________________________\n"
				+ "Nice! I've marked this task as done:\n  "
				+ doneTask
				+ "\n"
				+ "____________________________________________________________\n");
	}

	/**
	 * Prints the message when a task is added.
	 *
	 * @param size current size of the storeroom
	 */
	public void printAddTask(int size, Task task) {
		System.out.println("____________________________________________________________\n"
				+ "Got it. I've added this task:\n  "
				+ task
				+ "\n"
				+ "Now you have "
				+ size
				+ " tasks in the list."
				+ "\n"
				+ "____________________________________________________________\n");
	}

	/**
	 * Prints the message when a task is deleted.
	 *
	 * @param size current size of the storeroom
	 */
	public void printDeleteTask(int size, Task task) {
		System.out.println("____________________________________________________________\n"
				+ "Noted. I've removed this task:\n  "
				+ task
				+ "\n"
				+ "Now you have "
				+ size
				+ " tasks in the list."
				+ "\n"
				+ "____________________________________________________________\n");
	}

	/**
	 * Find tasks by keyword in the task list.
	 *
	 * @param nextLine input String
	 */
	public void findTask(String nextLine) throws DukeException {
		ArrayList<Task> filteredArrayList = new ArrayList<>();
		String keyword = nextLine.substring(5);
		if (taskList.size() == 0) {
			throw new DukeException("OOP!!! List is empty right now.");
		}
		for (Task task : taskList) {
			if (task.description.contains(keyword)) {
				filteredArrayList.add(task);
			}
		}
		TaskList filteredTaskList = new TaskList(filteredArrayList);
		filteredTaskList.printFilteredList();
	}
}
