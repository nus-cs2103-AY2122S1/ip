package item;

import exception.BotException;
import exception.EmptyCommandException;

public class Deadline extends Task {

    protected String by;

    private Deadline(String description, String by) throws BotException {
        super(description);
        this.by = by;
    }

    public static Deadline of(String description, String by) throws BotException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("deadline");
        } else {
            return new Deadline(description, by);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}