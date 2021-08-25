package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private static final String STORAGE_PATH = "data/duke.txt";
    private List<Task> list = new ArrayList<>();
    private Storage storage;

    public TaskList() {
        try {
            this.storage = new Storage(STORAGE_PATH);
            list = storage.readFile();
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
        this.list.add(task);
        this.save();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public Task remove(int index) {
        Task task = this.list.remove(index);
        this.save();
        return task;
    }

    public Task setDone(int index) {
        Task task = list.get(index);
        task.setDone();
        this.save();
        return task;
    }

    public int size() {
        return this.list.size();
    }

    /**
     * Searches task list for tasks with description containing search term.
     *
     * @param searchTerm Search term inputted by user.
     * @return task list containing only tasks that match search term.
     */
    public TaskList search(String searchTerm) {
        List<Task> matches = list.stream()
                .filter(task -> task.getName().contains(searchTerm))
                .collect(Collectors.toList());
        TaskList output = new TaskList();
        output.list = matches;
        return output;
    }

    /**
     * Saves the task list to text file.
     */
    private void save() {
        try {
            storage.save(this.list);
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
        for (int i = 0; i < this.list.size(); i++) {
            output.append(String.format("%d. %s", i + 1, this.list.get(i)));
            // Append new line for all lines except last line.
            if (i != this.list.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
