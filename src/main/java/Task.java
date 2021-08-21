import java.util.*;

abstract class Task {
	protected String description;
	protected boolean isDone;
	protected String type;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	public String getType() {
		return this.type;
	}

	public String getDescription() {
		return this.description;
	}

	public void markDone() {
		this.isDone = true;
	}

	abstract String getFormat();

	public List<String> getSaveParameters() {
		List<String> params = new ArrayList<>();
		params.add(type.substring(0, 1));
		params.add(isDone ? "1" : "0");
		params.add(description);
		return params;
	}

	public String toString() {
		return String.format("[%c][%s] %s", type.charAt(0), (isDone ? "X" : " "), description);
	}
}
