package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Optional;

public abstract class Task {
    static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String taskName;
    private final LocalDate date;
    private boolean isDone;

    private Task(String taskName) {
        this(taskName, null, false);
    }

    private Task(String taskName, LocalDate date, boolean isDone) {
        this.taskName = taskName;
        this.date = date;
        this.isDone = isDone;
    }

    private static Task of(TaskKind taskKind, boolean isDone, String taskName, LocalDate date) {
        switch (taskKind) {
        case TODO:
            return new Todo(taskName, isDone);
        case DEADLINE:
            return new Deadline(taskName, date, isDone);
        case EVENT:
            return new Event(taskName, date, isDone);
        default:
            throw new IllegalStateException("Unexpected value: " + taskKind);
        }
    }

    /**
     * Generate a Todo task from user's command
     *
     * @param s user's command
     * @return Todo task from user's command
     * @throws DukeException.DukeEmptyTask
     */
    protected static Task todo(String s) throws DukeException.DukeEmptyTask {
        Task t = new Todo(s);
        if (s != Command.NULL_COMMAND) {
            return t;
        } else {
            throw new DukeException.DukeEmptyTask(TaskKind.TODO);
        }
    }

    /**
     * Generate a Deadline task from user's command
     *
     * @param bodyCommand user's command
     * @return Deadline task from user's command
     * @throws DukeException.DukeEmptyTask
     * @throws DukeException.DukeEmptyNote
     */
    protected static Task deadline(String bodyCommand) throws DukeException {
        if (bodyCommand == Command.NULL_COMMAND) {
            throw new DukeException.DukeEmptyTask(TaskKind.DEADLINE);
        }

        String[] parts = bodyCommand.split(" /by ", 2);
        String taskName = parts[0];
        if (parts.length < 2) {
            throw new DukeException.DukeEmptyNote(TaskKind.DEADLINE);
        }

        try {
            String date = parts[1];
            LocalDate localDate = LocalDate.parse(date, DTF);
            return new Deadline(taskName, localDate, false);
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeParseTaskException(TaskKind.DEADLINE);
        }
    }

    /**
     * Generate an Event task from user's command
     *
     * @param bodyCommand user's command
     * @return Event task from user's command
     * @throws DukeException.DukeEmptyTask
     * @throws DukeException.DukeEmptyNote
     */
    protected static Task event(String bodyCommand) throws DukeException {
        if (bodyCommand == Command.NULL_COMMAND) {
            throw new DukeException.DukeEmptyTask(TaskKind.EVENT);
        }

        String[] parts = bodyCommand.split("/at ", 2);
        String taskName = parts[0];
        if (parts.length < 2) {
            throw new DukeException.DukeEmptyNote(TaskKind.EVENT);
        }

        try {
            String date = parts[1];
            LocalDate localDate = LocalDate.parse(date, DTF);
            return new Event(taskName, localDate, false);
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeParseTaskException(TaskKind.DEADLINE);
        }
    }

    /**
     * Decode the code in hard-drive
     *
     * @param hardCode
     * @return Task
     */
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
                date = LocalDate.parse(parts[3], DTF);
            }

            return Task.of(taskKind, isDone, taskName, date);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Determine if the task is done
     *
     * @return true if the task is done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * @return Task's name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @return Task's kind
     */
    public abstract TaskKind taskKind();

    /**
     * Encode the task to store in hard-drive
     *
     * @return Encoded string
     */
    public String encode() {
        return this.taskKind().shortName + " , " + this.isDone + " , " + this.taskName
                + " , " + Optional.ofNullable(this.date).map(date -> date.format(DTF)).orElse("null");
    }

    /**
     * Make the task done
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Add task into memory
     */
    public void add() {
        Duke.todoList.add(this);
    }

    /**
     * State different kinds of task
     */
    protected enum TaskKind {
        TODO("todo", "T", null, "todo borrow book"),
        DEADLINE("Deadline", "D", "by", "deadline return book /by 2022-02-18"),
        EVENT("Event", "E", "at", "event book conference /at 2022-02-18");

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

    private static class Todo extends Task {
        private Todo(String taskName) {
            super(taskName);
        }

        private Todo(String taskName, boolean isDone) {
            super(taskName, null, isDone);
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

    private static class Deadline extends Task {
        private Deadline(String taskName, LocalDate date, boolean isDone) {
            super(taskName, date, isDone);
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

    private static class Event extends Task {
        private Event(String taskName, LocalDate date, boolean isDone) {
            super(taskName, date, isDone);
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
}
