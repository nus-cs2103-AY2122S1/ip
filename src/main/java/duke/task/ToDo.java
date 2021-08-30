package duke.task;

/**
 * Represents to-dos (a type of tasks) that Duke can keep track of.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class ToDo extends Task {

    /** A string representing the description of a to-do. */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string that is used to represent to-dos when saved to a file.
     *
     * @return A string representation of a to-do for saving to files.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string that is used to represent to-dos when Duke is interacting with a user.
     *
     * @return A string representation of a to-do to be displayed to users.
     */
    @Override
    public String saveToFile() {
        return "T " + super.saveToFile();
    }
}
