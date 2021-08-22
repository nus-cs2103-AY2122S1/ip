package duke;

import java.util.ArrayList;
import duke.task.*;

/**
 * Class to manage the list of users tasks during an application run. Class is basically an alias for
 * Java's arraylist with a more limited interface, implemented due to course requirements.
 */
public class TaskList {
    private static ArrayList<Task> tl;

    public TaskList() {
        tl = new ArrayList<>();
    }

    /**
     * Constructor called when loading in data.
     * @param loadedData loadedData is the TaskList from a previous session.
     */
    public TaskList(ArrayList<Task> loadedData) {
        tl = loadedData;
    }

    public int size() {
        return tl.size();
    }

    public Task get(int i) {
        return tl.get(i);
    }

    public Task delete(int i) {
        return tl.remove(i);
    }

    public void add(Task t) {
        tl.add(t);
    }
}
