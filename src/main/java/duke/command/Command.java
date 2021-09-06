package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public interface Command {
    public String execute(TaskList taskList, Ui ui, Storage storage);
}
