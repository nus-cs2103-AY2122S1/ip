package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

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
        LIST
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
