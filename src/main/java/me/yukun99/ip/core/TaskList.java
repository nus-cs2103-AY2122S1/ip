package me.yukun99.ip.core;

import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle list of all tasks currently in the todo list.
 */
public class TaskList {
	// List of all tasks currently in the todo list.
	private final List<Task> taskList;

	/**
	 * Constructor for a TaskList instance.
	 */
	public TaskList() {
		this.taskList = new ArrayList<>();
	}

	/**
	 * Adds a task to the TaskList.
	 *
	 * @param task Task to be added to the TaskList.
	 */
	public void addTask(Task task) {
		this.taskList.add(task);
	}

	/**
	 * Marks task at specified index as done.
	 *
	 * @param strIndex String representing the index of the task in the TaskList.
	 * @throws HelpBotInvalidTaskException If strIndex is not an Integer.
	 */
	public void doneTask(String strIndex, Ui ui) throws HelpBotInvalidTaskException {
		try {
			int index = Integer.parseInt(strIndex);
			Task task = this.taskList.get(index - 1);
			task.setDone(ui);
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			throw new HelpBotInvalidTaskException(e, "done", strIndex + "");
		}
	}

	/**
	 * Updates the date of the task at specified index.
	 *
	 * @param strIndex String representing the index of the task in the TaskList.
	 * @param date New date to be updated for the task.
	 * @return Updated task.
	 * @throws HelpBotInvalidTaskException If specified Task index does not exist.
	 * @throws HelpBotInvalidTaskTypeException If specified Task is an instance of ToDo.
	 */
	public Task updateTask(String strIndex, String date)
			throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException {
		try {
			int index = Integer.parseInt(strIndex);
			Task task = this.taskList.get(index - 1);
			task.updateDate(date);
			return task;
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			throw new HelpBotInvalidTaskException(e, "update", strIndex + "");
		}
	}

	/**
	 * Deletes a task from the TaskList.
	 *
	 * @param strIndex String representing the index of the task to be deleted.
	 * @return Deleted task.
	 * @throws HelpBotInvalidTaskException If specified Task index does not exist.
	 */
	public Task deleteTask(String strIndex) throws HelpBotInvalidTaskException {
		try {
			int index = Integer.parseInt(strIndex);
			Task deleted = this.taskList.get(index);
			this.taskList.remove(index);
			return deleted;
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			throw new HelpBotInvalidTaskException(e, "delete", strIndex + "");
		}
	}

	/**
	 * Gets the number of tasks in the TaskList.
	 *
	 * @return Number of tasks in the TaskList.
	 */
	public int getRemaining() {
		return this.taskList.size();
	}

	/**
	 * Gets the string representation of the TaskList.
	 *
	 * @return String representation of the TaskList.
	 */
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder("Oh. My. God. Fine. Here are your tasks:");
		if (this.taskList.size() == 0) {
			message
					.append("\n  Oh I'm sorry, were you expecting ME to make you a todo list, you lazy sod?")
					.append("\n  Do it yourself, idiot.");
		}
		for (int i = 0; i < this.taskList.size(); ++i) {
			message.append("\n ").append(i + 1).append(".").append(this.taskList.get(i));
		}
		return message.toString();
	}
}
