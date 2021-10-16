package duke.controller;

import duke.exception.DukeException;
import duke.operation.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class LogicController {
	private Parser parser = new Parser();
	private TaskList taskList;
	private Ui ui = new Ui();
	private Storage storage;

	/**
	 * Adds tasks to the storeroom.
	 *
	 * @param command   command name
	 * @param inputLine input string
	 * @throws DukeException
	 */
	public String addTask(Command command, String inputLine, TaskList tl, Storage st) throws DukeException {
		Task task;
		String guiOutput;
		taskList = tl;
		storage = st;
		switch (command) {
		case TODO: {
			parser.firstWordAndDescriptionCheck(inputLine, "todo");
			task = parser.splitToDO(inputLine);
			taskList.addToTaskList(task);
			storage.updateFile(taskList);
			guiOutput = ui.printAddTask(taskList.getSize(), task);
			break;
		}
		case DEADLINE: {
			parser.firstWordAndDescriptionCheck(inputLine, "deadline");
			task = parser.splitDeadline(inputLine);
			taskList.addToTaskList(task);
			storage.updateFile(taskList);
			guiOutput = ui.printAddTask(taskList.getSize(), task);
			break;
		}
		case EVENT: {
			parser.firstWordAndDescriptionCheck(inputLine, "event");
			task = parser.splitEvent(inputLine);
			taskList.addToTaskList(task);
			storage.updateFile(taskList);
			guiOutput = ui.printAddTask(taskList.getSize(), task);
			break;
		}
		default: {
			throw new DukeException(parser.getNotUnderstoodMessage());
		}
		}
		return guiOutput;
	}

	/**
	 * Deletes a task from the storeroom.
	 *
	 * @param nextLine input string
	 */
	public String deleteTask(String nextLine, TaskList tl, Storage st) {
		taskList = tl;
		storage = st;
		int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
		Task taskToDelete = taskList.getTaskByIndex(intValue - 1);
		taskList.removeTaskByIndex(intValue - 1);
		storage.updateFile(taskList);
		return ui.printDeleteTask(taskList.getSize(), taskToDelete);
	}

	/**
	 * Marks a task as finished.
	 *
	 * @param nextLine input string
	 */
	public String finishTask(String nextLine, TaskList tl, Storage st) {
		taskList = tl;
		storage = st;
		int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
		Task doneTask = taskList.getTaskByIndex(intValue - 1);
		doneTask.doneTask();
		taskList.setTaskByIndex(intValue - 1, doneTask);
		storage.updateFile(taskList);
		return ui.printDoneTask(doneTask);
	}

	/**
	 * Finds tasks by keyword in the task list and return a filtered taskList.
	 *
	 * @param nextLine input String
	 */
	public String findTask(String nextLine, TaskList tl) throws DukeException {
		taskList = tl;
		ArrayList<Task> filteredArrayList = new ArrayList<>();
		String keyword = nextLine.substring(5);
		if (taskList.getSize() == 0) {
			throw new DukeException("OOP!!! List is empty right now.");
		}
		addFoundTask(keyword, filteredArrayList);
		TaskList filteredTl = new TaskList(filteredArrayList);
		return parser.parseFilteredTaskListAsString(filteredTl);
	}

	public ArrayList<Task> addFoundTask(String keyword, ArrayList<Task> filteredArrayList) {
		for (Task task : taskList.getTaskList()) {
			if (task.getDescription().contains(keyword)) {
				filteredArrayList.add(task);
			}
		}
		return filteredArrayList;
	}

	public void listTask() {
		ui.printList();
	}

	public String getList(TaskList tl) {
		return parser.parseTaskListAsString(tl);
	}
}
