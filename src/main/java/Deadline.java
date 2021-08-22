import java.util.*;
import java.time.*;
import java.time.format.*;

public class Deadline extends Task {
	private String by;
	private LocalDate date;

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