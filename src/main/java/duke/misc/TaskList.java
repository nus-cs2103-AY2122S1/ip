package duke.misc;

import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * TaskList class which encapsulates all tasks, as well as handle information and
 * operations relating to the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        storage = new Storage();
        try {
            tasks = storage.readData();
        } catch (IOException e) {
            Ui.printBlock("No such directory!");
        }
    }

    /**
     * Creates a string with all task information appended in rows.
     *
     * @return String of all task information.
     */
    public String displayList() {
        StringBuilder sb = new StringBuilder("");
        int idx = 0;
        for (Task task : tasks) {
            sb.append(String.format("%d.%s\n", ++idx, task.toString()));
        }
        return sb.toString();
    }

    /**
     * Adds a task to current TaskList.
     *
     * @param task The task to be added.
     * @return The String output by toString method of task to be added.
     */
    public String add(Task task) {
        tasks.add(task);
        return task.toString();
    }

    /**
     * Deletes specified task from current TaskList.
     *
     * @param idx Index of task to be deleted.
     * @return The String output by toString method of task to be added.
     * @throws DukeException In case the index of task is out of bounds.
     */
    public String delete(int idx) throws DukeException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        String message = tasks.get(idx - 1).toString();
        tasks.remove(idx - 1);
        return message;
    }

    /**
     * Completes specified task from current TaskList.
     *
     * @param idx Index of task to be completed.
     * @return The String output by toString method of task to be added.
     * @throws DukeException In case index of task is out of bounds.
     */
    public String complete(int idx) throws DukeException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(idx - 1).markDone();
        return tasks.get(idx - 1).toString();
    }

    /**
     * Saves all tasks into specified directory.
     *
     * @throws IOException In case directory is invalid.
     */
    public void saveData() throws IOException {
        storage.writeData(tasks);
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
