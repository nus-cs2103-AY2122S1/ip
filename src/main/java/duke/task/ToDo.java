package duke.task;

/**
 * Represents a ToDo task to be completed with no time restriction.
 */
public class ToDo extends Task {
	public ToDo(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
