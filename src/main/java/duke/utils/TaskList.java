package duke.utils;


import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the non-persistent memory of tasks.
 */
public class TaskList {
    ArrayList<Task> userInputs;

    /**
     * Takes no arguments. It is to initialize an empty TaskList.
     */
    public TaskList() {
        this.userInputs = new ArrayList<>();
    }

    /**
     * Takes in an arraylist of task. Is used to initialize with some past data (For rebooting purposes)
     *
     * @param userInputs   an Arraylist of tasks
     */
    public TaskList(ArrayList<Task> userInputs) {
        this.userInputs = userInputs;
    }

    /**
     * Retrieves a specific task given by the index. Indexing starts from 0.
     *
     * @param input   the index of the task
     * @return Specific task of interest
     */
    public Task getTask(Integer input) {
        return userInputs.get(input);
    }

    /**
     * Retrieves all tasks
     *
     * @return All tasks in memory.
     */
    public ArrayList<Task> getTasks() {
        return userInputs;
    }

    /**
     * Adds a specific task to memory and prints out confirmations
     * @param task   the task of interest
     * 
     */
    public void addTask(Task task) throws DukeException {
        userInputs.add(task);
        System.out.println(task);
    }

    /**
     * Removes a specific task to memory and prints out confirmations
     * @param input  the index of task of interest
     */
    public void deleteTask(int input) {
        userInputs.remove(input);
    }
}
