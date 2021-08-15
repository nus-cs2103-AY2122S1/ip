package me.yukun99.ip.tasks;

import me.yukun99.ip.HelpBot;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

import java.util.ArrayList;
import java.util.List;

public abstract class Task {
	// Task type list
	public enum TaskType {
		TODO,
		DEADLINE,
		EVENT,
	}

	// Constants
	private static final String PREFIX_NOT_DONE = "[ ] ";
	private static final String PREFIX_DONE = "[X] ";

	// Constants/Variables related to the instance
	private final String name;
	private boolean done = false;

	// Map of all task names to corresponding tasks
	public static final List<Task> tasks = new ArrayList<>();

	/**
	 * Constructor for a Task instance.
	 *
	 * @param name Name of the task to be created.
	 */
	public Task(String name) {
		this.name = name;
	}

	/**
	 * Marks a task as done.
	 */
	public void setDone() {
		if (done) {
			HelpBot.reply("You've already done this task, stop bothering me!");
			return;
		}
		done = true;
		String reply = "About time you did your work, you lazy bum! I GUESS I'll mark it as done for you:\n  "
				+ this;
		HelpBot.reply(reply);
	}

	/**
	 * Tries to update a task from a message sent by the user.
	 *
	 * @param message Message sent by the user.
	 * @throws HelpBotInvalidTaskException     If task index given is invalid.
	 * @throws HelpBotInvalidTaskTypeException If task does not contain a date to be updated.
	 */
	public static void updateTask(String message) throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException {
		String[] messageSplit = message.split(" /to ");
		int taskIndex;
		try {
			taskIndex = Integer.parseInt(messageSplit[0]) - 1;
		} catch (NumberFormatException e) {
			throw new HelpBotInvalidTaskException(e, "update", messageSplit[0]);
		}
		Task task;
		try {
			task = tasks.get(taskIndex);
		} catch (IndexOutOfBoundsException e) {
			throw new HelpBotInvalidTaskException(e, "update", taskIndex + "");
		}
		task.updateDate(messageSplit[1]);
		String reply = "Dude, make up your mind! I'll update it, but just this once, okay?\n"
				+ task;
		HelpBot.reply(reply);
	}

	/**
	 * Updates the date of an event or deadline task.
	 *
	 * @param date New date to be updated.
	 * @throws HelpBotInvalidTaskTypeException If task does not contain a date to be updated.
	 */
	protected abstract void updateDate(String date) throws HelpBotInvalidTaskTypeException;

	/**
	 * Deletes a task from the task list.
	 *
	 * @param index Index of the task to be deleted.
	 * @throws HelpBotInvalidTaskException If task index given is invalid.
	 */
	public static void deleteTask(String index) throws HelpBotInvalidTaskException {
		int taskIndex;
		try {
			taskIndex = Integer.parseInt(index) - 1;
		} catch (NumberFormatException e) {
			throw new HelpBotInvalidTaskException(e, "delete", index);
		}
		Task task;
		try {
			task = tasks.get(taskIndex);
		} catch (IndexOutOfBoundsException e) {
			throw new HelpBotInvalidTaskException(e, "delete", taskIndex + "");
		}
		tasks.remove(taskIndex);
		String reply = "";
		if (task.done) {
			reply += "Can't you just keep track of this yourself? Fine, removed this for you:\n";
		} else {
			reply += "Oh, procrastinating now are we? Sure, removed this:\n";
		}
		reply += task
				+ "\n" + Task.getTaskAmount();
		HelpBot.reply(reply);
	}

	/**
	 * Gets the message to reply for the current number of tasks.
	 *
	 * @return Message to reply for the current number of tasks.
	 */
	public static String getTaskAmount() {
		return "You now have " + Task.tasks.size() + " tasks to do.";
	}

	/**
	 * Checks whether another Task object is equal to the current instance.
	 *
	 * @param o Task object to check against.
	 * @return Whether the other Object is equal to the current instance.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Task)) {
			return false;
		}
		Task task = (Task) o;
		return this.name.equals(task.name);
	}

	/**
	 * Returns a String representation of the Task instance.
	 *
	 * @return String representation of the Task instance.
	 */
	@Override
	public String toString() {
		if (!done) {
			return PREFIX_NOT_DONE + this.name;
		}
		return PREFIX_DONE + this.name;
	}
}
