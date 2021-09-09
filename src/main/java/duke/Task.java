package duke;

import java.time.LocalDateTime;

/**
 * This class encapsulates the tasks: To-do, Deadline, and Event.
 */
public class Task {

    private final String name;

    private boolean isDone;

    private String taskType;

    /**
     * Constructor for newly added tasks.
     * @param name The name of the task.
     * @param taskType The type of the task.
     * @throws DukeException.NoNameException Throws then the task name passed in is empty.
     */
    public Task(String name, String taskType) throws DukeException.NoNameException {
        if (name.replaceAll(" ", "").equals("")) {
            throw new DukeException.NoNameException("Duke.Duke says: Duke.Task cannot have no name");
        }
        this.name = name;
        isDone = false;
        this.taskType = taskType;
    }

    /**
     * Constructor for tasks created from storage.
     * @param name The name of the task.
     * @param taskType The type of the task.
     * @param done Whether the task has been completed or not.
     * @throws DukeException.NoNameException Throws then the task name passed in is empty.
     */
    public Task(String name, String taskType, boolean done) throws DukeException.NoNameException {
        if (name.replaceAll(" ", "").equals("")) {
            throw new DukeException.NoNameException("Duke.Duke says: Duke.Task cannot have no name");
        }
        this.name = name;
        this.isDone = done;
        this.taskType = taskType;
        assert(name != null) : (name!= "");
    }

    public String getName() {
        return name;
    }

    public void completeTask() {
        isDone = true;
    }

    public String toString() {
        return (taskType + (isDone ? " (done) " : " (not done) ") + name);
    }

    public String toStringSave() {
        int doneData = isDone ? 1 : 0;
        char taskTypeData = taskType.equals("#ToDo")
                ? 'T'
                : taskType.equals("#Deadline")
                ? 'D'
                : 'E';
        return taskTypeData + ">" + doneData + ">" + name;
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
            assert (deadline != null);
        }

        public Deadline(String name, LocalDateTime deadline, boolean done) throws DukeException.NoNameException {
            super(name, "#Deadline", done);
            this.deadline = deadline;
            assert (deadline != null);
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
            assert (when != null);
        }

        public Event(String name, LocalDateTime when, boolean done) throws DukeException.NoNameException {
            super(name, "#Event", done);
            this.when = when;
            assert (when != null);
        }

        public String toString() {
            return super.toString() + " (" + when.toString().replace('T', ' ') + ")";
        }

        public String toStringSave() {
            return super.toStringSave() + ">" + when;
        }

    }

}
