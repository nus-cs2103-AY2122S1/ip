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

    public Items() {
        list = new ArrayList<>();
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
     */
    public String markDone(int index) throws DukeException {
        index = index - 1;
        if (index < 0 || index >= list.size()) {
            if (list.size() == 0) {
                throw new DukeException("No task found, please add task");
            } else {
                throw new DukeException("No task found, input a number from [1..." + list.size() + "]");
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
                throw new DukeException("No task found, please add task");
            } else {
                throw new DukeException("No task found, input a number from [1..." + list.size() + "]");
            }
        }
        Task task = list.get(index);
        list.remove(index);
        String res = "Got it. I've removed this task: \n" + "  " + task.toString() + "\n";
        res += ("Now you have " + list.size() + " tasks in the list");
        return res;
    }

    /**
     * The String representation of the Items object
     * @return The string representation of the Items object
     */
    public String toString() {
        String str = "This your task in list:\n";
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            str += " " + (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return size == 0 ? "You currently have nothing in your list" : str.substring(0, str.length() - 1);
    }
}
