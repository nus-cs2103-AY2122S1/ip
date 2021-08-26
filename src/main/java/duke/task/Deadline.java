package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline to be completed by.
 */
public class Deadline extends Task {
	private final LocalDate deadline;

	public Deadline(String name, LocalDate deadline) {
		super(name);
		this.deadline = deadline;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public String getFormattedDeadline() {
		return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")); // -> Oct 15 2019
	}

	public String toSaveString() {
		return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.getFormattedDeadline() + ")";
	}
}
