class Event extends Task {

    // Time which event happen at
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return string representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the string representation which is use to store the tasks.
     *
     * @return string representation used for storing task.
     */
    @Override
    public String toDataFormat() {
        return String.format("E | %s | %s | %s", isDone ? "1" : "0", description, at);
    }
}
