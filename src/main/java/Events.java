public class Events extends Task {
    private final String date;

    public Events(String input) {
        super(input.split(" /at ", 2)[0].substring(6));
        this.date = input.split(" /at ", 2)[1];
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
