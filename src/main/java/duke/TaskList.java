package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private void save() {
        try {
            storage.save(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats the task list for display.
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
