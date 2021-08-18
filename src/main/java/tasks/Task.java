package tasks;

import java.util.List;
import java.util.ArrayList;

/**
 * This class encapsulates a user-added task
 *
 * @author kevin9foong
 */
public class Task {
    private final String taskText;
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public Task(String taskText) {
        this.taskText = taskText;
    }

    /**
     * Adds a new task to the taskList.
     * @param task task to be added
     */
    public static void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieves a new list containing all tasks in the taskList.
     * @return new list containing copy of all tasks from the taskList
     */
    public static List<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }

    @Override
    public String toString() {
        return this.taskText;
    }
}
