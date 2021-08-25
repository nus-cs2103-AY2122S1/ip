package saber.task;

import saber.SaberTime;
import saber.exceptions.SaberTimeParserException;

public class Event extends Task {

    protected SaberTime at;

    public Event(String description, String at, boolean isDone) throws SaberTimeParserException {
        super(description, isDone);
        this.at = new SaberTime(at);
    }

    public String getTimeInString() {
        return at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}