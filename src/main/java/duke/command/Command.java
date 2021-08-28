package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.data.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents a command made from the terminal by the user.
 */
public abstract class Command {
    private static List<String> commandTypes = new ArrayList<>();
    private boolean isExit = false;

    public Command() {}

    /**
     * Executes the command made by the user.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean based on whether the command is an exit command.
     *
     * @return boolean representing whether the command is an exit command
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets the isExit attribute of the Command class to indicate that the command is an exit command.
     */
    public void setExit() {
        this.isExit = true;
    }

    private static void setDefaultTypes() {
        commandTypes.add("todo");
        commandTypes.add("deadline");
        commandTypes.add("event");
        commandTypes.add("bye");
        commandTypes.add("list");
        commandTypes.add("done");
        commandTypes.add("delete");
    }

    /**
     * Returns a list of strings that represent the command types present.
     *
     * @return a list of strings representing the command types
     */
    public static List<String> getCommandTypes() {
        if (commandTypes.isEmpty()) {
            setDefaultTypes();
        }
        return commandTypes;
    }

    //todo command already present
    public static void addCommandType(String commandType) {
        if (commandTypes.isEmpty()) {
            setDefaultTypes();
        }
        commandTypes.add(commandType);
    }
}
