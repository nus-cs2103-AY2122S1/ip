public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, String time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    public static Deadline fromInput(String input) throws Exception {
        String[] deadlineInputs = input.trim().split("\\s+/by\\s+", 2);

        if (deadlineInputs.length == 1) {
            if (deadlineInputs[0].length() == 0) {
                throw new Exception("Deadline must have description and time");
            } else {
                throw new Exception("Deadline must have time");
            }
        }

        if (deadlineInputs.length != 2) {
            throw new Exception("Deadline must have description and time");
        }

        String description = deadlineInputs[0];
        String time = deadlineInputs[1];

        return new Deadline(description, time);
    }

    @Override
    public String toString() {
        String time = this.time.length() > 0 ? (" (by: " + this.time + ")") : "";

        return "[D]" + super.toString() + time;
    }
}
