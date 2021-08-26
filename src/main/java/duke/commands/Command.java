package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

abstract public class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    private boolean isExit;

    public enum CommandType {
        ADD,
        DELETE,
        DONE,
        UNDO,
        ERROR,
        EXIT,
        LIST,
        SEARCH
    }

    private CommandType commandType;

    public boolean isExit() {
        return isExit;
    }

    public Command(CommandType commandType, boolean isExit) {
        this.commandType = commandType;
        this.isExit = isExit;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }
}
