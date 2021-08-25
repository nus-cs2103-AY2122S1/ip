package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline-type Task is a task consisting of its details and deadline in day.
 */
public class Deadline extends Task {
	/**
	 * The deadline in day.
	 */
	protected LocalDate by;
	private static final DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

	/**
	 * Constructs a instance of Deadline that consist of its details and deadline in day.
	 *
	 * @param taskDetails Description of the task
	 * @param by day in dd/MM/yyyy
	 */
	public Deadline(String taskDetails, String by) {
		super(taskDetails);
		LocalDate byDate = LocalDate.parse(by, inputDateFormat);
		this.by = byDate;
	}

	/**
	 * Return the string representation of Deadline details with day and time, prefixed with [D]
	 *
	 * @return the string representation of Deadline details
	 */
	@Override
	public String toString() {
		String outputDate = this.by.format(outputDateFormat);
		return "[D]" + super.toString() + " (by: " + outputDate + ")";
	}

	/**
	 * Returns the string representation for storing in text file.
	 *
	 * @return the string representation for storing in text file
	 */
	@Override
	public String toStringSave() {
		int completeBinary = 0;
		if (this.isComplete) {
			completeBinary = 1;
		}
		return "D" + " | " + completeBinary + " | " + this.taskDetails + " | " +
				this.by.format(inputDateFormat);
	}

}