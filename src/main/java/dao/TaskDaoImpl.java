package dao;

import configuration.Setting;
import model.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation of the TaskDao using the temporary storage of ArrayList for database
 */
public class TaskDaoImpl implements TaskDao {
	private final Logger logger = Logger.getLogger(TaskDaoImpl.class.getName());
	
	public TaskDaoImpl() {
		try {
			// a local file is used to persistently store all the tasks,
			// and it should only have 1 object, which is the list of task
			File file = new File(Setting.FILE_TASK_PATH);
			boolean isCreated = file.createNewFile();
			if (isCreated) {
				writeTaskToFile(new ArrayList<>());
				logger.info("Task file is successfully created at " + file.getAbsolutePath());
			} else {
				logger.info("Task file already exists at " + file.getAbsolutePath());
			}
		} catch (IOException e) {
			logger.severe("Task file cannot be created " + e.getMessage());
		}
	}
	
	/**
	 * Writes the serialized ArrayList of Task into a file.
	 * It will log IOException error accordingly.
	 */
	private void writeTaskToFile(ArrayList<Task> tasks) {
		try (FileOutputStream file = new FileOutputStream(Setting.FILE_TASK_PATH);
		     ObjectOutputStream objectOutputStream = new ObjectOutputStream(file)
		) {
			objectOutputStream.writeObject(tasks);
			logger.fine("Writing Task list to file : " + Setting.FILE_TASK_PATH);
		} catch (IOException e) {
			logger.severe("Writing tasks to file " + Setting.FILE_TASK_PATH + " failed : " + e.getMessage());
		}
	}
	
	@Override
	public void addTask(Task task) {
		synchronized (this) {
			try {
				ArrayList<Task> tasks = readTaskFromFile();
				tasks.add(task);
				writeTaskToFile(tasks);
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
				ArrayList<Task> tasks = readTaskFromFile();
				Task removedTask = tasks.remove(index);
				writeTaskToFile(tasks);
				
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
				ArrayList<Task> tasks = readTaskFromFile();
				Task task = tasks.get(index);
				task.markDone();
				writeTaskToFile(tasks);
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
			ArrayList<Task> tasks = readTaskFromFile();
			return tasks.get(index);
		} catch (Exception e) {
			logger.warning(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Task> getAll() {
		try {
			return readTaskFromFile();
		} catch (Exception e) {
			logger.warning(e.getMessage());
			return List.of();
		}
	}
	
	@Override
	public int getSize() {
		try {
			ArrayList<Task> tasks = readTaskFromFile();
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
	
	/**
	 * Reads the serialized ArrayList of Task from a file and return it.
	 * It will log IOException and ClassNotFound error accordingly.
	 */
	private ArrayList<Task> readTaskFromFile() {
		try (FileInputStream file = new FileInputStream(Setting.FILE_TASK_PATH);
		     ObjectInputStream objectInputStream = new ObjectInputStream(file)
		) {
			@SuppressWarnings("unchecked")
			// Casting in mandatory here since we are reading objects
			ArrayList<Task> tasks = (ArrayList<Task>) objectInputStream.readObject();
			logger.fine("Reading Task List from file : " + Setting.FILE_TASK_PATH);
			
			return tasks;
		} catch (IOException e) {
			logger.severe("Reading Task List Failed : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.severe("Object cannot be casted to array of task : " + e.getMessage());
		}
		
		// Since it cannot be read, we rewrite the file with a new file.
		return new ArrayList<>();
	}
}
