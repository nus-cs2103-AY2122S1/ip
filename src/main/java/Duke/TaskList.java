package Duke;

import Duke.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    private final List<Task> list = new ArrayList<>();

    /**
     * Adds a task to the task list.
     * @param task Task to be added to the list.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public Task remove(int index) {
        return this.list.remove(index);
    }

    public Task setDone(int index) {
        Task task = list.get(index);
        task.setDone();
        return task;
    }

    public int size() {
        return this.list.size();
    }

    /**
     * Formats the task list for display.
     * @return Formatted task list in a string.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(LIST_MESSAGE);
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
