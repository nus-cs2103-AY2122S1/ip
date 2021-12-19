package bloom.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bloom.task.Task;


/**
 * Represents a list of tasks.
 */
public class TaskList {

    /** The list of tasks. */
    private static List<Task> tasks;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        TaskList.tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public static int size() {
        return TaskList.tasks.size();
    }

    /**
     * Gets a task based on the inputted index.
     *
     * @param index the index of the task being queried
     * @return      the required task
     */
    public static Task get(int index) {
        return TaskList.tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added to the list
     */
    public static void add(Task task) {
        TaskList.tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index the index of the task to be removed
     * @return      the removed task
     */
    public static Task delete(int index) {
        return TaskList.tasks.remove(index);
    }

    /**
     * Prints upcoming tasks after a specific input date.
     *
     * @param dateTime the input date
     * @return         the upcoming tasks
     */
    public static List<Task> printUpcomingTaskList(LocalDateTime dateTime) {
        return tasks.stream()
                .filter(t -> t.getDate().isBefore(dateTime))
                .collect(Collectors.toList());
    }
}
