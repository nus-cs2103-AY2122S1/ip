package duke.utils;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Class that represents a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor that initializes a TaskList object
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds a given task to the TaskList
     *
     * @param task
     */
    public void addTask(Task task){
        tasks.add(task);
    }

    /**
     * Getting a task at a specified index in the TaskList
     *
     * @param index
     * @return
     */
    public Task getTask(int index){
        return tasks.get(index);
    }

    /**
     * Removing a task from the specified index in the TaskList
     * @param index
     */
    public void removeTask(int index){
        tasks.remove(index);
    }

    // 4. clearing tasklist

    /**
     * Clears the TaskList of all tasks
     */
    public void clearTaskList(){
        tasks.clear();
    }

    // 5. checking number of duke.tasks in tasklist

    /**
     * @return int number of tasks in the TaskList
     */
    public int numberOfTasks(){
        return tasks.size();
    }

}
