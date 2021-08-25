package duke.task;


/**
 * A toDo-type of Task that contains details.
 */
public class ToDo extends Task {

	/**
	 * Constructs a uncompleted task with the details as taskDetails
	 *
	 * @param taskDetails task name
	 */
	public ToDo(String taskDetails) {
		super(taskDetails);
	}

	/**
	 * Returns the task details with the prefix first with [T] followed by the status of completion of the task.
	 *
	 * @return the task details with the prefix first with [T] followed by the status of completion of the task.
	 */
	public String toString() {
		return "[T]" + super.toString();
	}

	/**
	 * Returns the string representation for storing in text file.
	 *
	 * @return the string representation for storing in text file
	 */
	@Override
	public String toStringSave() {
		int completeBinary = 0;
		if (this.isComplete) {
			completeBinary = 1;
		}
		return "T" + " | " + completeBinary + " | " + this.taskDetails + " | ";
	}

}
