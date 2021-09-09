package duke.command;

import duke.taskList.TaskList;

public abstract class Command {
    protected TaskList tasks;
    protected String input;

    public Command(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    public abstract boolean isExitCommand();

//    public abstract String execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
