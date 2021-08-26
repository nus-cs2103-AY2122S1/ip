package data;

import exception.DukeException;
import operation.Event;
import operation.Task;
import operation.Deadline;
import operation.ToDo;
import operation.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This is the StoreRoom class to store tasks and handle actions.
 */
public class StoreRoom {
	private ArrayList<Task> taskList;
	private Path dir = Paths.get(System.getProperty("user.dir") + "/src/main/java/data");

	public StoreRoom() {
		this.taskList = new ArrayList<>();
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
			Task.checkIfFirstWordValid(inputLine, "todo");
			if (Task.isDescriptionEmpty(inputLine)) {
				message = "____________________________________________________________\n"
						+ "OOPS!!! The description of a "
						+ "todo"
						+ " cannot be empty.\n"
						+ "____________________________________________________________\n";
				throw new DukeException(message);
			}
			task = new ToDo(inputLine.substring(5));
			taskList.add(task);
			task.printAddTask(taskList.size());
			break;
		}
		case DEADLINE: {
			Task.checkIfFirstWordValid(inputLine, "deadline");
			if (Task.isDescriptionEmpty(inputLine)) {
				message = "____________________________________________________________\n"
						+ "OOPS!!! The description of a "
						+ "deadline"
						+ " cannot be empty.\n"
						+ "____________________________________________________________\n";
				throw new DukeException(message);
			}
			task = Deadline.splitDeadline(inputLine);
			taskList.add(task);
			task.printAddTask(taskList.size());
			break;
		}
		case EVENT: {
			Task.checkIfFirstWordValid(inputLine, "event");
			if (Task.isDescriptionEmpty(inputLine)) {
				message = "____________________________________________________________\n"
						+ "OOPS!!! The description of a "
						+ "event"
						+ " cannot be empty.\n"
						+ "____________________________________________________________\n";
				throw new DukeException(message);
			}
			task = Event.splitEvent(inputLine);
			taskList.add(task);
			task.printAddTask(taskList.size());
			break;
		}
		default: {
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
		doneTask.printDoneTask();
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
	 * Deletes a task from the storeroom.
	 *
	 * @param nextLine input string
	 */
	public void deleteTask(String nextLine) {
		int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
		Task taskToDelete = taskList.get(intValue - 1);
		taskList.remove(intValue - 1);
		taskToDelete.printDeleteTask(taskList.size());
	}

	/**
	 * Handles situation when input is invalid.
	 *
	 * @throws DukeException
	 */
	public void invalidTask() throws DukeException {
		String message = "____________________________________________________________\n"
				+ "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
				+ "____________________________________________________________\n";
		throw new DukeException(message);
	}

	/**
	 * Creates duke.txt to contain task list, if not exists.
	 */
	public void createFile() {
		if (!Files.exists(dir)) {
			try {
				Files.createDirectories(dir);
			} catch (IOException e) {
				System.out.println("Unexpected IO error occurred.");
			}
		}
		try {
			String path = dir.toString() + "/duke.txt";
			File file = new File(path);
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Unexpected IO error occurred.");
		}
	}

	/**
	 * Updates task list inside duke.txt.
	 */
	public void updateFile() {
		try {
			String path = dir.toString() + "/duke.txt";
			FileWriter fileWriter = new FileWriter(path);
			for (Task task : taskList) {
				fileWriter.write(task.toString());
				fileWriter.write("\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Unexpected IO error occurred");
		}
	}
}
