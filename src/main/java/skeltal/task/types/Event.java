package skeltal.task.types;

import skeltal.Parser;
import skeltal.SkeltalException;
import skeltal.task.Task;

/**
 * This class is a child of task that has the additional attribute of time,
 * to allow the storing of an Event timing.
 */
public class Event extends Task {
    private String time;

    /**
     * A constructor that initialises a Event object.
     * @param description A semi-processed string from the parser which contains
     *                The task and the time. e.g "Task /time".
     * @throws SkeltalException If the time description is not found.
     */
    public Event(String description) throws SkeltalException {
        super(description.split("/", 2)[0]);
        this.time = Parser.parseDescription(description, "event");
    }

    /**
     * A method that overrides the store() function in the Task parent class,
     * to include the task type and time description of the Event object.
     *
     * @return A String representation of the Event object that is readable by the loader.
     */
    @Override
    public String store() {
        return "E | " + super.store() + "| " + time;
    }

    /** Returns a String representation of the Event object, for printing purposes.
     * Eg "[E][ ] Task (time)".
     * @return A string representation of the Event object for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + formatTime();
    }

    private String formatTime() {
        return "(at: " + this.time + ")";
    }
}
