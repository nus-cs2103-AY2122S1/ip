package duke.task;

import duke.exception.DukeException;

/** Event class that inherits from Task */
public class Event extends Task {

    /**
     * Constructor for an Event
     * @param description Description of the event
     * @param date Date of the event
     * @param done Whether the event is done
     * @throws DukeException When the event cannot be created
     */
    public Event(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    /**
     * @return String of the event data for saving in tasks.txt
     */
    @Override
    public String toFileData() {
        return String.join(Task.STORAGE_DELIMITER, Task.EVENT_ALPHABET, super.toFileData(), super.dateToString());
    }

    /**
     * @return String representation of the Event
     */
    @Override
    public String toString() {
        return String.format("%s%s (at: %s)",
                super.wrapTaskAlphabet(Task.EVENT_ALPHABET),
                super.toString(),
                super.getDateString());
    }
}
