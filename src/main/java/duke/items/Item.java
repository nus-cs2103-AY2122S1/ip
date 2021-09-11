package duke.items;

/**
 * Represents an abstract representation of entries in a to-do list.
 * Each entry has a name and a flag for whether it is done.
 */
public abstract class Item {
    private String name;
    private String tag = null;
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
        return "name&&&" + this.name + (this.tag == null ? "" : "###tag&&&" + this.tag);
    }
    
    /**
     * Returns whether the name contains a substring pattern.
     * 
     * @param string Substring.
     * @return The result.
     */
    public boolean queryString(String string) {
        return this.name.contains(string);
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[] ") + this.name + (this.tag == null ? "" : " (tag: " + this.tag + ")");
    }

    public void addTag(String tag) {
        this.tag = tag;
    }
}
