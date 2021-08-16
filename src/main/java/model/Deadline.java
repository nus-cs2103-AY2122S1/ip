package model;

/**
 * a task containing description and the deadline of the task
 */
public class Deadline extends Task {
	private final String deadline;
	
	/**
	 * public constructor for deadline
	 *
	 * @param desc     description of the deadline
	 * @param deadline string representing the deadline
	 */
	public Deadline(String desc, String deadline) {
		super(desc);
		this.deadline = deadline;
	}
	
	/**
	 * String representation of deadline, marked with [D], desc and the deadline
	 *
	 * @return string
	 */
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + deadline + ")";
	}
}
