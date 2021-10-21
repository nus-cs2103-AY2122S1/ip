package duke.task;

import static java.util.AbstractMap.SimpleImmutableEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exception.InvalidTaskNumberException;

/**
 * Encapsulates a list of tasks that the user wants to keep track of.
 */
public class TaskList {
    private final List<Task> tasks;
    private final int limit;

    /**
     * Creates the task list with the given limit.
     *
     * @param limit the limit to the size of the list. The number of tasks cannot exceed this limit.
     */
    public TaskList(int limit) {
        tasks = new ArrayList<>();
        this.limit = limit;
    }

    /**
     * Creates the task list with maximum limit of {@code Integer.MAX_VALUE}.
     */
    public TaskList() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Adds the task into the list.
     *
     * @param task The task to be added
     * @throws InvalidTaskNumberException when the task number given is invalid.
     */
    public void addTask(Task task) {
        if (tasks.size() >= limit) {
            throw new InvalidTaskNumberException(size());
        }
        tasks.add(task);
    }

    private boolean validTaskNumber(int taskNumber) {
        return taskNumber <= tasks.size() && taskNumber > 0;
    }

    /**
     * Removes the task according to the specified task number.
     *
     * @param taskNumber The task number of the task to be removed. This is the number that the user sees.
     * @return the task that is being removed
     * @throws InvalidTaskNumberException when the task number given is invalid.
     */
    public Task removeTask(int taskNumber) {
        if (validTaskNumber(taskNumber)) {
            return tasks.remove(taskNumber - 1);
        }
        throw new InvalidTaskNumberException(size());
    }

    /**
     * Gets the task associated with the task number.
     *
     * @param taskNumber The task number of the task to be retrieved. This is the number that the user sees.
     * @return the relevant task
     * @throws InvalidTaskNumberException when the task number given is invalid.
     */
    public Task getTask(int taskNumber) {
        if (!validTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(size());
        }
        return tasks.get(taskNumber - 1);
    }

    /**
     * Gets all the tasks in the list.
     *
     * @return the immutable list of tasks in this task list
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Gets the list of all upcoming tasks in the list, sorted chronologically.
     *
     * @return the list of tasks, with their corresponding task number
     */
    public List<SimpleImmutableEntry<? extends Task, Integer>> getUpcomingTasks() {
        Map<Task, Integer> upcomingTasks = new TreeMap<>((task1, task2) -> {
            LocalDateTime dateTime1 = task1.getDateTime();
            LocalDateTime dateTime2 = task2.getDateTime();
            // time can be null if task is to-do. By default, put all to-do to the last.
            return dateTime1 == null
                ? 1
                : dateTime2 == null
                ? -1
                : dateTime1.equals(dateTime2) // In this situation, we have to sort based on order in tasklist.
                ? Integer.compare(tasks.indexOf(task1), tasks.indexOf(task2))
                : dateTime1.compareTo(dateTime2);
        });
        IntStream.range(1, tasks.size() + 1).forEach(i -> {
            Task task = tasks.get(i - 1);
            String type = task.getTaskType();
            if (!task.isDone() && (// Task is not done and it is either to-do or the date is later than now.
                type.equals("T") || task.getDateTime().isAfter(LocalDateTime.now()))) {
                upcomingTasks.put(task, i);
            }
        });
        return upcomingTasks.entrySet().stream().map(SimpleImmutableEntry::new)
            .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Gets all the tasks in the list, along with the task number.
     *
     * @return the immutable list of tasks with task numbers in this task list
     */
    public List<SimpleImmutableEntry<? extends Task, Integer>> getAllTasks() {
        List<SimpleImmutableEntry<? extends Task, Integer>> result = new ArrayList<>();
        IntStream.range(0, tasks.size()).forEach(i -> result.add(new SimpleImmutableEntry<>(tasks.get(i), i + 1)));
        return Collections.unmodifiableList(result);
    }

    /**
     * Gets all the tasks in the list containing the string in the description.
     *
     * @param pattern the pattern to be matched with the task descriptions in the list
     * @return the immutable list of tasks containing str in the description, with their task numbers
     */
    public List<SimpleImmutableEntry<? extends Task, Integer>> getTasksContaining(String pattern) {
        return getAllTasks().stream().filter(taskAndNumber ->
                taskAndNumber.getKey().getDescription().toUpperCase()
                    .contains(pattern.toUpperCase()))
            .collect(Collectors.toUnmodifiableList());
    }


    /**
     * Marks the selected task as done.
     *
     * @param taskNumber the task number to be marked as done. This is the number that the user sees.
     * @return true if task is successfully marked, or false if task has been marked as done before.
     * @throws InvalidTaskNumberException when the task number given is invalid.
     */
    public boolean markAsDone(int taskNumber) {
        return getTask(taskNumber).markAsDone();
    }

    /**
     * Gets the size of the task list.
     *
     * @return the size of the list as an integer.
     */
    public int size() {
        return tasks.size();
    }

}
