package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * The class to represent a list of tasks the user has.
 */
public class TaskList {

    /** the list of tasks */
    private ArrayList<Task> listOfTasks;

    /**
     * The constructor of the TaskList class
     *
     * @param tasks the list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.listOfTasks = tasks;
    }

    /**
     * The constructor of the TaskList class
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    /**
     * The method to return the list of tasks
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * The method to print all tasks to console
     * @param tasks
     */
    public String showAllTasks(TaskList tasks) {
        if (listOfTasks.isEmpty()) {
            return "You currently have no tasks! Add one now ☻";
        } else {
            return "Here are the tasks in your list:\n" + showTasks(tasks);
        }
    }

    public String showMatchingTasks(TaskList listOfTasks) {
        if (listOfTasks.isEmpty()) {
            return "No matching tasks found! ☹";
        } else {
            return "Here are the tasks that match this keyword:\n" + showTasks(listOfTasks);
        }
    }

    public String showTasks(TaskList listOfTasks) {
        String output = "";
        int size = listOfTasks.size();
        for (int i = 0; i < size; i++) {
            Task t = listOfTasks.get(i);
            output = output + (i + 1) + "." + t.toString() + "\n";
        }
        return output;
    }


    /**
     * Method to determine if the list of tasks is empty
     *
     * @return whether the list of tasks is empty
     */
    public boolean isEmpty() {
        return this.listOfTasks.isEmpty();
    }

    /**
     * Method to determine the number of elements in the list of tasks
     *
     * @return the number of elements in the list of tasks
     */
    public int size() {
        return this.listOfTasks.size();
    }

    /**
     * Method to return a task at a specific index in the list
     *
     * @return the task at the specific index in the list
     */
    public Task get(int i) {
        return this.listOfTasks.get(i);
    }

    /**
     * The method to add a Task to the list
     *
     * @param t the Task to be added
     */
    public String addTask(Task t) {
        listOfTasks.add(t);
        return "Okay! Task added:\n  " + t.toString() + "\n" +
            "You now have " + listOfTasks.size() + " task(s) in the list.";
    }

    /**
     * The method to delete a Task from the list
     *
     * @param index the index of the Task to be deleted
     */
    public String deleteTask(int index) {
        int numOfTasks = listOfTasks.size();
        if (index >= numOfTasks) {
            return "No task of this number. Add new task or input a different number.";
        } else if (index < 0) {
            return "Input a task number from 1 - " + numOfTasks;
        } else {
            Task t = listOfTasks.get(index);
            listOfTasks.remove(index);
            return "Ok! I've deleted this task:\n  " + t.toString() + "\n" +
                "You now have " + (numOfTasks - 1) + " task(s) in the list.";
        }
    }

    /**
     * The method to mark a Task from the list as done
     *
     * @param index the index of the Task to be marked as done
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
     * The method to get a list of matching tasks based on a given keyword.
     *
     * @param keyword keyword to filter out matching tasks
     * @return the list of tasks matching the given keyword
     */
    public TaskList findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.listOfTasks) {
            String name = t.getName();
            if (name.equals(keyword) || name.contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return new TaskList(matchingTasks);
    }
}
