package operation;

import java.time.DateTimeException;
import java.time.LocalDate;
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
	}

	/**
	 * Splits deadline input string into body and deadline date.
	 *
	 * @param input input string
	 * @return new split Deadline object
	 */
	public static Deadline splitDeadline(String input) {
		String[] partsOfDeadline = input.split("/by ");
		String deadlineContent = partsOfDeadline[0].substring(9);
		DateTimeFormatter dateTimeFormatterFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
}
