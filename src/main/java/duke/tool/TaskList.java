package duke.tool;

import duke.operation.Task;
import java.util.ArrayList;

/**
 * This is the TaskList class to contain tasks and handle list related operations.
 */
public class TaskList {
	protected ArrayList<Task> taskList;

	public TaskList() {
		this.taskList = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> list) {
		this.taskList = list;
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void addToTaskList(Task task) {
		taskList.add(task);
	}

	public int getSize() {
		return taskList.size();
	}

	public Task getTaskByIndex(int index) {
		return taskList.get(index);
	}

	public void removeTaskByIndex(int index){
		taskList.remove(index);
	}

	public void setTaskByIndex(int index, Task toSet){
		taskList.set(index, toSet);
	}
}
