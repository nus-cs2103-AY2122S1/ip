package item;

import exception.BotException;
import exception.EmptyCommandException;

public class Event extends Task {
    protected String at;

    private Event(String description, String at) throws BotException {
        super(description);
        this.at = at;
    }

    public static Event of(String description, String at) throws BotException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("event");
        } else {
            return new Event(description, at);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
