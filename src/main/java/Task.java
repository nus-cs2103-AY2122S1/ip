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
        private String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String getTaskType() {
            return "[D]";
        }

        @Override
        public String getDescription() {
            return super.getDescription() + "(by: " + this.by + ")";
        }

    }

    protected static class Event extends Task{
        private String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String getTaskType() {
            return "[E]";
        }

        @Override
        public String getDescription() {
            return super.getDescription() + "(at: " + this.at + ")";
        }

    }
}
