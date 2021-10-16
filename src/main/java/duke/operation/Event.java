package duke.operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Event class for event tasks.
 */
public class Event extends Task{
	protected LocalDateTime at;

	/**
	 * Constructor for Event objects.
	 *
	 * @param description input string
	 * @param at          event date
	 */
	public Event(String description, LocalDateTime at, boolean isDone) {
		super(description, isDone);
		this.at = at;
		taskType = Command.EVENT;
	}


	@Override
	public String toString() {
		DateTimeFormatter dateTimeFormatterTo = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
		return "[E]"
				+ super.toString()
				+ " [at] "
				+ this.at.format(dateTimeFormatterTo);
	}

	@Override
	public int compareTo(Task otherTask) {
		if (otherTask.getTaskType().equals(Command.EVENT)) {
			Event otherEvent = (Event) otherTask;
			return this.at.compareTo(otherEvent.at);
		} else {
			return super.compareTo(otherTask);
		}
	}
}
