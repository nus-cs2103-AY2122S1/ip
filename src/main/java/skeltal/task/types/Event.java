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

    private Event(String description) {
        super(description);
    }

    /**
     * A factory method that initialises a Event object.
     *
     * @param taskAndTime A semi-processed string from the parser which contains
     *                    The task and the time. e.g "Task /time".
     * @returns An Event object.
     * @throws SkeltalException
     */
    public static Event of(String taskAndTime) throws SkeltalException {
        String[] taskTimeArr = Parser.parseDescription(taskAndTime, "event");
        String task = taskTimeArr[0];
        String time = taskTimeArr[1];
        Event event = new Event(task);
        event.setTime(time);
        return event;
    }

    private void setTime(String time) {
        this.time = time;
    }

    /**
     * A method that overrides the store() function in the Task parent class,
     * to include the task type and time description of the Event object.
     *
     * @return A String representation of the Event object that is readable by the loader.
     */
    @Override
    public String store() {
        return "E | " + super.store() + " | " + time;
    }

    /**
     * Returns a String representation of the Event object, for printing purposes.
     * Eg "[E][ ] Task (time)".
     *
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
