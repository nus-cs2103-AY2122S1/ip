package dao;

import model.Task;

import java.util.List;

/**
 * Data access object (dao) for the Task model
 */
public interface TaskDao {
	/**
	 * add a task to the data layer, throw error instead of Optional if add fails
	 *
	 * @param task task to be added
	 * @return task that is added
	 */
	Task add(Task task);
	
	/**
	 * delete the task using 0-indexing
	 *
	 * @param index as it appears in the list - 1 (from 1 indexing to 0 indexing)
	 * @return deleted task
	 */
	Task delete(int index);
	
	/**
	 * mark a task as done
	 *
	 * @param index as it appears in the list - 1 (from 1 indexing to 0 indexing)
	 * @return marked task
	 */
	Task markDone(int index);
	
	/**
	 * @param index as it appears in the list - 1 (from 1 indexing to 0 indexing)
	 * @return the single task
	 */
	Task get(int index);
	
	/**
	 * @return list of all of done and not done tasks
	 */
	List<Task> getAll();
	
	/**
	 * @return the size of the task
	 */
	int size();
}