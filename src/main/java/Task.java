import java.util.*;

public class Task {
	private String description;
	private boolean isDone;

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
		return String.format("[%s] %s", (isDone ? "X" : " "), description);
	}
}
