package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public abstract class Command {

    protected Ui ui;
    protected TaskList taskList;

    public boolean isExit() {
        return false;
    }

    public boolean updatesTaskList() {
        return false;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public abstract String getUsageMessage();

    public abstract void execute() throws DukeException;

}
