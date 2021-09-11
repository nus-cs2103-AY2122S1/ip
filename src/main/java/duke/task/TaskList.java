package duke.task;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * The TaskList encapsulates a list of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private ArrayList<Task> prev;

    /**
     * Constructor for a TaskList object with a given ArrayList of Tasks.
     *
     * @param tasks the given ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.prev = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Removes a Task corresponding to the given index and returns it.
     *
     * @param num the index of the Task to be removed.
     * @return the removed Task.
     */
    public Task remove(int num) {
        return tasks.remove(num);
    }

    /**
     * Adds a given Task to the TaskList.
     *
     * @param tsk the Task to be added to the TaskList.
     * @return true if the Task was successfully added, false otherwise.
     */
    public boolean add(Task tsk) {
        return tasks.add(tsk);
    }

    /**
     * Retrieves a Task corresponding to the given index.
     *
     * @param num the index corresponding to the desired Task.
     * @return the Task corresponding to the given index.
     */
    public Task getTask(int num) {
        return tasks.get(num);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     *
     * @return the number of Tasks in the TaskList.
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Marks the Task corresponding to the given index as done.
     *
     * @param num the index corresponding to the Task to be marked as done.
     */
    public void markTask(int num) {
        tasks.get(num).markDone();
    }

    /**
     * Saves a copy of the TaskList, and then clears it of all its Tasks.
     */
    public void clear() {
        // clears taskList and saves the previous one to prevList
        prev = new ArrayList<>(tasks);
        tasks = new ArrayList<>();
    }

    /**
     * Restores the current TaskList to a previously saved TaskList.
     */
    public void restore() {
        tasks = new ArrayList<>(prev);
    }

    /**
     * Returns an ordered list of all the Tasks in the TaskList.
     *
     * @return a String containing an ordered list of all the Tasks in the TaskList
     */
    public String listTaskArr() {
        return listTasks(tasks);
    }

    private String listTasks(ArrayList<Task> lst) {
        StringBuilder res = new StringBuilder();
        ListIterator<Task> iter = lst.listIterator();

        while (iter.hasNext()) {
            res.append(iter.nextIndex() + 1).append(". ").append(iter.next());
            if (iter.hasNext()) {
                res.append("\n");
            }
        }

        return res.toString();
    }

    /**
     * Returns a ListIterator of the Tasks in the TaskList.
     *
     * @return a ListIterator of the Tasks in the TaskList.
     */
    public ListIterator<Task> makeIterator() {
        return tasks.listIterator();
    }

    /**
     * Finds Tasks with names matching a given key and returns an ordered list of the matching tasks.
     *
     * @param key the given key to match with.
     * @return an ordered list of matching Tasks.
     */
    public String findTasks(String key) {
        ListIterator<Task> iter = tasks.listIterator();
        ArrayList<Task> found = new ArrayList<>();

        while (iter.hasNext()) {
            Task t = iter.next();
            if (t.getName().toLowerCase().contains(key.toLowerCase())) {
                found.add(t);
            }
        }

        if (found.size() == 0) {
            return "no matching tasks found";
        } else {
            return "Here are the matching tasks in your list:\n" + listTasks(found);
        }
    }
}
