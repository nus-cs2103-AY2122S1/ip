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

    protected Command(String command) {
        this.command = command;
        this.description = "";
        if (command.equals("bye")) {
            this.isExit = true;
        } else {
            this.isExit = false;
        }
    }

    protected Command(String command, String description) {
        this.command = command;
        this.isExit = false;
        this.description = description;
    }

    protected Command(String command, int index) {
        this.command = command;
        this.description = "";
        this.isExit = false;
        this.index = index;
    }

    public abstract void execute(TaskList taskList, Storage store, Ui ui) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Command)) {
            return false;
        } else {
            Command other = (Command) o;
            return this.command.equals(other.command)
                    && this.description.equals(other.description)
                    && (this.index == other.index);
        }
    }
}
