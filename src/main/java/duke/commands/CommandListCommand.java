package duke.commands;

import duke.TaskList;
import duke.gui.MainWindow;

/**
 * THis class handles command meant for marking tasks as done.
 */
public class CommandListCommand implements Command {
    @Override
    public String execute(TaskList taskList) {
        return MainWindow.getCommandList();
    }
}
