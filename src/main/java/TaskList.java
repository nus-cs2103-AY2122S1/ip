import java.util.ArrayList;

/**
 * This class represents a TaskList, which is the list of tasks that Duke refers to to carry out commands provided
 * to him.
 */

public class TaskList {
    private ArrayList<String> list;

    public TaskList() {
        this.list = new ArrayList<String>();
    }

    /**
     * Adds an item to the TaskList
     *
     * @param item the item to be added to the TaskList
     */
    public void add(String item) {
        this.list.add(item);
        System.out.println("    " + "added: " + item);
    }

    /**
     * Lists out the current items in the TaskList
     */
    public void listOut() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + this.list.get(i));
        }
    }
}
