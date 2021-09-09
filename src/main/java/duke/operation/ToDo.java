package duke.operation;

/**
 * This is the ToDo class for todo tasks.
 */
public class ToDo extends Task {
	public ToDo(String description) {
		super(description);
	}

	public static ToDo splitToDO(String input) {
		assert input.length() >= 5 : "OOPS!!! todo task input does not have enough length.";
		return new ToDo(input.substring(5));
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
