package duke.items;

/**
 * Represents an abstract representation of entries in a to-do list.
 * Each entry has a name and a flag for whether it is done.
 */
public abstract class Item {
    private String name;
    private boolean isDone = false;

    public Item(String name) {
        this.name = name;
    }

    /**
     * Marks this object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of whether this item is done.
     * 
     * @return String encoding whether this item is done.
     */
    public String getDone() {
        return (isDone ? "X" : "O");
    }

    /**
     * Returns a String encoding of the object for storage in database.
     * 
     * @return The encoding.
     */
    public String getPickle() {
        return this.name;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.name;
    }
}
