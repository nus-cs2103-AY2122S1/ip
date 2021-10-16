package duke.operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Deadline class for deadline tasks.
 */
public class Deadline extends Task {
	protected LocalDateTime by;

	/**
	 * Constructor for Deadline objects.
	 *
	 * @param description string input
	 * @param by          deadline date
	 */
	public Deadline(String description, LocalDateTime by, boolean isDone) {
		super(description, isDone);
		this.by = by;
		taskType = Command.DEADLINE;
	}


	@Override
	public String toString() {
		DateTimeFormatter dateTimeFormatterTo = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
		return "[D]"
		        + super.toString()
				+ " [by] "
				+ this.by.format(dateTimeFormatterTo);
	}
	@Override
	public int compareTo(Task otherTask) {
		if (otherTask.getTaskType().equals(Command.DEADLINE)) {
			Deadline otherDeadline = (Deadline) otherTask;
			return this.by.compareTo(otherDeadline.by);
		} else {
			return super.compareTo(otherTask);
		}
	}
}
