package duke.main;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * 
 * @author Gordon Yit
 * @Version Cs2103T, Semester 2
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor, loads arraylist of tasks from the storage to the tasks arraylist.
     * 
     * @param tasksStored an arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasksStored) {
        tasks = new ArrayList<>(100);
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
     * @return the updated tasks arraylist.
     */
    public ArrayList<Task> add(Task task) {
        this.tasks.add(task);
        return tasks;
    }

    /**
     * Retrieves a task based on the given index.
     * 
     * @param taskIndex index of the task. 
     * @return a task matching the given index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
    
    /**
     * Marks the task corresponding to the done. 
     *
     * @param taskIndex index of the task to be marked done. 
     */
    public Task markDone(int taskIndex) {
        Task task = getTask(taskIndex);
        task.markAsDone();
        return task;
    }
    
    /**
     * Deletes a task from the task list.
     * 
     * @param taskIndex index of the task to be deleted.
     * @return the updated tasks arraylist.
     */
    public ArrayList<Task> delete(int taskIndex) {
        this.tasks.remove(taskIndex);
        return tasks;
    }
    
    /**
     * Finds all the tasks matching the date given. 
     * 
     * @param searchPhrase the phrase use to filter out tasks.
     * @return an arraylist of tasks containing the searchPhrase.
     */
    public ArrayList<Task> findMatchingTasks(String searchPhrase) {
        ArrayList<Task> tasksMatchingDate = new ArrayList<>();
        for (Task t: tasks) {
            if (t.hasSearchPhrase(searchPhrase)) {
                tasksMatchingDate.add(t);
            }
        }
        return tasksMatchingDate;
    }

    /**
     * Returns the tasks arraylist.
     * 
     * @return arraylist of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    /**
     * Provides the number of tasks in the tasks list.
     * @return size of the tasks arraylist.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

}
