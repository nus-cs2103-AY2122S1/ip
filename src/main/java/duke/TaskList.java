package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public TaskList(List<Task> taskList) {
        for (Task task : taskList) {
            this.taskList.add(task);
        }
    }

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
     * Gets the i-th task from the task list, using 1-based indexing.
     * This means that getTask(1) removes the first task in the list.
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
     * Deletes the i-th task from the task list, using 1-based indexing.
     * This means that deleteTask(1) removes the first task in the list.
     *
     * @param i The index of the task to be removed based on 1-based indexing.
     * @return the task that is removed.
     * @throws IndexOutOfBoundsException if the task index specified is less than 1 or more than the size of the list.
     */
    public Task deleteTask(int i) throws IndexOutOfBoundsException {
        int idx = i - 1;
        return taskList.remove(idx);
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
     * Produces a string representing the store format of the list of tasks for duke.Duke.
     *
     * @return a String of the list of tasks in duke.Duke store format.
     */
    public String toDukeStoreFormat() {
        String outputString =
                this.taskList.stream().map(task -> task.toDukeStoreFormat() + "\n").collect(Collectors.joining());
        return outputString;
    }

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
