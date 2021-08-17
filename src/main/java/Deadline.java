import java.util.Scanner;

/**
 * A class to create task with the type of Deadline.
 *
 * @author Felissa Faustine
 */
public class Deadline extends Task {
    public String time;

    /**
     * This is the constructor of the Deadline task.
     *
     * @param taskName The name of the task.
     * @param time The deadline.
     */
    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Gets the symbol of deadline.
     *
     * @return The string representation of the deadline symbol.
     */
    public String getSymbol() {
        return "[D]";
    }

    /**
     * Gets the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return getSymbol() + super.toString() + "(by:" + this.time + ")";
    }
}
