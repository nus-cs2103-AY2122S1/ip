import java.util.*;

public class Event extends Task {
	private String at;

	public static Event init(String line) {
		int pos = line.indexOf("/at");
		if (pos == -1 || line.length() < pos + 5) {
			throw new TaskException(new Event("?", "?"));
		}
		return new Event(line.substring(6, pos), line.substring(pos+4));
	}

	public Event(String description, String at) {
		super(description);
		this.type = "Event";
		this.at = at;
	}

	public String getFormat() {
		return "event {description} /at {event period}";
	}

	public String toString() {
		return super.toString() + "(at: " + at + ")";
	}
}