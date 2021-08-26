package duke.task;

/**
 * Represents a ToDo task to be completed with no time restriction.
 */
public class ToDo extends Task {
	/**
	 * Creates a new ToDo task with the given name.
	 */
	public ToDo(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
