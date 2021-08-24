package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.io.IOException;
import java.util.Optional;

public abstract class Task {
    final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private boolean isDone;
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
        this(false, taskName, null);
    }

    private Task(boolean isDone, String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
        this.isDone = isDone;
    }

    private static Task of(TaskKind taskKind, boolean isDone, String taskName, LocalDate date) {
        switch (taskKind) {
        case TODO:
            return new Todo(isDone, taskName);
        case DEADLINE:
            return new Deadline(isDone, taskName, date);
        case EVENT:
            return new Event(isDone, taskName, date);
        default:
            throw new IllegalStateException("Unexpected value: " + taskKind);
        }
    }

    private static class Todo extends Task{
        private Todo(String taskName) {
            super(taskName);
        }

        private Todo(boolean isDone, String taskName) {
            super(isDone, taskName, null);
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
        private Deadline(boolean isDone, String taskName, LocalDate date) {
            super(isDone, taskName, date);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.DEADLINE;
        }

        @Override
        public String toString() {
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isDone = super.isDone ? "[X]" : "[ ]";
            String deadline = "(by: " + super.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
            return shortName + " " + isDone + " " + super.taskName + "" + deadline;
        }
    }

    private static class Event extends Task{
        private Event(boolean isDone, String taskName, LocalDate date) {
            super(isDone, taskName, date);
        }

        @Override
        public TaskKind taskKind() {
            return TaskKind.EVENT;
        }

        @Override
        public String toString() {
            String shortName = "[" + this.taskKind().shortName() + "]";
            String isDone = super.isDone ? "[X]" : "[ ]";
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
                return new Deadline(false, taskName, localDate);
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
                return new Event(false, taskName, localDate);
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
        return this.taskKind().shortName + " , " + this.isDone + " , " + this.taskName +
                " , " + Optional.ofNullable(this.date).map(date -> date.format(dtf)).orElse("null");
    }

    public static Task decode(String hardCode) {
        String[] parts = hardCode.split(" , ");

        TaskKind taskKind;
        Boolean isDone;
        String taskName;
        LocalDate date;

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

            switch (parts[3]) {
            case "null":
                date = null;
                break;
            default:
                date = LocalDate.parse(parts[3], dtf);
            }

            return Task.of(taskKind, isDone, taskName, date);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void done() {
        this.isDone = true;
    };

    public void add() throws IOException {
        Duke.todoList.add(this);
    }
}
