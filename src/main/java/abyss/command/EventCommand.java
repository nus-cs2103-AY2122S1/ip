package abyss.command;

import abyss.exception.InvalidEventException;

/**
 * Represents a command to add an event to the list of tasks.
 */
public class EventCommand implements Command {
    private static final String EVENT_REGEX = "^\\S[ -~]*\\/at[ ]+\\S[ -~]*$";

    private String description;
    private String at;

    protected EventCommand(String content) throws InvalidEventException {
        if (!content.matches(EVENT_REGEX)) {
            throw new InvalidEventException();
        }
        String[] parts = content.split("\\/at[ ]+", 2);
        this.description = parts[0];
        this.at = parts[1];
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.at;
    }
}
