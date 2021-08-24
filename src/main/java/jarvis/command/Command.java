package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

public abstract class Command {
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

    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException;
}
