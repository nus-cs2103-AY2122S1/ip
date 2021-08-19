public class Deadline extends Task {
    private final String dueDate;
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String INVALID_DEADLINE_MESSAGE = "Invalid use of deadline command. Use 'deadline <text> /by <datetime>'";

    private Deadline(String text, String dueDate) {
        super(text);
        this.dueDate = dueDate;
    }

    public static Deadline newDeadline(String input) throws DukeException {
        String[] deadlineInfo = input.split(DEADLINE_DELIMITER);
        if (deadlineInfo.length < 2) {
            throw new DukeException(INVALID_DEADLINE_MESSAGE);
        }
        String deadline = deadlineInfo[1].trim();
        String deadlineText = deadlineInfo[0].trim();
        return new Deadline(deadline, deadlineText);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }
}
