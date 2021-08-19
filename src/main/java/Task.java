

public abstract class Task {
    private boolean done;
    private final String taskName;

    protected enum TaskKind {
        TODO("todo"), DEADLINE("deadline"), EVENT("event");

        private String kind;
        TaskKind(String kind) {
            this.kind = kind;
        }

        @Override
        public String toString() {
            return kind;
        }
    }

    private Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    private static class Todo extends Task{
        private Todo(String taskName) {
            super(taskName);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.TODO;
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
        public TaskKind taskKind() {
            return TaskKind.DEADLINE;
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
        public TaskKind taskKind() {
            return TaskKind.EVENT;
        }

        @Override
        public String toString() {
            String isDone = "[E]" + (super.done ? "[X]" : "[ ]");
            return isDone + " " + super.taskName;
        }
    }

    protected static Task todo(String s) throws DukeException.DukeEmptyTask{
        Task t = new Todo(s);
        if (s != Command.NULL_COMMAND) {
            return t;
        } else {
            throw new DukeException.DukeEmptyTask(t);
        }
    }

    protected static Task deadline(String s) throws DukeException.DukeEmptyTask{
        Task t = new Deadline(s);
        if (s != Command.NULL_COMMAND) {
            return t;
        } else {
            throw new DukeException.DukeEmptyTask(t);
        }
    }

    protected static Task event(String s) throws DukeException.DukeEmptyTask{
        Task t = new Event(s);
        if (s != Command.NULL_COMMAND) {
            return t;
        } else {
            throw new DukeException.DukeEmptyTask(t);
        }
    }

    public void done() {
        this.done = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public abstract TaskKind taskKind();
}
