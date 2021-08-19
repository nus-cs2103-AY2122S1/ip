package dao;

import model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TaskDao using the temporary storage of ArrayList for database
 */
public class TaskDaoImpl implements TaskDao {
	
	// ArrayList is working as a database for now
	private final ArrayList<Task> taskList = new ArrayList<>();
	
	@Override
	public Task add(Task task) {
		taskList.add(task);
		
		return task;
	}
	
	@Override
	public Task delete(int index) {
		if (isInvalidIndex(index)) {
			throw new IllegalArgumentException("non valid index for deletion");
		}
		
		return taskList.remove(index);
	}
	
	@Override
	public Task markDone(int index) {
		if (isInvalidIndex(index)) {
			throw new IllegalArgumentException("non valid index for marking the task done");
		}
		
		Task task = taskList.get(index);
		task.checkDone();
		
		return task;
	}
	
	@Override
	public Task get(int index) {
		if (isInvalidIndex(index)) {
			throw new IllegalArgumentException("non valid index for marking the task done");
		}
		
		return taskList.get(index);
	}
	
	@Override
	public List<Task> getAll() {
		return taskList;
	}
	
	@Override
	public int size() {
		return taskList.size();
	}
	
	private boolean isInvalidIndex(int index) {
		return index >= taskList.size() || index < 0;
	}
}
