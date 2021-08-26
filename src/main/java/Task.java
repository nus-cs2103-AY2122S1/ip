import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Task {
    protected String description;
    protected boolean status;

    private Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public String getStatusIcon() {
        return (status ? "[X]" : "[ ]");
    }

    public String getTaskType() {
        return "[ ]";
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean status) {
        this.status= status;
    }

    protected static class Todo extends Task{

        public Todo(String description, boolean status) {
            super(description, status);
        }

        @Override
        public String getTaskType() {
            return "[T]";
        }

        @Override
        public String toString() {
            return "todo" + " | " + (this.status ? "1" : "0") + " | " + this.description;
        }

    }

    protected static class Deadline extends Task{
        private LocalDate by;

<<<<<<< HEAD
        public Deadline(String description, boolean status, String by) {
            super(description, status);
            this.by = by;
=======
        public Deadline(String description, String by) {
            super(description);
            this.by = LocalDate.parse(by, DateTimeFormatter.ISO_LOCAL_DATE);
>>>>>>> branch-Level-8
        }

        @Override
        public String getTaskType() {
            return "[D]";
        }

        @Override
        public String getDescription() {
            return super.getDescription() + "(by: " +
                    this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
        }

        @Override
        public String toString() {
            return "deadline" + " | " + (this.status ? "1" : "0") + " | " + this.description + " | " + this.by;
        }

    }

    protected static class Event extends Task{
        private LocalDate at;

<<<<<<< HEAD
        public Event(String description, boolean status, String at) {
            super(description, status);
            this.at = at;
=======
        public Event(String description, String at) {
            super(description);
            this.at = LocalDate.parse(at, DateTimeFormatter.ISO_LOCAL_DATE);
>>>>>>> branch-Level-8
        }

        @Override
        public String getTaskType() {
            return "[E]";
        }

        @Override
        public String getDescription() {
            return super.getDescription() + "(at: " +
                    this.at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
        }

        @Override
        public String toString() {
            return "event" + " | " + (this.status ? "1" : "0") + " | " + this.description + " | " + this.at;
        }

    }
}
