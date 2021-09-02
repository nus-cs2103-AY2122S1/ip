package duke.commands;

import duke.Storage;
import duke.task.TaskList;

public class InvalidCommand extends Command {
    private String command;

    public InvalidCommand(String command) {
        this.command = command;
    }

    public String[] execute(Storage storage, TaskList tasks) {
        return new String[]{"Invalid Command Given!"};
    }
}
