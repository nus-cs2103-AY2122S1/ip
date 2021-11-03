package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task
 */
public class Task {

    public static final String DONE_MARKER = "X";
    public static final String NOT_DONE_MARKER = " ";

    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static String[] splitDescriptionAndDate(String args, String separator) throws DukeException {
        String regex = String.format("(?<desc>.*)\\s\\(%s: (?<date>.*)\\)", separator);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);
        if (!matcher.matches()) {
            throw new DukeException("There was a problem loading your saved tasks!");
        }

        String type = matcher.group("desc");
        String desc = matcher.group("date");

        String[] desc_date = {type, desc};

        return desc_date;
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /** Sets isDone to given boolean */
    public void setIsDone(boolean b) {
        isDone = b;
    }

    @Override
    public String toString() {
        String marker;
        if (isDone) {
            marker = DONE_MARKER;
        } else {
            marker = NOT_DONE_MARKER;
        }
        return String.format("[%s] %s", marker, description);
    }
}