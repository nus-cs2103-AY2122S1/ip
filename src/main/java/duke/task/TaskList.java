package duke.task;

import java.util.*;

public class TaskList {
	private List<Task> tasks;

	public TaskList(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task get(int index) {
		return this.tasks.get(index);
	}

	public void markDone(int index) {
		this.get(index).markDone();
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void add(Task task) {
		this.tasks.add(task);
	}

	public void delete(int index) {
		this.tasks.remove(index);
	}

	public int size() {
		return this.tasks.size();
	}

	public boolean isEmpty() {
		return this.tasks.isEmpty();
	}
}
