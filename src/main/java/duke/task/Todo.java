package duke.task;

import duke.exceptions.DukeIncompleteException;
import duke.exceptions.DukeWrongCommandException;

public class Todo extends Task {
    private final String taskName;
    /**
     * Constructor of a TODO task
     * @param taskName name of the task
     */
    public Todo(String taskName) {
        super(taskName);
        if (taskName.length() == 0) {
            throw new DukeIncompleteException();
        } else if (taskName.contains("/by")) {
            throw new DukeWrongCommandException("Deadline");
        } else if (taskName.contains("/at")) {
            throw new DukeWrongCommandException("Event");
        }
        this.taskName = taskName;
    }


    @Override
    public String toString() {
        assert (!taskName.contains("/by") && !taskName.contains("/at"))
                : "Use the other commands instead";
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return this.toString();
    }

    @Override
    public String getDate() {
        return "There are no date specified with task ";
    }

    @Override
    public Task completedTask() {
        Task newTask = new Todo(taskName);
        newTask.markAsCompleted();
        return newTask;
    }
}
