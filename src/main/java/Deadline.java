public class Deadline extends Task {
    private final String dueDate;
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String INVALID_DEADLINE_MESSAGE = "Invalid use of deadline command. Use 'deadline <text> /by <datetime>'";
    private static final String MISSING_DEADLINE_MESSAGE = "Some arguments are missing. Use 'deadline <text> /by <datetime>'";
    private static final String INVALID_SAVE_MESSAGE = "Deadline save is given in the wrong format";
    public static final char SYMBOL = 'D';

    private Deadline(String text, String dueDate, boolean isDone) {
        super(text, isDone);
        this.dueDate = dueDate;
    }

    public static Deadline newDeadline(String input, boolean isDone) throws DukeException {
        if (input.split(" ").length < 3) {
            throw new DukeException(MISSING_DEADLINE_MESSAGE);
        }

        String[] deadlineInfo = input.split(DEADLINE_DELIMITER);
        if (deadlineInfo.length < 2) {
            throw new DukeException(INVALID_DEADLINE_MESSAGE);
        }
        String deadline = deadlineInfo[1].trim();
        String deadlineText = deadlineInfo[0].trim();
        return new Deadline(deadlineText, deadline, false);
    }

    public static Deadline newDeadlineFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 3) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String isDone = inputArr[0].trim();
        String deadlineText = inputArr[1].trim();
        String deadline = inputArr[2].trim();
        return new Deadline(deadlineText, deadline, isDone.equals("1"));
    }

    public String getSaveFormat(){
        return String.format("%c | %d | %s | %s", SYMBOL, super.getDoneInt(), this.getText(), this.dueDate);
    };

    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)", SYMBOL, super.toString(), this.dueDate);
    }
}
