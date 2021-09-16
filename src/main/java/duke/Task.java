package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Task class that includes Todos, Events, Deadline and Within.
 */
public class Task {
    protected String description;
    protected boolean isComplete;

    /**
     * Constructs generic Task object
     *
     * @param description Description of task
     * @param isComplete Completion status of Task
     */
    private Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Returns the corresponding status icon
     *
     * @return Corresponding status icon
     */
    public String getStatusIcon() {
        return (isComplete ? "[X]" : "[ ]");
    }

    public String getTaskType() {
        return "[ ]";
    }

    public String getDescription() {
        return this.description;
    }

    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }

    public static class Todo extends Task {
        /**
         * Constructs Todo task
         *
         * @param description Description for the task
         * @param status Completion status of task
         */
        public Todo(String description, boolean status) {
            super(description, status);
        }

        @Override
        public String getTaskType() {
            return "[T]";
        }

        /**
         * Formats task information into String format to be saved onto storage
         *
         * @return String containing task info
         */
        @Override
        public String toString() {
            return "todo" + " | " + (this.isComplete ? "1" : "0") + " | " + this.description;
        }

    }

    public static class Deadline extends Task {
        private LocalDate by;


        /**
         * Constructs Deadline task
         *
         * @param description Description for the task
         * @param status Completion status of task
         * @param by Deadline of task in YYYY-MM-DD format
         */
        public Deadline(String description, boolean status, String by) {
            super(description, status);
            this.by = LocalDate.parse(by, DateTimeFormatter.ISO_LOCAL_DATE);
        }

        @Override
        public String getTaskType() {
            return "[D]";
        }

        /**
         * Returns description of task including deadline
         *
         * @return Description of task including deadline
         */
        @Override
        public String getDescription() {
            return super.getDescription() + "(by: "
                    + this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
        }

        /**
         * Formats task information into String format to be saved onto storage
         *
         * @return String containing task info
         */
        @Override
        public String toString() {
            return "deadline" + " | " + (this.isComplete ? "1" : "0") + " | " + this.description + " | " + this.by;
        }

    }

    public static class Event extends Task {
        private LocalDate at;

        /**
         * Constructs Event task
         *
         * @param description Description for the task
         * @param status Completion status of task
         * @param at Deadline of task in YYYY-MM-DD format
         */
        public Event(String description, boolean status, String at) {
            super(description, status);
            this.at = LocalDate.parse(at, DateTimeFormatter.ISO_LOCAL_DATE);
        }

        @Override
        public String getTaskType() {
            return "[E]";
        }

        /**
         * Returns description of task including date
         *
         * @return Description of task including date
         */
        @Override
        public String getDescription() {
            return super.getDescription() + "(at: "
                    + this.at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
        }

        /**
         * Formats task information into String format to be saved onto storage
         *
         * @return String containing task info
         */
        @Override
        public String toString() {
            return "event" + " | " + (this.isComplete ? "1" : "0") + " | " + this.description + " | " + this.at;
        }

    }

    public static class Within extends Task {
        private LocalDate firstDate;
        private LocalDate secondDate;

        /**
         * Constructs Within task
         *
         * @param description Description for the task
         * @param status Completion status of task
         * @param firstDate Starting timeframe of task in YYYY-MM-DD format
         * @param secondDate Ending timeframe of task in YYYY-MM-DD format
         * @throws DukeException When date format is incorrect
         */
        public Within(String description, boolean status, String firstDate, String secondDate) throws DukeException {
            super(description, status);
            try {
                this.firstDate = LocalDate.parse(firstDate, DateTimeFormatter.ISO_LOCAL_DATE);
                this.secondDate = LocalDate.parse(secondDate, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter date in format YYYY-MM-DD");
            }
        }

        @Override
        public String getTaskType() {
            return "[W]";
        }

        /**
         * Returns description of task including date
         *
         * @return Description of task including date
         */
        @Override
        public String getDescription() {
            return super.getDescription() + "(between "
                    + this.firstDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
                    + " and "
                    + this.secondDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
        }

        /**
         * Formats task information into String format to be saved onto storage
         *
         * @return String containing task info
         */
        @Override
        public String toString() {
            return "within" + " | " + (this.isComplete ? "1" : "0") + " | " + this.description + " | "
                    + this.firstDate + " | " + this.secondDate;
        }

    }
}
