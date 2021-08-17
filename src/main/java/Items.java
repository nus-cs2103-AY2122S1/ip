/**
 * The items in the Duke app.
 * Responsible in adding things to the list of items.
 */

public class Items {

    /**
     * The items are stored in a list.
     */
    private Task[] list;

    /**
     * The number of items in the list.
     */
    private int len;

    public Items() {
        list = new Task[100];
        len = 0;
    }

    /**
     * Add an item to the list.
     * @param task A Task to be added to the list
     * @return A status message to be displayed
     */
    public String addItem(Task task) throws DukeException {
        if (len >= 100) {
            throw new DukeException("Your task list is full!");
        }
        list[len++] = task;
        String res = "Got it. I've added this task: \n" + "  " + task.toString() + "\n";
        res += ("Now you have " + len + " tasks in the list");
        return res;
    }

    /**
     * Marks the task at the given index as done
     * @param index The index to be marked as done
     * @return  A status message to be displayed
     */
    public String markDone(int index) throws DukeException {
        index = index - 1;
        if (index < 0 || index >= len) {
            if (len == 0) {
                throw new DukeException("No task found, please add task");
            } else {
                throw new DukeException("No task found, input a number from [1..." + len + "]");
            }
        }
        Task task = list[index];
        task.markDone();
        return "Great success! Task Complete: \n" + "  " + task.toString();

    }

    /**
     * The String representation of the Items object
     * @return The string representation of the Items object
     */
    public String toString() {
        String str = "This your task in list:\n";
        for (int i = 0; i < len; ++i) {
            str += " " + (i + 1) + ". " + list[i].toString() + "\n";
        }
        return len == 0 ? "You currently have nothing in your list" : str.substring(0, str.length() - 1);
    }
}
