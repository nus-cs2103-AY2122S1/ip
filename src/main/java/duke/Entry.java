package duke;

public abstract class Entry {
    private final String ENTRY;

    private boolean isDone;

    /**
     * Constructor for Entry.
     */
    Entry() {
        this.ENTRY = "";
        this.isDone = false;
    }

    /**
     * Constructor for Entry with a value.
     *
     * @param value Entry value.
     */
    Entry(String value) {
        this.ENTRY = value;
        this.isDone = false;
    }

    /**
     * Method to revert the isDone status of the Entry.
     */
    public void revertDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Method to set the isDone status to true of an Entry.
     *
     * @return Boolean if successful operation.
     */
    public boolean setDone() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    /**
     * Returns true if Entry is Empty.
     *
     * @return Boolean corresponding to Entry's length.
     */
    public boolean isEmpty() {
        return this.ENTRY.length() < 1;
    }

    /**
     * Returns the string to be saved representing the Entry.
     *
     * @return String to represent entry in memory.
     */
    public String saveString() {
        String isDoneString = "0";
        if (this.isDone) {
            isDoneString = "1";
        }
        return "," + isDoneString + "," + this.ENTRY;
    }

    /**
     * Overrides Object's toString method to return a description of Entry.
     *
     * @return String to represent Entry.
     */
    @Override
    public String toString() {
        char isDoneDisplay = this.isDone ? 'X' : ' ';
        return ("[" + isDoneDisplay + "] " + this.ENTRY);
    }
}