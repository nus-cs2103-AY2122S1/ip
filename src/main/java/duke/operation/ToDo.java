package duke.operation;

/**
 * This is the ToDo class for todo tasks.
 */
public class ToDo extends Task {
	public ToDo(String description, boolean isDone) {
		super(description, isDone);
		taskType = Command.TODO;
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
