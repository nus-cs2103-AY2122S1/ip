import java.io.IOException;

public abstract class Task {
    private boolean isDone;
    private final String taskName;

    protected enum TaskKind {
        TODO("todo", "T", null, "todo borrow book"),
        DEADLINE("Deadline", "D", "deadline", "deadline return book /by Sunday"),
        EVENT("Event", "E", "start time", "event book conference /at Mon 2-4pm");

        private final String kind;
        private final String shortName;
        private final String note;
        private final String example;


        TaskKind(String kind, String shortName, String note, String example) {
            this.kind = kind;
            this.shortName = shortName;
            this.note = note;
            this.example = example;
        }

        private String shortName() {
            return shortName;
        }

        protected String note() {
            return note;
        }

        protected String getExample() {
            return example;
        }

        @Override
        public String toString() {
            return kind;
        }
    }

    private Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    private Task(boolean isDone, String taskName) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    private static Task of(TaskKind taskKind, boolean isDone, String taskName) {
        switch (taskKind) {
        case TODO:
            return new Todo(isDone, taskName);
        case DEADLINE:
            return new Deadline(isDone, taskName, null);
        case EVENT:
            return new Event(isDone, taskName, null);
        default:
            throw new IllegalStateException("Unexpected value: " + taskKind);
        }
    }

    private static class Todo extends Task{
        private Todo(String taskName) {
            super(taskName);
        }

        private Todo(boolean isDone, String taskName) {
            super(isDone, taskName);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.TODO;
        }

        @Override
        public String toString() {
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isisDone = super.isDone ? "[X]" : "[ ]";
            return shortName + " " + isisDone + " " + super.taskName;
        }
    }

    private static class Deadline extends Task{
        private final String deadline;
        private Deadline(String taskName, String note) {
            super(taskName);
            this.deadline = note;
        }

        private Deadline(boolean isDone, String taskName, String note) {
            super(isDone, taskName);
            this.deadline = note;
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.DEADLINE;
        }

        @Override
        public String toString() {
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isisDone = super.isDone ? "[X]" : "[ ]";
            String deadline = "(by: " + this.deadline + ")";
            return shortName + " " + isisDone + " " + super.taskName + "" + deadline;
        }
    }

    private static class Event extends Task{
        private final String startTime;
        private Event(String taskName, String note) {
            super(taskName);
            this.startTime = note;
        }

        private Event(boolean isDone, String taskName, String note) {
            super(isDone, taskName);
            this.startTime = note;
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.EVENT;
        }

        @Override
        public String toString() {
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isisDone = super.isDone ? "[X]" : "[ ]";
            String startTime = "(at: " + this.startTime + ")";
            return shortName + " " + isisDone + " " + super.taskName + "" + startTime;
        }
    }

    protected static Task todo(String s) throws DukeException.DukeEmptyTask{
        Task t = new Todo(s);
        if (s != Command.NULL_COMMAND) {
            return t;
        } else {
            throw new DukeException.DukeEmptyTask(TaskKind.TODO);
        }
    }

    protected static Task deadline(String bodyCommand) throws DukeException.DukeEmptyTask, DukeException.DukeEmptyNote{
        if (bodyCommand != Command.NULL_COMMAND) {
            String[] parts = bodyCommand.split("/by ", 2);
            String taskName = parts[0];
            if (parts.length > 1) {
                String deadline = parts[1];
                return new Deadline(taskName, deadline);
            } else {
                throw new DukeException.DukeEmptyNote(TaskKind.DEADLINE);
            }
        } else {
            throw new DukeException.DukeEmptyTask(TaskKind.DEADLINE);
        }
    }

    protected static Task event(String bodyCommand) throws DukeException.DukeEmptyTask, DukeException.DukeEmptyNote{
        if (bodyCommand != Command.NULL_COMMAND) {
            String[] parts = bodyCommand.split("/at ", 2);
            String taskName = parts[0];
            if (parts.length > 1) {
                String startTime = parts[1];
                return new Event(taskName, startTime);
            } else {
                throw new DukeException.DukeEmptyNote(TaskKind.EVENT);
            }
        } else {
            throw new DukeException.DukeEmptyTask(TaskKind.EVENT);
        }
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public abstract TaskKind taskKind();

    public String encode() {
        return this.taskKind().shortName + " , " + this.isDone + " , " + this.taskName + "\n";
    }

    public static Task decode(String hardCode) {
        String[] parts = hardCode.split(" , ");

        TaskKind taskKind;
        Boolean isDone;
        String taskName;
        try {
            switch (parts[0]) {
            case "D":
                taskKind = TaskKind.DEADLINE;
                break;
            case "E":
                taskKind = TaskKind.EVENT;
                break;
            case "T":
            default:
                taskKind = TaskKind.TODO;
            }

            isDone = Boolean.parseBoolean(parts[1]);
            taskName = parts[2];
            return Task.of(taskKind, isDone, taskName);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void add() throws IOException {
        Duke.todoList.add(this);
    }
}
