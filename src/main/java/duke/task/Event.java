package duke.task;

import java.util.*;

public class Event extends Task {
	private String at;

	public Event(String description, String at) {
		super(description);
		this.type = "Event";
		this.at = at;
	}

	public String getFormat() {
		return "event {description} /at {event period}";
	}

	public List<String> getSaveParameters() {
		List<String> params = super.getSaveParameters();
		params.add(at);
		return params;
	}

	public String toString() {
		return super.toString() + " (at: " + at + ")";
	}
}