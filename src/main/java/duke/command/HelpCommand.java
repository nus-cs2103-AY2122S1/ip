package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

public class HelpCommand extends Command {
    private CommandsTypes commandType;

    /**
     * Makes command for general help.
     */
    public HelpCommand() {
        this.commandType = CommandsTypes.HELP;
    }

    /**
     * Makes a help command for the different commands.
     *
     * @param commandType type of commmand that help is required.
     * @throws DukeException if invalid command is inputted.
     */
    public HelpCommand(String commandType) throws DukeException {
        switch (commandType) {
            case "add":
                this.commandType = CommandsTypes.ADD;
                break;
            case "delete":
                this.commandType = CommandsTypes.DELETE;
                break;
            case "find":
                this.commandType = CommandsTypes.FIND;
                break;
            case "tag":
                this.commandType = CommandsTypes.TAG;
                break;
            case "done":
                this.commandType = CommandsTypes.MARK_DONE;
                break;
            case "list":
                this.commandType = CommandsTypes.LIST;
                break;
            default:
                throw new DukeException("Invalid command after help. Type help to see which commands can be used");
        }
    }

    public static String getHelpMessage() {
        return "To see how to use different commands, type help {command}. Eg."
                + "\nhelp add, for help on adding tasks"
                + "\nhelp find, for help on finding different tasks"
                + "\nhelp delete, for help on deleting tasks"
                + "\nhelp done, for help on marking tasks as done"
                + "\nhelp list, for help on listing the tasks"
                + "\nhelp tag, for help on tagging tasks";
    }

    /**
     * Returns help for command
     *
     * @param tasks the current list of tasks.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     * @return Ui's message showing how the command is used.
     * @throws DukeException if input is invalid.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, FileManager fileManager) throws DukeException {
        return ui.helpMessage(this.commandType);
    }

    /**
     * Returns if command is exit
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
