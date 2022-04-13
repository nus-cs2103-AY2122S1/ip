package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public interface Command {
    public String execute(TaskList taskList, Ui ui, Storage storage);
}
