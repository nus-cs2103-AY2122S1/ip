package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
}
