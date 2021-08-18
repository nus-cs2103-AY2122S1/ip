/**
 * The items in the bot
 * responsible for adding things to the list of items
 */

public class Items {
    /**
     * items to be stored in the list.
     */
    private Task[] list;

    /**
     * the number of items in the list.
     */
    private int len;

    public Items() {
        list = new Task[100];
        len = 0;
    }

    /**
     * Add an item to the list.
     * @param item A string to represent the item added
     * @return A status message to be displayed
     */
    public String addItem(String item) {
        Task task = new Task(item);
        list[len++] = task;
        return "added " + item;
    }

    /**
     * marks the specified task as done
     * @param index the index at which the task is.
     * @return error message if index is greater than the length of list, else completion message.
     */
    public String markDone(int index) {
        if (index > len) {
            return "you don't have these many tasks!";
        }
        return list[index - 1].doneTask();
    }

    /**
     * The String representation of the items object.
     * @return The String representation of the items object.
     */
    public String printList() {
        if (len == 0) {
            return "You have 0 items in your list";
        }
        StringBuilder str = new StringBuilder("These are your tasks: \n");

        for (int i = 0; i < len; i++) {
            if (i < len - 1) {
                str.append(" ").append(i + 1).append(".").append(list[i].toString()).append("\n");
            } else {
                str.append(" ").append(i + 1).append(".").append(list[i].toString());
            }
        }
        return str.toString();
    }
}
