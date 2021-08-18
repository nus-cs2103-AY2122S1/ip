public class Event extends Task {

    private String at;

    /**
     * A constructor for this event Task.
     *
     * @param description The description of what the task is.
     * @param at The specific start and end time that this task has to be done at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [E][Task status] Task Description (at: Task start time to end time)
     *
     * @return A string representation of this Task, which is the type of task (Event),
     *         its status, its description, and its start time to end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}

