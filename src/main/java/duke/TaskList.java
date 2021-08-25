package duke;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> list;

    /**
     * Initialises an empty Tasklist.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Stores a prior Tasklist.
     *
     * @param list Arraylist of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
}
