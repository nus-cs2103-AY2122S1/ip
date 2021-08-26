package data;

import exception.DukeException;
import operation.Event;
import operation.Task;
import operation.Deadline;
import operation.ToDo;
import operation.Command;


import java.util.ArrayList;

public class StoreRoom {
	private ArrayList<Task> taskList;

	public StoreRoom() {
		this.taskList = new ArrayList<>();
	}

	public void addTask(Command command, String inputLine) throws DukeException{
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
			task.addTask(taskList.size());
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
			task.addTask(taskList.size());
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
			task.addTask(taskList.size());
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

	public void finishTask(String nextLine) {
		int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
		Task doneTask = this.taskList.get(intValue - 1);
		doneTask.doneTask();
		this.taskList.set(intValue - 1, doneTask);
		doneTask.printDoneTask();
	}

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

	public void deleteTask(String nextLine) {
		int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
		Task taskToDelete = taskList.get(intValue - 1);
		taskList.remove(intValue - 1);
		taskToDelete.printDeleteTask(taskList.size());
	}

	public void invalidTask() throws DukeException {
		String message = "____________________________________________________________\n"
				+ "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
				+ "____________________________________________________________\n";
		throw new DukeException(message);

	}
}
