package abyss.command;

import abyss.exception.InvalidDeadlineException;

public class DeadlineCommand implements Command {
    private static final String DEADLINE_REGEX = "^\\S[ -~]*\\/by[ ]+\\S[ -~]*$";

    private String description;
    private String by;

    protected DeadlineCommand(String content) throws InvalidDeadlineException {
        if (!content.matches(DEADLINE_REGEX)) {
            throw new InvalidDeadlineException("Description and date of a 'deadline' task piece " +
                    "cannot be empty.");
        }
        String[] parts = content.split("\\/by[ ]+", 2);
        this.description = parts[0];
        this.by = parts[1];
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.by;
    }
}
