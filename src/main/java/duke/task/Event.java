package duke.task;

/**
 * Represents an event task with a time and a name.
 */
public class Event extends Task {
	private String dateTime;

	/**
	 * Constructs an event task with a name and datetime.
	 */
	public Event (String name, String dateTime) {
		super(name);
		this.dateTime = dateTime;
	}

	/**
	 * Gets the datetime.
	 *
	 * @return the datetime string
	 */
	public String getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + this.getDateTime() + ")";
	}
}
