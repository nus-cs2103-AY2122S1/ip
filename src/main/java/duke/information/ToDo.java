package duke.information;

/**
 * Class that stores a task.
 */
public class ToDo extends Task {

    /**
     * Constructs ToDo class.
     *
     * @param description ToDo description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the task's information into a string.
     * To be stored in the user's dedicated txt file.
     *
     * @return String of the task information.
     */
    @Override
    public String toData() {
        return "T|" + super.toData() + "\n";
    }

    /**
     * Converts the task's information into a string.
     * To be used to display the task information to the user.
     *
     * @return String of the task information.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}