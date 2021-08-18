public class Task {
	private String name;
	private boolean done = false;

	public Task(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isDone() {
		return done;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getCheckBox() {
        return (done ? "[X] " : "[ ] "); // mark done task with X
    }

	@Override
	public String toString() {
		return getCheckBox() + this.name;
	}
}