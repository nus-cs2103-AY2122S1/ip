package duke.operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Event class for event tasks.
 */
public class Event extends Task {
	protected LocalDateTime at;

	/**
	 * Constructor for Event objects.
	 *
	 * @param description input string
	 * @param at          event date
	 */
	public Event(String description, LocalDateTime at) {
		super(description);
		this.at = at;
	}

	/**
	 * Splits event string into body and event date.
	 *
	 * @param input input string
	 * @return new split event object
	 */
	public static Event splitEvent(String input) {
		String[] partsOfEvent = input.split("/at ");
		String eventContent = partsOfEvent[0].substring(6);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime at = LocalDateTime.parse(partsOfEvent[1], dateTimeFormatter);
		return new Event(eventContent, at);
	}

	@Override
	public String toString() {
		DateTimeFormatter dateTimeFormatterTo = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
		return "[E]"
				+ super.toString()
				+ " (at: "
				+ this.at.format(dateTimeFormatterTo)
				+ ")";
	}
}
