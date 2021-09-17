package duke;

import java.util.ArrayList;

/**
 * Class for TaskList which is a list
 * that contains all the tasks in Duke.
 *
 * @author Samuel Lau
 */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList with tasks.
     *
     * @param savedData ArrayList of tasks to be initialized as list.
     */
    public TaskList(ArrayList<Task> savedData) {
        this.list = savedData;
    }

    /**
     * Adds task to the list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param number Number indicating task location in the ArrayList.
     */
    public void delete(int number) {
        list.remove(number);
    }

    /**
     * Marks specified task from the list as completed.
     *
     * @param number Number indicating task location in the ArrayList.
     */
    public void done(int number) {
        list.get(number).markAsDone();
    }
}