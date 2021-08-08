/**
 * The items in the Duke app.
 * Responsible in adding things to the list of items.
 */

public class Items {

    /**
     * The items are stored in a list.
     */
    private String[] list;

    /**
     * The number of items in the list.
     */
    private int len;

    public Items() {
        list = new String[100];
        len = 0;
    }

    /**
     * Add an item to the list.
     * @param item A string to represent the item added
     * @return A status message to be displayed
     */
    public String addItem(String item) {
        list[len++] = item;
        return "added: " + item;
    }

    /**
     * The String representation of the Items object
     * @return The string representation of the Items object
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < len; ++i) {
            str += " " + (i + 1) + ". " + list[i] + "\n";
        }
        return len == 0 ? "" : str.substring(0, str.length() - 1);
    }
}
