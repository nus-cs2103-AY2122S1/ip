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
        private String by;

        public Deadline(String description, boolean status, String by) {
            super(description, status);
            this.by = by;
        }

        @Override
        public String getTaskType() {
            return "[D]";
        }

        @Override
        public String getDescription() {
            return super.getDescription() + "(by:" + this.by + ")";
        }

        @Override
        public String toString() {
            return "deadline" + " | " + (this.status ? "1" : "0") + " | " + this.description + " | " + this.by;
        }

    }

    protected static class Event extends Task{
        private String at;

        public Event(String description, boolean status, String at) {
            super(description, status);
            this.at = at;
        }

        @Override
        public String getTaskType() {
            return "[E]";
        }

        @Override
        public String getDescription() {
            return super.getDescription() + "(at:" + this.at + ")";
        }

        @Override
        public String toString() {
            return "event" + " | " + (this.status ? "1" : "0") + " | " + this.description + " | " + this.at;
        }

    }
}
