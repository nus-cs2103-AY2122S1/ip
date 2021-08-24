package bloom.task;

import bloom.app.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
	private final LocalDateTime at;

	public Event(String description, String at) {
		super(description);
		this.at = new Parser().parseDate(at);
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
