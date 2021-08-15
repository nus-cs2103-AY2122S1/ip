package me.yukun99.ip.tasks;

public class Event extends Task {
	private String date;

	/**
	 * Constructor for an Event instance.
	 *
	 * @param name Name of the Event.
	 * @param date Date the event occurs at.
	 */
	public Event(String name, String date) {
		super(name);
		this.date = date;
	}

	protected void updateDate(String date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Event)) {
			return false;
		}
		Event event = (Event) o;
		return super.equals(o) && this.date.equals(event.date);
	}

	@Override
	public String toString() {
		return " [E]" + super.toString() + " (at: " + date + ")";
	}
}
