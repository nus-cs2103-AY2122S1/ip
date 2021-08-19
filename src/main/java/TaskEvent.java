public class TaskEvent extends Task {
    private String at;

    public TaskEvent(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * String representation of Event
     *
     * @return event display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[E]" + checkBox + description + " (at: " + at + ")";
    }

    String saveString() {
        return "E" + '\t' + (this.done ? "1" : "0") + '\t' + this.description + '\t' + this.at;
    }


}
