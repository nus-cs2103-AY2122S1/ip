package duke;

import java.time.LocalDateTime;

public class Event extends Task{

    /**
     * Constructor to initialise the task.
     * @param task The task itself.
     * @param type The type of the task defined by the enum duke.Type.
     * @param done The status of the task.
     * @param datetime The time for task.
     */
    public Event(String task, Type type, Boolean done, LocalDateTime datetime) {
        super(task, type, done, datetime);
    }

    /**
     * String reflecting type of task.
     * @return String reflecting type of task.
     */
    public String typeString() {
        return "[E]";
    }

    /**
     * String reflecting task itself.
     * @return String reflecting task itself.
     */
    public String taskString() {
        return String.format("%s (%s: %s)", getTask(), Type.getConnector(getType()), getDatetimeString());
    }
}
