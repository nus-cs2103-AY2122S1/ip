package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.UnknownCommandException;
import jarvis.message.OutputMessage;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Encapsulates a Command which can be executed.
 */
public abstract class Command {

    /**
     * Creates an appropriate command depending on the user input.
     *
     * @param userInput User input.
     * @return A Command that can be executed.
     * @throws JarvisException If the user input does not contain a valid command.
     */
    public static Command createCommand(String userInput) throws JarvisException {
        CommandTypeEnum commandType = Parser.getCommandTypeFromInput(userInput);
        String userInputWithoutCommandPrefix = Parser.getInputWithoutCommandType(userInput);

        switch (commandType) {
        case DEADLINE:
            return new DeadlineCommand(userInputWithoutCommandPrefix);
        case DELETE:
            return new DeleteCommand(userInputWithoutCommandPrefix);
        case DONE:
            return new MarkAsDoneCommand(userInputWithoutCommandPrefix);
        case EVENT:
            return new EventCommand(userInputWithoutCommandPrefix);
        case FIND:
            return new FindCommand(userInputWithoutCommandPrefix);
        case HELP:
            return new HelpCommand();
        case LIST:
            return new ListCommand();
        case TODO:
            return new TodoCommand(userInputWithoutCommandPrefix);
        case UNDO:
            return new UndoCommand(userInputWithoutCommandPrefix);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Executes the specific command.
     *
     * @param taskList The list in which the tasks are stored.
     * @param storage Storage to save or load tasks to hard-disk.
     * @param ui Ui to show information to the user.
     * @return A OutputMessage that needs to be shown to the user after execution.
     * @throws JarvisException If there is an error.
     */
    public abstract OutputMessage execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException;
}
