package duke.commands;

import duke.exceptions.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * This is an abstract duke.commands.Command class.
 */
public abstract class Command {

    protected final String command;
    protected final Boolean isExit;
    protected String description;
    protected int index;

    protected Command(String command) { //for list or bye
        this.command = command;
        if (command.equals("bye")) {
            this.isExit = true;
        } else {
            this.isExit = false;
        }
    }

    protected Command(String command, String description) { //For addCommand
        this.command = command;
        this.isExit = false;
        this.description = description;
    }

    protected Command(String command, int index) { //For done or delete
        this.command = command;
        this.isExit = false;
        this.index = index;
    }

    public abstract void execute(TaskList taskList, Storage store, Ui ui)
            throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}
