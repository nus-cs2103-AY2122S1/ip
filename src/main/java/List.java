import java.util.ArrayList;

/**
 * The List class stores the list of items. Items are stored in
 * an ArrayList of Strings. Supports adding of new items to the list
 * and printing of the entire current list.
 */
public class List {
    private ArrayList<Task> list;

    /**
     * Constructor for List class.
     */
    public List() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds an item to the list.
     *
     * @param description The description of the task to be added
     */
    public void addItem(String description) {
        Task task = new Task(description);
        this.list.add(task);
        System.out.println("added: " + description);
    }

    /**
     * Changes the status for the task indicated by the user.
     *
     * @param taskNumber The index(plus 1) of the task to be marked as done
     * @return The string representation of the task after it is marked as done
     */
    public String changeTaskStatus(int taskNumber) {
        Task task = list.get(taskNumber - 1);
        task.doneTask();
        return task.toString();
    }

    /**
     * List the items in the list in the order added, along with a counter.
     */
    public void listItems() {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i-1));
        }
    }
}
