package duke.item;

/**
 * Represents at item that is added to the task manager application.
 */
public abstract class Item {
    /**
     * Stores the item to the hard disk of the task manager application.
     *
     * @return String that represents the item and is stored in the hard disk.
     */
    public abstract String storeItem();
}
