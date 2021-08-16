import java.util.*;

public class Deadline extends Task {
	private String by;

	public static Deadline init(String line) {
		int pos = line.indexOf("/by");
		if (pos == -1 || line.length() < pos + 5) {
			throw new TaskException(new Deadline("?", "?"));
		}
		return new Deadline(line.substring(9, pos), line.substring(pos+4));
	}

	public Deadline(String description, String by) {
		super(description);
		this.type = "Deadline";
		this.by = by;
	}

	public String getFormat() {
		return "deadline {description} /by {by when?}";
	}

	public String toString() {
		return super.toString() + "(by: " + by + ")";
	}
}