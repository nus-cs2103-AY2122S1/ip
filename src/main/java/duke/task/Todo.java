package duke.task;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucWrongCommandException;

public class Todo extends Task {
    private final String taskName;
    /**
     * Constructor of a TODO task
     * @param taskName name of the task
     */
    public Todo(String taskName) {
        super(taskName);
        if (taskName.length() == 0) {
            throw new DucIncompleteException();
        } else if (taskName.contains("/by")) {
            throw new DucWrongCommandException("Deadline");
        } else if (taskName.contains("/at")) {
            throw new DucWrongCommandException("Event");
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
