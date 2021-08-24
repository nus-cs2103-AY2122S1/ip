package bloom.task;

import bloom.app.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
	private final LocalDateTime by;

	public Deadline(String description, String by) {
		super(description);
		this.by = new Parser().parseDate(by);
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.by + ")";
	}

	@Override
	public String toDb() {
		return "D | " + super.toDb() + " | " + this.by;
	}
}
