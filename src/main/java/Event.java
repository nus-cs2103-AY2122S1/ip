/**
 * class to represent events, a type of task with a eventDate field
 *
 */
public class Event extends Task {

	private String eventDate;

	Event(String title, String eventDate) {
		super(title);
		this.eventDate = eventDate;
	}

	Event(String title, Boolean done, String eventDate) {
		super(title, done);
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		if (this.done) {
			return "[E][ ] " + this.title + "(at: " + this.eventDate + ")";
		} else {
			return "[E][X] " + this.title + "(at: " + this.eventDate + ")";
		}
	}

	@Override
	public String saveString() {
		if (this.done) {
			return "E : 1 : " + this.title + " : " + this.eventDate;
		} else {
			return "E : 1 : " + this.title + " : " + this.eventDate;
		}
	}
}
