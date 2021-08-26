class Event extends Task {
    private String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String formatForSave() {
        return "E" + super.formatForSave() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}