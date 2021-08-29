package seedu.duke.command;

import seedu.duke.DateTimeManager;
import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void updateDateTasks(HashMap<LocalDate, ArrayList<Task>> dateTasks,
                                DateTimeManager manager) {
        return;
    }

    public Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public abstract String getUsageMessage();

    public abstract void execute() throws DukeException;

}
