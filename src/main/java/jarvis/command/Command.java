package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Encapsulates a Command which can be executed.
 */
public abstract class Command {

    /**
     * Creates an appropriate command depending on the user input
     *
     * @param userInput User input
     * @return A Command that can be executed
     * @throws JarvisException If the user input does not contain a valid command
     */
    public static Command createCommand(String userInput) throws JarvisException {
        CommandTypeEnum commandType = Parser.getCommandTypeFromInput(userInput);
        String userInputWithoutCommandPrefix = Parser.getInputWithoutCommandType(userInput,
                commandType.getCommandTypeStringLength());

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
        case TODO:
            return new TodoCommand(userInputWithoutCommandPrefix);
        default:
            // When command does not match the other commands above, then it must be list command
            return new ListCommand();
        }
    }

    /**
     * Executes the specific command
     *
     * @param taskList The list in which the tasks are stored
     * @param storage Storage to save or load tasks to hard-disk
     * @param ui Ui to show information to the user
     * @throws JarvisException If there is an error
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException;
}
