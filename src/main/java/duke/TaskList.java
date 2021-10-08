package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Class to manage the list of users tasks during an application run. Class is basically an alias for
 * Java's arraylist with a more limited interface, implemented due to course requirements.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor called when loading in data.
     *
     * @param loadedData loadedData is the TaskList from a previous session.
     */
    public TaskList(ArrayList<Task> loadedData) {
        tasks = loadedData;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Task delete(int i) {
        return tasks.remove(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }
}
