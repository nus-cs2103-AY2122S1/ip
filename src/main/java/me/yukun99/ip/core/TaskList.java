package me.yukun99.ip.core;

import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private final List<Task> taskList;

	public TaskList() {
		this.taskList = new ArrayList<>();
	}

	public void addTask(Task task) {
		this.taskList.add(task);
	}

	public void doneTask(String strIndex) throws HelpBotInvalidTaskException {
		try {
			int index = Integer.parseInt(strIndex);
			Task task = this.taskList.get(index - 1);
			task.setDone();
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			throw new HelpBotInvalidTaskException(e, "done", strIndex + "");
		}
	}

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

	public int getRemaining() {
		return this.taskList.size();
	}

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
