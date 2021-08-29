package duke.task;

import java.util.Arrays;
import java.util.List;

import duke.parser.Parser;
import duke.storage.Storage;


/**
 * This class encapsulates an event task.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";
    private String at;

    /**
     * Constructor of the duke.task.Event class
     *
     * @param description description of this event
     * @param at the time period of this event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of this event
     *
     * @return string representation of this event
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Returns the savable string format of this task.
     *
     * @return Formatted string to be saved into storage.
     */
    @Override
    public String toSavableFormat() {
        String isDone = Parser.parseIsDoneToString(this.isDone());
        List<String> stringList = Arrays.asList(TASK_TYPE, isDone, this.getDescription(), this.at);
        return String.join(Storage.DELIMITER, stringList);
    }

    @Override
    protected String getTaskType() {
        return TASK_TYPE;
    }
}
