import java.util.ArrayList;

/**
 * The List class stores the list of items. Items are stored in
 * an ArrayList of Strings. Supports adding of new items to the list
 * and printing of the entire current list.
 */
public class List {
    private ArrayList<String> list;

    /**
     * Constructor for List class.
     */
    public List() {
        this.list = new ArrayList<String>();
    }

    /**
     * Adds an item to the list.
     *
     * @param message The message to be added to the list
     */
    public void addItem(String message) {
        this.list.add(message);
        System.out.println("added: " + message);
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
