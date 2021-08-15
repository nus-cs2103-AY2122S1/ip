import java.util.*;

abstract class Task {
	protected String description;
	protected boolean isDone;
	protected String type;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	public String getDescription() {
		return this.description;
	}

	public void markDone() {
		this.isDone = true;
	}

	public String toString() {
		return String.format("[%s][%s] %s", type, (isDone ? "X" : " "), description);
	}
}
