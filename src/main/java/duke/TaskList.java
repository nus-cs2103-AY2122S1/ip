package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * The class to represent a list of tasks the user has.
 */
public class TaskList {

    /** The list of tasks */
    private ArrayList<Task> listOfTasks;

    private Task getMostRecentlyDeletedTask = null;

    /**
     * The constructor of the TaskList class.
     *
     * @param tasks the list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.listOfTasks = tasks;
    }

    /**
     * The constructor of the TaskList class.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Shows the user all the tasks in the list.
     *
     * @return the string containing all tasks
     */
    public String showAllTasks() {
        if (listOfTasks.isEmpty()) {
            return "You currently have no tasks! Add one now ☻";
        } else {
            return "Here are the tasks in your list:\n" + showTasks(listOfTasks);
        }
    }

    /**
     * Shows the user all the matching tasks given a keyword.
     *
     * @param matchingTasks the list of tasks matching the keyword given by user
     * @return the string containing all matching tasks
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found! ☹";
        } else {
            return "Here are the tasks that match this keyword:\n" + showTasks(matchingTasks);
        }
    }

    /**
     * Returns all tasks as a String.
     *
     * @param tasks the tasks in the list
     * @return the String containing all tasks in the given ArrayList
     */
    public String showTasks(ArrayList<Task> tasks) {
        String output = "";
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task t = tasks.get(i);
            output = output + (i + 1) + "." + t.toString() + "\n";
        }
        return output;
    }


    /**
     * Returns if the list of tasks is empty.
     *
     * @return whether the list of tasks is empty
     */
    public boolean isEmpty() {
        return this.listOfTasks.isEmpty();
    }

    /**
     * Returns the number of elements in the list of tasks.
     *
     * @return the number of elements in the list of tasks
     */
    public int size() {
        return this.listOfTasks.size();
    }

    /**
     * Returns the task at the specific index in the list.
     *
     * @return the task at the specific index in the list
     */
    public Task get(int i) {
        return this.listOfTasks.get(i);
    }

    /**
     * Adds a Task to the list.
     *
     * @param t the Task to be added
     * @return the String to show to users
     */
    public String addTask(Task t) {
        listOfTasks.add(t);
        return "Okay! Task added:\n  " + t.toString() + "\n"
                + "You now have " + listOfTasks.size() + " task(s) in the list.";
    }

    /**
     * Adds a Task to the list at the specified index.
     *
     * @param t the Task to be added
     * @param index the index in the list to add the Task at
     * @return the String to show to users
     */
    public String addTask(Task t, int index) {
        listOfTasks.add(index, t);
        return "Okay! Task added back:\n  " + t.toString() + "\n"
                + "You now have " + listOfTasks.size() + " task(s) in the list.";
    }

    /**
     * Deletes a Task from the list.
     *
     * @param index the index of the Task to be deleted
     * @return the String to show to users
     */
    public String deleteTask(int index) {
        int numOfTasks = listOfTasks.size();
        if (index >= numOfTasks) {
            return "No task of this number. Add new task or input a different number.";
        } else if (index < 0) {
            return "Input a task number from 1 - " + numOfTasks;
        } else {
            Task t = listOfTasks.get(index);
            getMostRecentlyDeletedTask = t;
            listOfTasks.remove(index);
            return "Okay! I've deleted this task:\n  " + t.toString() + "\n"
                    + "You now have " + (numOfTasks - 1) + " task(s) in the list.";
        }
    }

    /**
     * Marks the task at the specified index from the list as done.
     *
     * @param index the index of the Task to be marked as done
     * @return the String to show to users
     */
    public String markTaskAsDone(int index) {
        int numOfTasks = listOfTasks.size();
        if (index >= numOfTasks) {
            return "No task of this number. Add new task or input a different number.";
        } else if (index < 0) {
            return "Input a task number from 1 - " + numOfTasks;
        } else {
            Task t = listOfTasks.get(index);
            t.markAsDone();
            return "Nice! I've marked this task as done:\n  " + t.toString();
        }
    }

    /**
     * Marks a Task as not yet done.
     *
     * @param index the index of the Task to be marked as undone
     * @return the String to show to users
     */
    public String markTaskAsUndone(int index) {
        Task t = listOfTasks.get(index);
        t.markAsUndone();
        return "Ok, this task has not yet been completed:\n  " + t.toString();
    }

    /**
     * Gets a list of matching tasks based on a given keyword.
     *
     * @param keyword keyword to filter out matching tasks
     * @return the list of tasks matching the given keyword
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.listOfTasks) {
            String name = t.getName();
            if (name.equals(keyword) || name.contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the most recently deleted task from the task list.
     *
     * @return the most recently deleted task
     */
    public Task getMostRecentlyDeletedTask() {
        return this.getMostRecentlyDeletedTask;
    }
}
