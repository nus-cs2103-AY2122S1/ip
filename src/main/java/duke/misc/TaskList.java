package duke.misc;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList() throws IOException {
        storage = new Storage();
        tasks = storage.readData();
    }

    // creates a string of list of task
    public String displayList() {
        StringBuilder sb = new StringBuilder("");
        int idx = 0;
        for (Task task : tasks) {
            sb.append(String.format("%d.%s\n", ++idx, task.toString()));
        }
        return sb.toString();
    }

    // adds task to list
    public String add(Task task) {
        tasks.add(task);
        return task.toString();
    }

    // removes the task
    public String delete(int idx) throws DukeException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        String message = tasks.get(idx - 1).toString();
        tasks.remove(idx - 1);
        return message;
    }

    // mark task as done
    public String complete(int idx) throws DukeException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(idx - 1).markDone();
        return tasks.get(idx - 1).toString();
    }

    // saves data
    public void saveData() throws IOException {
        try {
            storage.writeData(tasks);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Finds all tasks with specified keyword.
     *
     * @param key keyword to search.
     * @return String of all tasks found appended in rows.
     */
    public String find(String key) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (Task task: tasks) {
            if (task.getDescription().contains(key)) {
                sb.append(String.format("%d.%s\n", ++idx, task.toString()));
            }
        }
        return sb.toString();
    }
}
