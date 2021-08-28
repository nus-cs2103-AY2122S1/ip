package bot.utility;

import java.util.List;

import bot.tasks.Task;


/**
 * Represents a list of tasks that can perform operations such as delete and add.
 */
public class TaskList {
    private final List<Task> tasks;

    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /*
    protected void addTask(Task) {
        this.tasks.add(task);
    }

    protected void deleteTask(int index) {
        this.tasks.remove(index);
    }

    protected void updateTask(int index) {
        this.tasks.get(index).markAsDone();
    }*/

    protected List<Task> showTasks() {
        return this.tasks;
    }
}
