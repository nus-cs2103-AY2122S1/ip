package duke;

import duke.exception.DukeException;

public abstract class Command {
    protected TaskList tasks;
    protected String input;

    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    public Command(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    public abstract String run() throws DukeException;
}
