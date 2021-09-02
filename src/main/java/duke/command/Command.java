package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public abstract class Command {

    protected final String userCommand;
    protected final String userArgument;


    public Command(String userCommand, String userArgument) {
        this.userArgument = userArgument;
        this.userCommand = userCommand;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

}
