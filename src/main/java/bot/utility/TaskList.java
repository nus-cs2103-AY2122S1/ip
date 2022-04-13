package bot.utility;

import java.util.List;

import bot.tasks.Task;


/**
 * Represents a list of tasks that can perform operations such as delete and add.
 */
public class TaskList {
    private static List<Task> tasks;

    private TaskList() {

    }

    protected static void initialize() {
        tasks = Logger.loadList();
    }

    /**
     * Returns the List of Tasks currently stored in the hard drive.
     *
     * @return A List of Tasks
     */
    public static List<Task> showTasks() {
        return tasks;
    }

    protected static boolean isShorterThan(int size) {
        return size > tasks.size();
    }

    protected static boolean isEmpty() {
        return tasks.isEmpty();
    }
}
