package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Prints the available commands and guides.
 */
public class HelpCommand extends Command {
    /**
     * The command word that identifies a HelpCommand instance.
     */
    public static final String COMMAND_WORD = "help";

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + " - display the list of available commands";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Available commands:\n👉"
                + TodoCommand.MESSAGE_USAGE + "\n👉"
                + DeadlineCommand.MESSAGE_USAGE + "\n👉"
                + EventCommand.MESSAGE_USAGE + "\n👉"
                + DoneCommand.MESSAGE_USAGE + "\n👉"
                + DeleteCommand.MESSAGE_USAGE + "\n👉"
                + FindCommand.MESSAGE_USAGE + "\n👉"
                + ScheduleCommand.MESSAGE_USAGE + "\n👉"
                + ListCommand.MESSAGE_USAGE + "\n👉"
                + SortCommand.MESSAGE_USAGE + "\n👉"
                + HelpCommand.MESSAGE_USAGE + "\n👉"
                + ExitCommand.MESSAGE_USAGE;
    }
}
