import java.util.ArrayList;
import java.util.List;

/**
 * The is the TaskManager class that that
 * contains a list of task.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class TaskManager {
    private static final String INDENTATION = "     ";
    private final List<Task> tasks;

    /**
     * This is constructor method of TaskManager
     */
    TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Add task to TaskManager
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Print tasks from TaskManager with format:
     *      1. Task1
     *      2. Task2
     *      ...
     */
    public void printTasks() {
        for(int i = 0; i < tasks.size(); i++) {
            String task = INDENTATION + (i + 1) + ". " + tasks.get(i).getName();
            System.out.println(task);
        }
    }
}
