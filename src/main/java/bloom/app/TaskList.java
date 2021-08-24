package bloom.app;

import bloom.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
	
	private static List<Task> tasks;
	
	public TaskList() {
		TaskList.tasks = new ArrayList<>();
	}
	
	public static int size() {
		return TaskList.tasks.size();
	}
	
	public static Task get(int index) {
		return TaskList.tasks.get(index);
	}
	
	public static void add(Task task) {
		TaskList.tasks.add(task);
	}
	
	public static Task delete(int index) {
		return TaskList.tasks.remove(index);
	}
}
