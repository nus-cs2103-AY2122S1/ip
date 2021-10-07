package duke;

import duke.exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing the tasks that the user can create with duke.Duke.
 * Each duke.Task has a description, and is either done or not yet done.
 */
public class Task {
    private static final String DONE_STATUS_ICON = "X";
    private static final String NOT_DONE_STATUS_ICON = " ";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /** An enum describing the type of task. */
    public enum Type {
        TODO("T"), DEADLINE("D"), EVENT("E");

        private final String symbol;

        Type(String symbol) {
            this.symbol = symbol;
        }

        /**
         * Returns the symbol corresponding to the type of task.
         *
         * @return The symbol of the task.
         */
        public String getSymbol() {
            return this.symbol;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        /**
         * Method to parse a given String input and return a Type.
         * Used when parsing input from the user.
         *
         * @param input The given String input.
         * @return The Type matching the String input.
         */
        public static Type getTypeFromInput(String input) throws DukeException {
            for (Type type : Type.values()) {
                if (input.contentEquals(type.toString())) {
                    return type;
                }
            }
            throw new DukeException();
        }

        /**
         * Returns the Type represented by the symbol.
         * Used when reading from the file.
         *
         * @param symbol The symbol provided.
         * @return The represented Type.
         */
        public static Type getTypeFromSymbol(String symbol) throws ReadFileException {
            for (Type type : Type.values()) {
                if (symbol.equals(type.getSymbol())) {
                    return type;
                }
            }
            throw new ReadFileException();
        }
    }

    private final String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** A duke.Task without any date/time attached to it. */
    private static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        public ToDo(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        public String toString() {
            return "[" + Type.TODO.getSymbol() + "]" + super.toString();
        }
    }

    /** A type of task that needs to be done before a specific date/time. */
    private static class Deadline extends Task {
        private final LocalDate time;

        public Deadline(String description, String time) throws DukeException {
            super(description);
            try {
                this.time = LocalDate.parse(time, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }

        public Deadline(String description, boolean isDone, String time) throws DukeException {
            super(description, isDone);
            try {
                this.time = LocalDate.parse(time, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }

        @Override
        public String toString() {
            return "[" + Type.DEADLINE.getSymbol() + "]" + super.toString()
                    + " (by: " + DATE_TIME_FORMATTER.format(time) + ")";
        }
    }

    /** A type of duke.Task that starts at a specific time and ends at a specific date/time. */
    private static class Event extends Task {
        private final LocalDate time;

        public Event(String description, String time) throws DukeException {
            super(description);
            try {
                this.time = LocalDate.parse(time, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }

        public Event(String description, boolean isDone, String time) throws DukeException {
            super(description, isDone);
            try {
                this.time = LocalDate.parse(time, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }

        @Override
        public String toString() {
            return "[" + Type.EVENT.getSymbol() + "]" + super.toString()
                    + " (at: " + DATE_TIME_FORMATTER.format(time) + ")";
        }
    }

    /**
     * Parses the data from the file to return a duke.Task.
     *
     * @param str The String input read from the file.
     * @return The duke.Task whose data is stored in the file.
     * @throws duke.exception.DukeException The exception thrown when the file data is invalid.
     */
    public static Task readTaskFromFile(String str) throws DukeException {
        String regex = "^\\[([TDE])]\\[([X ])] (.+)$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(str);

        String taskSymbol;
        String statusIcon;
        String details;

        // check if file string matches the format
        if (m.find()) {
            taskSymbol = m.group(1);
            statusIcon =  m.group(2);
            details = m.group(3);
        } else {
            throw new ReadFileException();
        }

        assert details != null && !details.matches("^ *?$");

        boolean isDone;

        // parse task type
        Type type = Type.getTypeFromSymbol(taskSymbol);

        // parse status icon
        if (statusIcon.equals(DONE_STATUS_ICON)) {
            isDone = true;
        } else if (statusIcon.equals(NOT_DONE_STATUS_ICON)) {
            isDone = false;
        } else {
            throw new ReadFileException();
        }

        switch (type) {
            case TODO:
                return new ToDo(details, isDone);
            case DEADLINE:
                Pattern deadlinePattern =
                        Pattern.compile("(.+) \\(by: (\\d{1,2}-\\d{1,2}-\\d{4})\\)");
                Matcher deadlineMatcher = deadlinePattern.matcher(details);

                if (deadlineMatcher.find()) {
                    try {
                        return new Deadline(
                                deadlineMatcher.group(1), isDone, deadlineMatcher.group(2));
                    } catch (DateTimeParseException e) {
                        throw new ReadFileException();
                    }
                }
            case EVENT:
                Pattern eventPattern =
                        Pattern.compile("(.+) \\(at: (\\d{1,2}-\\d{1,2}-\\d{4})\\)");
                Matcher eventMatcher = eventPattern.matcher(details);

                if (eventMatcher.find()) {
                    try {
                        return new Event(
                                eventMatcher.group(1), isDone, eventMatcher.group(2));
                    } catch (DateTimeParseException e) {
                        throw new ReadFileException();
                    }
                }
            default:
                throw new ReadFileException();
        }
    }

    /**
     * Creates a task from the input provided by the user.
     *
     * @param str The input from the user.
     * @return A duke.Task made by the user.
     * @throws duke.exception.DukeException The exception thrown when input is invalid.
     */
    public static Task createTaskFromInput(String str) throws DukeException {

        Pattern pattern = Pattern.compile("^(todo|deadline|event)( (.*?))?$");
        Matcher m = pattern.matcher(str);

        Type type;
        boolean descIsEmpty;
        String details;

        if (m.find()) {
            type = Type.getTypeFromInput(m.group(1));

            String tmp = m.group(2);
            details = m.group(3);

            descIsEmpty = tmp == null || tmp.isEmpty() || tmp.matches("^ *?$")
                    || details == null;

            if (descIsEmpty) {
                throw new EmptyDescException(type);
            }
        } else {
            throw new DukeException();
        }

        switch (type) {
            case TODO:
                return new ToDo(details);
            case DEADLINE:
                Pattern p_deadline = Pattern.compile("^(.+) /by (.+)$");
                Matcher m_deadline = p_deadline.matcher(details);

                if (m_deadline.find()) {
                    return new Deadline(m_deadline.group(1), m_deadline.group(2));
                } else if (details.matches("^/by .*?$") || details.matches("^ *?$")) {
                    throw new EmptyDescException(type);
                } else {
                    throw new EmptyDateException(type);
                }
            case EVENT:
                Pattern p_event = Pattern.compile("^(.+) /at (.+)$");
                Matcher m_event = p_event.matcher(details);

                if (m_event.find()) {
                    return new Event(m_event.group(1), m_event.group(2));
                } else if (details.matches("^/at .*?$") || details.matches("^ *?$")) {
                    throw new EmptyDescException(type);
                } else {
                    throw new EmptyDateException(type);
                }
            default:
                throw new DukeException();
        }
    }

    /**
     * Returns the status icon indicated whether a task is done.
     *
     * @return A status icon showing whether a task is done.
     */
    private String getStatusIcon() {
        return (isDone ? DONE_STATUS_ICON : NOT_DONE_STATUS_ICON);
    }

    /** Marks the current task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}