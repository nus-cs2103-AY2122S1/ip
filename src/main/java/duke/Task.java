package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Task is a base class encapsulating a description and its completion status
 */
public abstract class Task {
    protected String desc;
    protected boolean isComplete;

    public Task(boolean isComplete, String desc) throws DukeException.EmptyTaskDescriptionException {
        this.desc = desc.trim();
        if (desc.trim().length() == 0) {
            throw new DukeException.EmptyTaskDescriptionException();
        }
        this.isComplete = isComplete;
    }

    public void markAsComplete() {
        this.isComplete = true;
    }

    public String getRepr() {
        return String.format("%d|%s", this.isComplete ? 1 : 0, this.desc);
    };

    public static Task fromRepr(String s) throws DukeException.InvalidReprException, DukeException.EmptyTaskDescriptionException {
        if (!s.matches("[TED][|][01][|].+[|].*")) {
            throw new DukeException.InvalidReprException(s);
        }
        String[] arr = s.split("[|]");
        char ch = arr[0].charAt(0);
        boolean isComplete = arr[1].charAt(0) == '1';
        String desc = arr[2];
        String extra = arr[3];
        Task t;
        switch (ch) {
        case 'E':
            t = new Event(isComplete, desc, extra);
            break;
        case 'D':
            t = new Deadline(isComplete, desc, extra);
            break;
        case 'T':
            t = new Todo(isComplete, desc);
            break;

        default:
            throw new DukeException.InvalidReprException(s); // shouldnt come here
        }
        return t;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.isComplete ? 'X' : ' ', this.desc);
    }

    public static class Event extends Task {
        String at;

        public Event(boolean isComplete, String desc, String at) throws DukeException.EmptyTaskDescriptionException {
            super(isComplete, desc);
            this.at = at;
        }

        @Override
        public String getRepr() {
            return String.format("E|%s|%s", super.getRepr(), this.at);
        }

        @Override
        public String toString() {
            return String.format("[E]%s (at: %s)", super.toString(), this.at);
        }
    }

    /**
     * Represents a task with a deadline, given in the form of YYYY-MM-DD
     */
    public static class Deadline extends Task {
        String by;
        Optional<LocalDate> date;
        public Deadline(boolean isComplete, String desc, String by) throws DukeException.EmptyTaskDescriptionException {
            super(isComplete, desc);
            try {
                this.date = Optional.of(LocalDate.parse(by));
            } catch (DateTimeParseException e) {
                this.by = by;
                this.date = Optional.empty();
            }
        }

        private String getDate(String pattern) {
            return this.date.map((date) -> date.format(DateTimeFormatter.ofPattern(pattern)))
                    .orElse(this.by);
        }

        public void snooze(int numberOfDays) throws DukeException.DateException {
            if (this.date.isPresent()) {
                this.date = this.date.map(localDate -> localDate.plusDays(numberOfDays));
            } else {
                throw new DukeException.DateException();
            }
        }

        @Override
        public String getRepr() {
            return String.format("D|%s|%s", super.getRepr(), this.getDate("yyyy-MM-dd"));
        }

        @Override
        public String toString() {
            return String.format("[D]%s (by: %s)", super.toString(), this.getDate("MMM dd yyyy"));
        }
    }

    public static class Todo extends Task {

        public Todo(boolean isComplete, String desc) throws DukeException.EmptyTaskDescriptionException {
            super(isComplete, desc);
        }

        @Override
        public String getRepr() {
            return String.format("T|%s|null", super.getRepr());
        }

        @Override
        public String toString() {
            return String.format("[T]%s", super.toString());
        }
    }
}