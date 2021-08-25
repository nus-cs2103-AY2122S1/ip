package saber.task;

import saber.SaberTime;
import saber.exceptions.SaberTimeParserException;

public class Deadline extends Task {

    protected SaberTime by;

    public Deadline(String description, String by, boolean isDone) throws SaberTimeParserException {
        super(description, isDone);
        this.by = new SaberTime(by);
    }

    public String getTimeInString() {
        return by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}