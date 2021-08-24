import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class Task {
    final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private boolean done;
    private final String taskName;
    private final LocalDate date;

    protected enum TaskKind {
        TODO("todo", "T", null, "todo borrow book"),
        DEADLINE("Deadline", "D", "deadline", "deadline return book /by 2022-02-18"),
        EVENT("Event", "E", "start time", "event book conference /at 2022-02-18");

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
        this.done = false;
        this.date = null;
    }

    private Task(String taskName, LocalDate date) {
        this.taskName = taskName;
        this.done = false;
        this.date = date;
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
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isDone = super.done ? "[X]" : "[ ]";
            return shortName + " " + isDone + " " + super.taskName;
        }
    }

    private static class Deadline extends Task{
        private Deadline(String taskName, LocalDate date) {
            super(taskName, date);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.DEADLINE;
        }

        @Override
        public String toString() {
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isDone = super.done ? "[X]" : "[ ]";
            String deadline = "(by: " + super.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
            return shortName + " " + isDone + " " + super.taskName + "" + deadline;
        }
    }

    private static class Event extends Task{
        private Event(String taskName, LocalDate date) {
            super(taskName, date);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.EVENT;
        }

        @Override
        public String toString() {
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isDone = super.done ? "[X]" : "[ ]";
            String startTime = "(at: " + super.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
            return shortName + " " + isDone + " " + super.taskName + "" + startTime;
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
                String date = parts[1];
                LocalDate localDate = LocalDate.parse(date, dtf);
                return new Deadline(taskName, localDate);
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
                String date = parts[1];
                LocalDate localDate = LocalDate.parse(date, dtf);
                return new Event(taskName, localDate);
            } else {
                throw new DukeException.DukeEmptyNote(TaskKind.EVENT);
            }
        } else {
            throw new DukeException.DukeEmptyTask(TaskKind.EVENT);
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
