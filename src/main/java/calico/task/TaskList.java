package calico.task;

import java.util.ArrayList;
import java.util.HashSet;

import calico.CalicoException;

/**
 * Represents a list of tasks to be completed. Supports operations on the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList for Duke chatbot using a given list of tasks.
     *
     * @param tasks Filepath to save tasks and load tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList for Duke chatbot.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves a task.
     *
     * @param i Index of task to be retrieved.
     * @return Task.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Retrieves length of the list of tasks.
     *
     * @return Length of task list.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return Tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param i Index of task to be marked.
     */
    public void markDone(int i) {
        tasks.get(i).markDone();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param t Task to be added.
     * @throws CalicoException when duplicate task exists.
     */
    public void add(Task t) throws CalicoException {
        checkDuplicates(t);
        tasks.add(t);
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param i Index of task to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }


    /**
     * Searches for tasks that matches the category.
     *
     * @param cat Category to search for.
     * @return ArrayList of matching tasks.
     */
    public ArrayList<Task> findCat(char cat) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task i: tasks) {
            if (i.getCat() == cat) {
                results.add(i);
            }
        }
        return results;
    }

    /**
     * Searches for tasks that matches the search query.
     *
     * @param query Keyword to search for.
     * @return ArrayList of matching tasks.
     */
    public ArrayList<Task> find(String query) {
        if (query.equals("todo")) {
            return findCat('T');
        } else if (query.equals("deadline")) {
            return findCat('D');
        } else if (query.equals("event")) {
            return findCat('E');
        } else {
            ArrayList<Task> results = new ArrayList<>();

            for (Task i : tasks) {
                String s = i.getName() + " " + i.getDesc();
                if (s.matches("^.*" + query + ".*")) {
                    results.add(i);
                }
            }
            return results;
        }
    }

    /**
     * Converts TaskList to string format.
     *
     * @return TaskList as a string.
     */
    @Override
    public String toString() {
        return "contains tasks";
    }

    /**
     * Checks for duplicates in task list.
     *
     * @param t Task to be checked.
     */
    private void checkDuplicates(Task t) throws CalicoException {
        tasks.add(t);
        // Convert to HashSet to check for duplicates
        HashSet<Task> set = new HashSet<>(tasks);
        ArrayList<Task> result = new ArrayList<>(set);
        // Check if there are duplicates using length of ArrayLists.
        if (tasks.size() != result.size()) {
            tasks.remove(tasks.size() - 1);
            throw new CalicoException("task has already been added");
        }
        tasks.remove(tasks.size() - 1);
    }
}
