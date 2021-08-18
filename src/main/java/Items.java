import java.util.ArrayList;

/**
 * The items in the bot
 * responsible for adding things to the list of items
 */

public class Items {

    /**
     * items to be stored in the list.
     */
    private ArrayList<Task> list;

    public Items() {
        list = new ArrayList<>();
    }

    /**
     * Add an item to the list.
     * @param task A task to represent the item added
     * @return A status message to be displayed
     */
    public String addItem(Task task) {
        list.add(task);
        String output = "Got it, I've added this task:\n" + task.toString();
        output += "\nNow you have " + list.size() + " tasks in the list.";
        return output;
    }

    /**
     * marks the specified task as done
     * @param index the index at which the task is.
     * @return error message if index is greater than the length of list, else completion message.
     */
    public String markDone(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("Invalid index. Only positive values are accepted.");
        }
        if (list.size() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > list.size()) {
            throw new DukeException("You don't have these many tasks!");
        }
        Task task = list.get(index - 1);
        return task.doneTask();
    }

    public String deleteItem(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("Invalid index. Only positive values are accepted.");
        }
        if (list.size() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > list.size()) {
            throw new DukeException("You don't have these many tasks!");
        }
        Task task = list.get(index - 1);
        list.remove(index - 1);
        String output = "Noted. I have removed this task:\n" + task.toString()
                + "\n Now you have " + list.size() + " tasks remaining";
        return output;

    }

    /**
     * The String representation of the items object.
     * @return The String representation of the items object.
     */
    public String printList() throws DukeException {
        if (list.size() == 0) {
            throw new DukeException("You have 0 items in your list");
        }
        StringBuilder str = new StringBuilder("These are your tasks: \n");

        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                str.append(" ").append(i + 1).append(".").append(list.get(i).toString()).append("\n");
            } else {
                str.append(" ").append(i + 1).append(".").append(list.get(i).toString());
            }
        }
        return str.toString();
    }
}
