package duke.task;

import duke.DateTimeFormatCreator;
import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class TimedTask extends Task {

    private final LocalDateTime dateTime;

    TimedTask(String name,
              String dateTime,
              TaskType taskType,
              boolean isCompleted) throws DukeException {
        super(name, taskType, isCompleted);
        try {
            this.dateTime = LocalDateTime.parse(dateTime.trim(), DateTimeFormatCreator.format());
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect form of date input. Please try yyyy-MM-dd HH:mm instead.");
        }
    }

    protected abstract String getDateTimeString();

    protected String getDateTimeInternal() {
        return this.dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " "
                + this.dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public String getDateTime() {
        return this.dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getDateTimeInternal();
    }

    @Override
    public String getDetails() {
        String checkbox = "[" + ( getCompleted() ? "X" : " ") + "]";
        String details = getTaskTypeString() + checkbox + " " + this.getName();
        return details + getDateTimeString();
    }
}
