package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * This class represents Duke's list of tasks.
 */
public class TaskList {
    private static final String STORAGE_PATH = "data/duke.txt";
    private List<Task> tasks = new ArrayList<>();
    private Storage storage;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() throws DukeException {
        try {
            // Load data from saved file, if present.
            storage = new Storage(STORAGE_PATH);
            tasks = storage.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
        save();
    }

    /**
     * Gets a task from the task list.
     *
     * @param index Index of the task to be retrieved.
     * @return Task at given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index Index of the task to be removed.
     * @return Removed task.
     */
    public Task remove(int index) {
        Task task = tasks.remove(index);
        save();
        return task;
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked done.
     * @return Task marked as done.
     */
    public Task setDone(int index) {
        Task task = tasks.get(index);
        task.setDone();
        save();
        return task;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Searches task list for tasks with description containing search term.
     *
     * @param searchTerm Search term inputted by user.
     * @return task list containing only tasks that match search term.
     */
    public TaskList search(String searchTerm) throws DukeException {
        List<Task> matches = tasks.stream().filter(task -> task.getName().contains(searchTerm))
                .collect(Collectors.toList());
        TaskList output = new TaskList();
        output.tasks = matches;
        return output;
    }

    /**
     * Saves the task list to text file.
     */
    private void save() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats the task list for display.
     *
     * @return Formatted task list in a string.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("%d. %s", i + 1, tasks.get(i)));
            // Append new line for all lines except last line.
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
