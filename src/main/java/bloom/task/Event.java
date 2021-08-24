package bloom.task;

import java.time.LocalDateTime;

public class Event extends Task {
	protected final LocalDateTime at;

	public Event(String description, LocalDateTime at) {
		super(description);
		this.at = at;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + this.at + ")";
	}
	
	@Override
	public String toDb() {
		return "E | " + super.toDb() + " | " + this.at;
	}
}
