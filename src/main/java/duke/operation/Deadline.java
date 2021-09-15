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
	public Deadline(String description, LocalDateTime by) {
		super(description);
		this.by = by;
		taskType = Command.DEADLINE;
	}

	/**
	 * Splits deadline input string into body and deadline date.
	 *
	 * @param input input string
	 * @return new split Deadline object
	 */
	public static Deadline splitDeadline(String input) {
		assert input.contains("/by") : "OOPS!!! Missing keyword '/by' for deadline input.";
		String[] partsOfDeadline = input.split("/by ");
		String deadlineContent = partsOfDeadline[0].substring(9);
		DateTimeFormatter dateTimeFormatterFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		assert partsOfDeadline.length == 2 : "OOPS!!! Missing deadline date.";
		LocalDateTime by = LocalDateTime.parse(partsOfDeadline[1], dateTimeFormatterFrom);
		return new Deadline(deadlineContent, by);
	}

	@Override
	public String toString() {
		DateTimeFormatter dateTimeFormatterTo = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
		return "[D]"
		        + super.toString()
				+ " (by: "
				+ this.by.format(dateTimeFormatterTo)
				+ ")";
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
