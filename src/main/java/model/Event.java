package model;

/**
 * a task containing description and the time of the event
 */
public class Event extends Task {
	private final String time;
	
	/**
	 * public constructor of event
	 *
	 * @param desc string representing the description
	 * @param time string representing the time of the event
	 */
	public Event(String desc, String time) {
		super(desc);
		this.time = time;
	}
	
	/**
	 * String representation of Event, marked with [E], desc and the timing
	 *
	 * @return string
	 */
	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at : " + time + ")";
	}
}
