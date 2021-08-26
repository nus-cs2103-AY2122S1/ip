import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Task {
    protected String description;
    protected boolean status;

    private Task(String description) {
        this.description = description;
        this.status = false;
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

        public Todo(String description) {
            super(description);
        }

        @Override
        public String getTaskType() {
            return "[T]";
        }

    }

    protected static class Deadline extends Task{
        private LocalDate by;

        public Deadline(String description, String by) {
            super(description);
            this.by = LocalDate.parse(by, DateTimeFormatter.ISO_LOCAL_DATE);
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

    }

    protected static class Event extends Task{
        private LocalDate at;

        public Event(String description, String at) {
            super(description);
            this.at = LocalDate.parse(at, DateTimeFormatter.ISO_LOCAL_DATE);
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

    }
}
