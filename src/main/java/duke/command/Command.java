package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.data.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public abstract class Command {
    private static List<String> commandTypes = new ArrayList<>();
    private boolean isExit = false;

    public Command() {}

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

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
        commandTypes.add("find");
    }

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
