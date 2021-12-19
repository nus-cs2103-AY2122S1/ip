package kermit.command;

import kermit.KermitException;
import kermit.Response;
import kermit.Storage;
import kermit.TaskList;

public class HelpCommand extends Command {
    private static final String INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    private static final String HELP_MESSAGE = "Type 'help <command>'  to find out more about a command";

    private String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Execute help command.
     * Tells user how to use a command
     *
     * @param taskList Instance of task list used.
     * @param response       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if error completing task or saving task list.
     */
    @Override
    public String execute(TaskList taskList, Response response, Storage storage) throws KermitException {
        switch (command) {
        case "bye":
            return ExitCommand.getSyntax();
        case "list":
            return ListTasksCommand.getSyntax();
        case "done":
            return CompleteTaskCommand.getSyntax();
        case "delete":
            return DeleteTaskCommand.getSyntax();
        case "todo":
            return AddToDoCommand.getSyntax();
        case "event":
            return AddEventCommand.getSyntax();
        case "deadline":
            return AddDeadlineCommand.getSyntax();
        case "find":
            return FindKeywordCommand.getSyntax();
        case "help":
            return HelpCommand.getSyntax();
        case "":
            return HELP_MESSAGE;
        default:
            throw new KermitException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Returns if command is the exit command.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns syntax for command.
     *
     * @return Syntax for how command is used.
     */
    protected static String getSyntax() {
        return "help <command>";
    }


}
