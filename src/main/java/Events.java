public class Events extends Task {
    private final String date;

    public Events(String name, String date) {
        super(name);
        this.date = date;
    }

    public Events(boolean completed, String name, String deadline) {
        super(completed, name);
        this.date = deadline;
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("E~1~%s~%s", this.getName(), this.date);
        } else {
            return String.format("E~0~%s~%s", this.getName(), this.date);
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
