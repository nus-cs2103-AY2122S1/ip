package bot.utility;

import java.util.List;

import bot.tasks.Task;


/**
 * Represents a list of tasks that can perform operations such as delete and add.
 */
public class TaskList {
    private static List<Task> tasks;
    protected static void initialize() {
        tasks = Logger.loadList();
    }

    public static List<Task> showTasks() {
        return tasks;
    }
}
