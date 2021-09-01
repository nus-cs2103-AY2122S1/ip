package duke.commands;

import duke.DateTimeHandler;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the bye command, for terminating the program.
 */
public class ByeCommand extends Command {

    public ByeCommand(String arguments) {
        super("");
    }

    @Override
    public Command of(String arguments) {
        return new ByeCommand("");
    }

    @Override
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) {
        return "";
    }

    @Override
    public String getCommandPrefix() {
        return "bye";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
