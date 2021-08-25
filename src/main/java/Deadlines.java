public class Deadlines extends Task {
    private final String date;

    public Deadlines(String input) {
        super(input.split(" /by ", 2)[0].substring(9));
        this.date = input.split(" /by ", 2)[1];
    }

    public Deadlines(boolean completed, String name, String deadline) {
        super(completed, name);
        this.date = deadline;
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("D~1~%s~%s", this.getName(), this.date);
        } else {
            return String.format("D~0~%s~%s", this.getName(), this.date);
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}
