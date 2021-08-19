

public abstract class Task {
    private boolean done;
    private final String taskName;
    private final String note;

    protected enum TaskKind {
        TODO("todo", "T"), DEADLINE("deadline", "D"), EVENT("event", "E");

        private String kind;
        private String shortName;
        TaskKind(String kind, String shortName) {
            this.kind = kind;
            this.shortName = shortName;
        }

        private String shortName() {
            return shortName;
        }

        @Override
        public String toString() {
            return kind;
        }
    }

    private Task(String bodyCommand) {
        String[] parts = bodyCommand.split(" ", 2);
        this.taskName = parts[0];
        if (parts.length > 1) {
            this.note = parts[1];
        } else {
            this.note = "";
        }
        this.done = false;
    }

    private static class Todo extends Task{
        private Todo(String bodyCommand) {
            super(bodyCommand);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.TODO;
        }
    }

    private static class Deadline extends Task{
        private Deadline(String bodyCommand) {
            super(bodyCommand);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.DEADLINE;
        }
    }

    private static class Event extends Task{
        private Event(String bodyCommand) {
            super(bodyCommand);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.EVENT;
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

    @Override
    public String toString() {
        String shortName = "[" + this.taskKind().shortName() + "]";
        String isDone = this.done ? "[X]" : "[ ]";
        return shortName + " " + isDone + " " + this.taskName;
    }
}
