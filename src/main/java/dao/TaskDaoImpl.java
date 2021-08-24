package dao;

import configuration.Setting;
import model.Task;
import storage.FileListStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation of the TaskDao using the temporary storage of ArrayList for database
 */
public class TaskDaoImpl implements TaskDao {
	private final Logger logger = Logger.getLogger(TaskDaoImpl.class.getName());
	
	/** File containing the ArrayList of Task */
	private final FileListStorage<Task> taskFileListStorage = new FileListStorage<>(Setting.FILE_TASK_PATH);
	
	@Override
	public void addTask(Task task) {
		synchronized (this) {
			try {
				ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
				tasks.add(task);
				taskFileListStorage.writeArrayListToFile(tasks);
			} catch (Exception e) {
				logger.warning(e.getMessage());
			}
		}
	}
	
	@Override
	public Task deleteTask(int index) {
		if (isInvalidIndex(index)) {
			throw new IndexOutOfBoundsException("non valid index for deletion");
		}
		
		synchronized (this) {
			try {
				ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
				Task removedTask = tasks.remove(index);
				taskFileListStorage.writeArrayListToFile(tasks);
				
				return removedTask;
			} catch (Exception e) {
				logger.warning(e.getMessage());
			}
		}
		
		return null;
	}
	
	@Override
	public void markDone(int index) {
		if (isInvalidIndex(index)) {
			throw new IndexOutOfBoundsException("non valid index for marking the task done");
		}
		
		synchronized (this) {
			try {
				ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
				Task task = tasks.get(index);
				task.markDone();
				taskFileListStorage.writeArrayListToFile(tasks);
			} catch (Exception e) {
				logger.warning(e.getMessage());
			}
		}
	}
	
	@Override
	public Task getTask(int index) {
		if (isInvalidIndex(index)) {
			throw new IndexOutOfBoundsException("non valid index for marking the task done");
		}
		
		try {
			ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
			return tasks.get(index);
		} catch (Exception e) {
			logger.warning(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Task> getAll() {
		try {
			return taskFileListStorage.readArrayListFromFile();
		} catch (Exception e) {
			logger.warning(e.getMessage());
			return List.of();
		}
	}
	
	@Override
	public int getSize() {
		try {
			ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
			return tasks.size();
		} catch (Exception e) {
			logger.warning(e.getMessage());
			return -1;
		}
	}
	
	private boolean isInvalidIndex(int index) {
		int size = getSize();
		return size != -1 && (index >= getSize() || index < 0);
	}
}
