package duke;

import java.util.ArrayList;
import java.util.Iterator;

import duke.task.Task;

/**
 * A list of tasks.
 */
public class DukeList implements Iterable<Task> {
    /** ArrayList used to store tasks. */
    private final ArrayList<Task> list;

    /**
     * Public constructor to create an empty DukeList.
     */
    public DukeList() {
        list = new ArrayList<>();
    }

    /**
     * Public constructor that takes in an
     * ArrayList to create a DukeList.
     *
     * @param list ArrayList to copy tasks.
     */
    public DukeList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the task at a specific index.
     *
     * @param i Index of the task.
     * @return Task.
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds a new task.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task.
     *
     * @param i Index of the task to be deleted.
     */
    public void deleteTask(int i) {
        list.remove(i);
    }

    public String getMatches(String keyWord) {
        StringBuilder matchesString = new StringBuilder("Here are the matching tasks in your list:\n");
        int matches = 0;
        for (Task task : list) {
            if (task.getDescription().contains(keyWord)) {
                matchesString.append(matches++ + 1).append(".").append(task.toString()).append("\n");
            }
        }
        if (matches == 0) {
            return "No matches found";
        } else {
            assert matches > 0 : "Matches must be more then 0";
            return matchesString.toString();
        }
    }

    /**
     * Returns the String representation of the list.
     *
     * @return String representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String line = (i + 1) + "." + task.toString() + "\n";
            listString.append(line);
        }
        return listString.toString();
    }

    /**
     * Iterator.
     *
     * @return Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }

}
