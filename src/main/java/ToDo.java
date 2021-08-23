public class ToDo extends TaskItem {

    private boolean readFromFile = false;

    /**
     * Constructor for creating a ToDo object.
     * @param description the description of the ToDo/TaskItem.
     */
    public ToDo(String description) {
        super(description);
    }

//    public ToDo(String description, boolean readFromFile) {
//        super(description);
//        this.readFromFile = readFromFile;
//    }


    /**
     * Overriden toFileString method. Writes the task item into the dukeData file.
     * @return
     */

    @Override
    public String toFileString() {
        return "[T]" + super.toString();
    }

    /**
     * Overriden toString() method.
     * @return A String representation of a ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
