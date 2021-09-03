package duke;

import java.util.*;

public class TaskList {
    List<Task> taskList;

    /**
     * Creates a TaskList Object with no tasks.
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a TaskList Object with tasks.
     *
     * @param tasks The tasks the user has.
     */
    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds an item to the task list.
     *
     * @param input The input entered by the user.
     * @return The new task generated based on the user's input.
     */
    public Task addItem(String input) {
        Task t;

        if (input.contains("todo")) {
            String description = input.substring(input.indexOf(' ') + 1);
            t = new ToDo(description);
        } else {
            String description = input.substring(input.indexOf(' ') + 1, input.lastIndexOf('/') - 1);
            String time = input.substring(input.lastIndexOf("/") + 4);
            if (input.contains("deadline")) {
                t = new Deadline(description, time);
            } else {
                t = new Event(description, time);
            }
        }

        taskList.add(t);

        return t;
    }

    /**
     * Deletes an item from the task list.
     *
     * @param index The index of the item to be deleted.
     * @return The task that was just deleted.
     */
    public Task deleteItem(int index) {
        Task t = getTask(index);
        taskList.remove(index);

        return t;
    }

    /**
     * Gets an item from the task list based on the index of the item.
     *
     * @param index The index of the item.
     * @return The item at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() { return taskList.size(); }

    /**
     * Finds matching tasks from the task list based on the keyword provided.
     *
     * @param keyword The keyword provided by the user.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> find (String keyword) {
        List<Task> results = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = getTask(i);
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }

        return results;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked done.
     */
    public void markAsDone(int index) {
        Task t = getTask(index);
        t.setDone();
    }
}
