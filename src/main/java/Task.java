public abstract class Task {
    private boolean done;
    private String taskName;

    private Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    private static class Todo extends Task{
        private Todo(String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            String isDone = "[T]" + (super.done ? "[X]" : "[ ]");
            return isDone + " " + super.taskName;
        }
    }

    private static class Deadline extends Task{
        private Deadline(String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            String isDone = "[D]" + (super.done ? "[X]" : "[ ]");
            return isDone + " " + super.taskName;
        }
    }

    private static class Event extends Task{
        private Event(String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            String isDone = "[E]" + (super.done ? "[X]" : "[ ]");
            return isDone + " " + super.taskName;
        }
    }

    protected static Task todo(String s) {
        Task t = new Todo(s);
        return t;
    }

    protected static Task deadline(String s) {
        Task t = new Deadline(s);
        return t;
    }

    protected static Task event(String s) {
        Task t = new Event(s);
        return t;
    }

    public void done() {
        this.done = true;
    }

    public String getTaskName() {
        return taskName;
    }
}
