package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * A class that represents a list of tasks.
 */
public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    /**
     * Constructs an instance of task list from a List of Tasks.
     *
     * @param taskList a List of Tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList.addAll(taskList);
    }

    /**
     * Constructs an instance of task list that is empty.
     */
    public TaskList() {

    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return a int of the number of tasks.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns true if the task list is empty.
     *
     * @return true if the task list is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Gets the i-th task from the task list, using 1-based indexing.
     * This means that getTask(1) gets the first task in the list.
     *
     * @param i The index of the task to be returned based on 1-based indexing.
     * @return the task that corresponds to the index based on 1-based indexing.
     * @throws IndexOutOfBoundsException if the task index specified is less than 1 or more than the size of the list.
     */
    public Task getTask(int i) throws IndexOutOfBoundsException {
        int idx = i - 1;
        return taskList.get(idx);
    }

    /**
     * Marks the i-th task from the task list as done, using 1-based indexing.
     * This means that doTask(1) marks the first task in the list as done.
     *
     * @param i The index of the task to be marked as done based on 1-based indexing.
     * @return the task that corresponds to the index based on 1-based indexing.
     * @throws IndexOutOfBoundsException if the task index specified is less than 1 or more than the size of the list.
     */
    public Task doTasks(int i) throws IndexOutOfBoundsException {
        int idx = i - 1;
        Task task = taskList.get(idx);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the tasks in the task list from indices start to end, inclusive, as done, using 1-based indexing.
     * This means that doTasks(1,3) marks the first 3 task in the list as done.
     *
     * @param start The index of the first to be marked as done based on 1-based indexing, inclusive.
     * @param end The index of the last task to be marked as done based on 1-based indexing, inclusive.
     * @return the tasks that are marked as done by this method.
     * @throws IndexOutOfBoundsException if the task index specified is less than 1 or more than the size of the list.
     */
    public TaskList doTasks(int start, int end) throws IndexOutOfBoundsException {
        if (start < 1 || end > this.size() || start > end) {
            throw new IndexOutOfBoundsException();
        }
        TaskList completedTasks = new TaskList();
        int startIdx = start - 1;
        for (int i = startIdx; i < end; i++) {
            Task task = taskList.get(i);
            task.markAsDone();
            completedTasks.appendTask(task);
        }

        return completedTasks;
    }

    /**
     * Deletes the i-th task from the task list, using 1-based indexing.
     * This means that deleteTasks(1) removes the first task in the list.
     *
     * @param i The index of the task to be removed based on 1-based indexing.
     * @return the task that is removed.
     * @throws IndexOutOfBoundsException if the task index specified is less than 1 or more than the size of the list.
     */
    public Task deleteTasks(int i) throws IndexOutOfBoundsException {
        int idx = i - 1;
        return taskList.remove(idx);
    }

    /**
     * Deletes the tasks from indices start to end, inclusive, from the task list, using 1-based indexing.
     * This means that deleteTasks(1,3) removes the first 3 task in the list.
     *
     * @param start The index of the first task to be removed based on 1-based indexing, inclusive.
     * @param end The index of the last task to be removed based on 1-based indexing, inclusive.
     * @return the tasks that are removed.
     * @throws IndexOutOfBoundsException if the task index specified is less than 1 or more than the size of the list.
     */
    public TaskList deleteTasks(int start, int end) throws IndexOutOfBoundsException {
        if (start < 1 || end > this.size() || start > end) {
            throw new IndexOutOfBoundsException();
        }
        TaskList removedTasks = new TaskList();
        int startIdx = start - 1;
        int numOfRemovals = end - start + 1;
        for (int i = 0; i < numOfRemovals; i++) {
            Task removedTask = taskList.remove(startIdx);
            removedTasks.appendTask(removedTask);
        }

        return removedTasks;
    }

    /**
     * Appends a task to the list of existing tasks.
     *
     * @param task duke.Task to be added to list.
     */
    public void appendTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns a list of tasks whose descriptions contains a specified pattern.
     *
     * @param pattern a string of the pattern to be matched.
     * @return a list of matching tasks.
     */
    public TaskList findTasks(String pattern) {
        TaskList filteredList = new TaskList();
        for (Task task : this.taskList) {
            if (task.hasKeyWord(pattern)) {
                filteredList.appendTask(task);
            }
        }
        return filteredList;
    }

    /**
     * Produces a string representing the store format of the list of tasks for Duke.
     *
     * @return a String of the list of tasks in Duke store format.
     */
    public String toDukeStoreFormat() {
        return this.taskList.stream().map(task -> task.toDukeStoreFormat() + "\n").collect(Collectors.joining());
    }

    /**
     * Returns a string that shows the details of the list of tasks.
     *
     * @return a string of the tasks' details.
     */
    @Override
    public String toString() {
        AtomicInteger idx = new AtomicInteger(1);

        String outputList = taskList.stream().map(task -> Integer.toString(idx.getAndIncrement()) + ". " + task + "\n")
                .collect(Collectors.joining());
        // Remove last newline for prettier formatting
        if (outputList.length() > 0) {
            outputList = outputList.substring(0, outputList.length() - 1);
        }

        return outputList;
    }
}
