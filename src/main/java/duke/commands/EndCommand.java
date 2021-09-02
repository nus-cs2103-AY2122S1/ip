package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class EndCommand extends Command {
    private String command;

    public EndCommand(String command) {
        this.command = command;
    }

    public String[] execute(Storage storage, TaskList tasks) {
        return Ui.end();
    }
}
