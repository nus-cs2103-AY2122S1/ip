package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline to be completed by.
 */
public class Deadline extends Task {
	private final LocalDate deadline;

	/**
	 * Constructs a new deadline task.
	 */
	public Deadline(String name, LocalDate deadline) {
		super(name);
		this.deadline = deadline;
	}

	/**
	 * Gets the deadline of the task.
	 *
	 * @return the deadline date
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * Gets the formatted deadline in MMM d yyyy format.
	 *
	 * @return the formatted deadline String
	 */
	public String getFormattedDeadline() {
		return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")); // -> Oct 15 2019
	}

	/**
	 * Formats string when saving to file.
	 *
	 * @return String representation of task when saving to a file
	 */
	public String toSaveString() {
		return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.getFormattedDeadline() + ")";
	}
}
