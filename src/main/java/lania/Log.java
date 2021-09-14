package lania;

import java.util.ArrayList;

import javafx.util.Pair;
import lania.task.Task;

/**
 * The class that deals with storing the history of user commands
 * including add, delete and complete commands only.
 * Note that the logs are not saved into hard drive and will
 * disappear after the program exits.
 */
public class Log {
    /** List of past user commands */
    private ArrayList<String> commandLog;
    /** List of tasks marked as complete */
    private ArrayList<Integer> doneTasks;
    /** List of past removed tasks */
    private ArrayList<Pair<Task, Integer>> removedTasks;

    /**
     * Constructor for Log. Initialises the empty fields.
     */
    public Log () {
        commandLog = new ArrayList<>();
        removedTasks = new ArrayList<>();
        doneTasks = new ArrayList<>();
    }

    /**
     * Stores the 'add' command given by a string.
     *
     * @param description The type of command given.
     */
    public void addLog(String description) {
        commandLog.add(description);
    }

    /**
     * Stores the 'delete' command given by a string
     * and the deleted task.
     *
     * @param description The type of command given.
     * @param task The removed task.
     */
    public void addLog(String description, Task task, int index) {
        commandLog.add(description);
        Pair<Task, Integer> taskPair = new Pair<>(task, index - 1);
        removedTasks.add(taskPair);
    }

    /**
     * Stores the 'complete' command given by a string
     * and the completed task.
     *
     * @param description The type of command given.
     * @param i The index of the completed task.
     */
    public void addLog(String description, Integer i) {
        commandLog.add(description);
        doneTasks.add(i);
    }

    /**
     * Retrieves the most recent command.
     *
     * @return The most recent command.
     */
    public String getRecentLog() {
        String recentCommand;
        try {
            recentCommand = commandLog.remove(commandLog.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            recentCommand = "You have no tasks to undo!";
        }
        return recentCommand;
    }

    /**
     * Retrieves the most recent deleted task.
     *
     * @return The most recent deleted task.
     */
    public Pair<Task, Integer> getDeletedTask() {
        Pair<Task, Integer> recentTask = removedTasks.get(removedTasks.size() - 1);
        return recentTask;
    }

    /**
     * Retrieves the most recent completed task.
     *
     * @return The index indicating the most recent completed task.
     */
    public Integer getMarkedTask() {
        Integer recentMarked = doneTasks.get(doneTasks.size() - 1);
        return recentMarked;
    }
}
