package Duke;

import java.time.LocalDateTime;

public class Task {

    private final String NAME;

    private boolean isDone;

    private String taskType;

    public Task(String name, String taskType) throws DukeException.NoNameException {
        if (name.replaceAll(" ", "").equals("")) {
            throw new DukeException.NoNameException("Duke.Duke says: Duke.Task cannot have no name");
        }
        this.NAME = name;
        isDone = false;
        this.taskType = taskType;
    }

    public Task(String name, String taskType, boolean done) throws DukeException.NoNameException {
        if (name.replaceAll(" ", "").equals("")) {
            throw new DukeException.NoNameException("Duke.Duke says: Duke.Task cannot have no name");
        }
        this.NAME = name;
        this.isDone = done;
        this.taskType = taskType;
    }

    public String getName() {
        return NAME;
    }

    public void completeTask() {
        isDone = true;
    }

    public String toString() {
        return (taskType + (isDone ? " (done) " : " (not done) ") + NAME);
    }

    public String toStringSave() {
        int doneData = isDone ? 1 : 0;
        char taskTypeData = taskType.equals("#ToDo")
                ? 'T'
                : taskType.equals("#Deadline")
                ? 'D'
                : 'E';
        return taskTypeData + ">" + doneData + ">" + NAME;
    }

    public static class ToDo extends Task {

        public ToDo(String name) throws DukeException.NoNameException {
            super(name, "#ToDo");
        }

        public ToDo(String name, boolean done) throws DukeException.NoNameException {
            super(name, "#ToDo", done);
        }
    }

    public static class Deadline extends Task {

        private final LocalDateTime deadline;

        public Deadline(String name, LocalDateTime deadline) throws DukeException.NoNameException {
            super(name, "#Deadline");
            this.deadline = deadline;
        }

        public Deadline(String name, LocalDateTime deadline, boolean done) throws DukeException.NoNameException {
            super(name, "#Deadline", done);
            this.deadline = deadline;
        }

        public String toString() {
            return super.toString() + " (" + deadline.toString().replace('T', ' ') + ")";
        }

        public String toStringSave() {
            return super.toStringSave() + ">" + deadline;
        }
    }

    public static class Event extends Task {

        //private final LocalDate date;
        private final LocalDateTime when;

        public Event(String name, LocalDateTime when) throws DukeException.NoNameException {
            super(name, "#Event");
            this.when = when;
        }

        public Event(String name, LocalDateTime when, boolean done) throws DukeException.NoNameException {
            super(name, "#Event", done);
            this.when = when;
        }

        public String toString() {
            return super.toString() + " (" + when.toString().replace('T', ' ') + ")";
        }

        public String toStringSave() {
            return super.toStringSave() + ">" + when;
        }

    }

}
