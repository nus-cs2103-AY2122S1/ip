package IP.duke.main;

import IP.duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * 
 * @author Gordon Yit
 * @version Cs2103T, Semester 2
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor, loads arraylist of tasks from the storage to the tasks arraylist.
     * 
     * @param tasksStored an arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasksStored) {
        tasks = new ArrayList<>();
        for (Task t : tasksStored) {
            this.tasks.add(t);
        }
    }

    /**
     * Alternative class constructor, used when there is an error loading the file.
     * Initiates the tasks arraylist.
     */
    public TaskList() {
      tasks = new ArrayList<>(100);  
    }
    
    /**
     * Adds a task to the tasks list.
     * 
     * @param task a task to be added.
     * @return the added task.
     */
    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Retrieves a task based on the given index.
     * 
     * @param taskIndex index of the task. 
     * @return a task matching the given index.
     * @throws IndexOutOfBoundsException if taskIndex is negative or greater than size of tasks.
     */
    public Task getTask(int taskIndex) throws  IndexOutOfBoundsException{
        return tasks.get(taskIndex);
    }
    
    /**
     * Marks the task corresponding to the done. 
     *
     * @param taskIndex index of the task to be marked done.
     * @return the task marked done.
     * @throws IndexOutOfBoundsException if taskIndex is negative or greater than size of tasks.
     */
    public Task markDone(int taskIndex) throws IndexOutOfBoundsException{
        Task task = getTask(taskIndex);
        task.markAsDone();
        return task;
    }
    
    /**
     * Deletes a task from the task list.
     * 
     * @param taskIndex index of the task to be deleted.
     * @return the task deleted.
     * @throws IndexOutOfBoundsException if taskIndex is negative or greater than size of tasks.
     */
    public Task delete(int taskIndex) throws IndexOutOfBoundsException {
        Task task = tasks.get(taskIndex);
        tasks.remove(task);
        return task;
    }
    
    /**
     * Finds all the tasks matching the date given. 
     * 
     * @param date the date of interest.
     * @return an taskList of tasks which fall on that date.
     */
    public TaskList findTasksMatchingDate(Date date) {
        String dateString = date.toString();
        TaskList matchingTasks = new TaskList();
        for (Task t: tasks) {
            if (t.isSameDate(dateString)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the total number of tasks current tracked by Duke.
     * 
     * @return size of tasks arraylist.
     */
    public int getNumTasks() {
        return tasks.size();
    }
    
}
