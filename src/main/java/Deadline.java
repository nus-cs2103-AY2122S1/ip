/**
 * class for deadlines, a type of task, with an added deadline field
 *
 */
public class Deadline extends Task {

	private String deadline;

	Deadline(String title, String deadline) {
		super(title);
		this.deadline = deadline;
	}

	Deadline(String title, Boolean done, String deadline) {
		super(title, done);
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		if (this.done) {
			return "[D][X] " + this.title + "(by: " + this.deadline + ")";
		} else {
			return "[D][ ] " + this.title + "(by: " + this.deadline + ")";
		}
	}

	@Override
	public String saveString() {
		if (this.done) {
			return "D : 1 : " + this.title + " : " + this.deadline;
		} else {
			return "D : 0 : " + this.title + " : " + this.deadline;
		}
	}
}
