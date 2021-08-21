import java.util.*;
import java.time.*;
import java.time.format.*;

public class Deadline extends Task {
	private String by;
	private LocalDate date;

	public static Deadline init(String line) {
		int pos = line.indexOf("/by");
		if (pos == -1 || line.length() < pos + 5) {
			throw new TaskException(new Deadline("?", "?"));
		}
		return new Deadline(line.substring(9, pos-1), line.substring(pos+4));
	}

	public Deadline(String description, String by) {
		super(description);
		this.type = "Deadline";

		try {
			this.date = LocalDate.parse(by);
			this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
		} catch (DateTimeParseException ex) {
			this.date = null;
			this.by = by;
		}
	}

	public String getFormat() {
		return "deadline {description} /by {by when?}";
	}

	public List<String> getSaveParameters() {
		List<String> params = super.getSaveParameters();
		params.add(by);
		return params;
	}

	public String toString() {
		return super.toString() + " (by: " + by + ")";
	}
}