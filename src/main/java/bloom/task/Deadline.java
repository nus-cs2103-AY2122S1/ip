package bloom.task;

public class Deadline extends Task {
	private final String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}

	@Override
	public String toDb() {
		return "D | " + super.toDb() + " | " + this.by;
	}
}
