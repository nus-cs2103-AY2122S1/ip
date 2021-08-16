import java.util.Scanner;

/**
 * A class to create tasks with the type of event.
 */
public class Event extends Task {
    public String time;

    /**
     * The constructor of the Event class.
     *
     * @param taskName The name of the task.
     * @param time The time of the event.
     */
    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Gets the symbol of event.
     *
     * @return The string representation of the symbol of event.
     */
    public String getSymbol() {
        return "[E]";
    }

    /**
     * Gets the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return getSymbol() + " " + super.toString() + "(at:" + this.time + ")";
    }
}
