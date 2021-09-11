package duke.task;

/**
 * The Event class encapsulates an event that happens at a certain time.
 */
public class Event extends TimeTask {

    public static final String COMMAND_WORD = "event";
    public static final String TASK_INTIAL = "E";
    public static final String TIME_PREFIX = "at";
    public static final String KEYWORD = "/at";
    public static final String KEYWORD_WITH_SPACE = KEYWORD + " ";


    /**
     * Constructor for an Event task.
     *
     * @param name the given name of the Event.
     * @param time the given time of the Event.
     */
    public Event(String name, String time) {
        super(name, time);
    }

    /**
     * Returns a String representing the Event Task.
     *
     * @return a String representing the Event Task.
     */
    @Override
    public String toString() {
        return generateToString(TASK_INTIAL, TIME_PREFIX);
    }

    /**
     * Returns a String representing the Event Task to be saved in the taskList.txt file.
     *
     * @return a String representing the Event Task to be saved in the taskList.txt file.
     */
    public String printToFile() {
        return generatePrintToFile(TASK_INTIAL);
    }
}
