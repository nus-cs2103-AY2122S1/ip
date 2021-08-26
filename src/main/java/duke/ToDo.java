package duke;

/**
 * A ToDo task that extends from Task.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class ToDo extends Task{
    /**
     * A constructor for ToDo
     * @param description Takes in a user input
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * A method to get todo task info.
     * @return a string of the todo task details to be saved in data
     */
    @Override
    public String getTaskInfo() {
        return "T" + "|" + super.getZeroOrOne() + "|" + description;
    }

    /**
     * A method to get a String representation of todo task.
     * @return a string of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
