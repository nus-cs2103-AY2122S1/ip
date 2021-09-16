package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;
import katheryne.UnknownCommandException;

/**
 * Abstract command class for other commands to inherit from. Also has the factory method to create new commands.
 */
public abstract class Command {

    /**
     * Factory method which creates a command.
     *
     * @param commandWord The first word in the input
     * @return A command of the type of commandWord
     * @throws KatheryneException An exception thrown according to the message given
     */
    public static Command initialiseCommand(String commandWord, String[] processedRemainingText)
            throws KatheryneException {
        switch (commandWord) {
        case ListCommand.COMMAND:
            return new ListCommand();
        case ExitCommand.COMMAND:
            return new ExitCommand();
//        case FindCommand.COMMAND:
//            return new FindCommand(processedRemainingText);
        case DoneCommand.COMMAND:
            return new DoneCommand(processedRemainingText);
        case DeleteCommand.COMMAND:
            return new DeleteCommand(processedRemainingText);
        case TodoCommand.COMMAND:
            return new TodoCommand(processedRemainingText);
        case EventCommand.COMMAND:
            return new EventCommand(processedRemainingText);
        case DeadlineCommand.COMMAND:
            return new DeadlineCommand(processedRemainingText);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList A container for tasks which contains Katheryne's tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     * @throws KatheryneException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException;
}
