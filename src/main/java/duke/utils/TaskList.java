package duke.utils;

import java.util.*;

import duke.tasks.*;

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
    public Task getTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input);
            return userInputs.get(taskIndex - 1);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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
     * @param input   the task of interest
     * 
     */
    public void addTask(Task task) throws DukeException {
        userInputs.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + userInputs.size() + " tasks in the list.");
    }

    /**
     * Removes a specific task to memory and prints out confirmations
     * @param input  the index of task of interest
     */
    public void deleteTask(String input) {
        try {
            System.out.println("Noted. I've removed this task: ");
            int taskIndex = Integer.parseInt(input);
            Task task = userInputs.get(taskIndex - 1);
            userInputs.remove(taskIndex - 1);
            System.out.println(" " + task);
            task = null;
            System.out.println("Now you have " + userInputs.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
