package duke;

import java.util.ArrayList;

/**
 * The items in the Duke app.
 * Responsible in adding things to the list of items.
 */

public class Items {

    /**
     * The items are stored in a list.
     */
    private ArrayList<Task> list;

    /**
     * Constructor for Items
     */
    public Items() {
        list = new ArrayList<>();
    }

    /**
     * Constructor for Items
     * @param tasks An ArrayList of Tasks
     */
    public  Items(ArrayList<Task> tasks) {
        list = tasks;
    }

    /**
     * Add an item to the list.
     * @param task A Task to be added to the list
     * @return A status message to be displayed
     */
    public String addItem(Task task) throws DukeException {
        list.add(task);
        String res = "Got it. I've added this task: \n" + "  " + task.toString() + "\n";
        res += ("Now you have " + list.size() + " tasks in the list");
        return res;
    }

    /**
     * Marks the task at the given index as done
     * @param index The index to be marked as done
     * @return  A status message to be displayed
     * @throws DukeException
     */
    public String markDone(int index) throws DukeException {
        index = index - 1;
        if (index < 0 || index >= list.size()) {
            if (list.size() == 0) {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString() + " Task list is empty.");
            } else if (list.size() == 1) {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString() + " Only 1 item in the list.");
            } else {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString() + " Input a number from [1..." + list.size() + "].");
            }
        }
        Task task = list.get(index);
        task.markDone();
        return "Great success! Task Complete: \n" + "  " + task.toString();
    }

    /**
     * Removes a task in the list
     * @param index The index of the task to be removed
     * @return A status message to be displayed
     * @throws DukeException
     */
    public String removeItem(int index) throws DukeException {
        index = index - 1;
        if (index < 0 || index >= list.size()) {
            if (list.size() == 0) {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString() + " Task list is empty.");
            } else if (list.size() == 1) {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString() + " Only 1 item in the list.");
            } else {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString() + " Input a number from [1..." + list.size() + "].");
            }
        }
        Task task = list.get(index);
        list.remove(index);
        String res = "Got it. I've removed this task: \n" + "  " + task.toString() + "\n";
        res += ("Now you have " + list.size() + " tasks in the list");
        return res;
    }

    /**
     * Returns all the task (represented by a string) that contains the given keyword
     * @param keyword The keyword to search the task
     * @return all the tasks (string) that contains the given keyword
     */
    public String find(String keyword) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        int id = 1;
        for (Task task : list) {
            if (task.toString().contains(keyword)) {
                str.append(id++).append(". ").append(task.toString()).append("\n");
            }
        }
        return str.toString();
    }

    /**
     * The String representation of the Items object
     * @return The string representation of the Items object
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Task task : list) {
            str.append(task.toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Return the message of the status of the Task List
     * @return the status message of the Task List
     */
    public String getListMessage() {
        StringBuilder str = new StringBuilder("This your task in list:\n");
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            str.append(" ").append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }
        return size == 0 ? "You currently have nothing in your list" : str.substring(0, str.length() - 1);
    }
}
