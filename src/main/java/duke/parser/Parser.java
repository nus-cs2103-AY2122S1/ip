package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.enums.IndexAction;
import duke.enums.TaskType;
import duke.exception.InvalidInputException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MissingKeywordException;

/**
 * The Parser class encapsulates the dealing of user commands by Duke.
 */
public class Parser {

    /**
     * Produces the corresponding type of executable command from the user input.
     *
     * @param fullCommand The full command inputted by the user.
     * @return The corresponding type of executable command from the user input.
     * @throws InvalidInputException If the given full input by the user is invalid.
     */
    public Command parse(String fullCommand) throws InvalidInputException {

        if (fullCommand.startsWith("bye")) {

            return new ExitCommand();

        } else if (fullCommand.startsWith("list")) {

            return new ListCommand();

        } else if (fullCommand.startsWith("todo ")) {

            return createTaskCommand(TaskType.TODO, fullCommand);

        } else if (fullCommand.startsWith("deadline ")) {

            return createTaskCommand(TaskType.DEADLINE, fullCommand);

        } else if (fullCommand.startsWith("event ")) {

            return createTaskCommand(TaskType.EVENT, fullCommand);

        } else if (fullCommand.startsWith("done ")) {

            return createIndexCommand(IndexAction.DONE, fullCommand);

        } else if (fullCommand.startsWith("delete ")) {

            return createIndexCommand(IndexAction.DELETE, fullCommand);

        } else if (fullCommand.startsWith("find ")) {

            return createKeywordCommand(fullCommand);

        } else {

            throw new InvalidInputException();
        }
    }

    /**
     * Creates the corresponding command for the type of task to be added.
     *
     * @param taskType The type of task to be added based on the TaskType enum.
     * @param fullCommand The full input given by the user.
     * @return The corresponding type of executable task command from the user input.
     * @throws MissingDescriptionException If the input is missing a description.
     */
    public Command createTaskCommand(TaskType taskType, String fullCommand)
            throws MissingDescriptionException {

        Command command;
        String[] splitCommand = fullCommand.split(" ");

        checkForDescription(splitCommand, taskType);

        switch (taskType) {
        case TODO:
            String todoDescription = fullCommand.substring(5);
            command = new TodoCommand(todoDescription);
            break;
        case DEADLINE:
            String deadlineDescription = fullCommand.substring(9);
            command = new DeadlineCommand(deadlineDescription);
            break;
        case EVENT:
            String eventDescription = fullCommand.substring(6);
            command = new EventCommand(eventDescription);
            break;
        default:
            command = new ErrorCommand();
            break;
        }

        return command;
    }

    /**
     * Creates the corresponding command for the type of action to be done to the task.
     *
     * @param indexAction The type of action to be executed based on the IndexAction enum.
     * @param fullCommand The full input given by the user.
     * @return The corresponding type of executable task command from the user input.
     * @throws MissingIndexException If the input is missing an index.
     */
    public Command createIndexCommand(IndexAction indexAction, String fullCommand)
            throws MissingIndexException {

        Command command;
        String[] splitCommand = fullCommand.split(" ");

        checkForIndex(splitCommand);

        switch(indexAction) {
        case DONE:
            command = new DoneCommand(splitCommand[1]);
            break;
        case DELETE:
            command = new DeleteCommand(splitCommand[1]);
            break;
        default:
            command = new ErrorCommand();
            break;
        }

        return command;
    }

    /**
     * Creates the corresponding command for the type of action to be done to the task.
     *
     * @param fullCommand The full input given by the user.
     * @return The corresponding type of executable task command from the user input.
     * @throws MissingKeywordException If the input is missing a keyword.
     */
    public Command createKeywordCommand(String fullCommand)
            throws MissingKeywordException {

        String[] splitCommand = fullCommand.split(" ");
        checkForKeyword(splitCommand);
        return new FindCommand(splitCommand[1]);
    }

    /**
     * Checks for the presence of a description for inputs that add tasks.
     *
     * @param splitCommand The full input given by the user broken up into an array.
     * @throws MissingDescriptionException If the input is missing a description.
     */
    public void checkForDescription(String[] splitCommand, TaskType taskType)
            throws MissingDescriptionException {

        if (taskType.equals(TaskType.TODO)) {
            if (splitCommand.length == 1) {
                throw new MissingDescriptionException();
            }
        }

        if (taskType.equals((TaskType.DEADLINE)) || taskType.equals(TaskType.EVENT)) {
            if (splitCommand.length <= 2) {
                throw new MissingDescriptionException();
            }
        }
    }

    /**
     * Checks for the presence of an index for inputs that perform an action to a task.
     *
     * @param splitCommand The full input given by the user broken up into an array.
     * @throws MissingIndexException If the input is missing an index.
     */
    public void checkForIndex(String[] splitCommand)
            throws MissingIndexException {

        if (splitCommand.length == 1) {
            throw new MissingIndexException();
        }
    }

    /**
     * Checks for the presence of a keyword for inputs that perform a search function.
     *
     * @param splitCommand The full input given by the user broken up into an array.
     * @throws MissingKeywordException If the input is missing a keyword.
     */
    public void checkForKeyword(String[] splitCommand)
            throws MissingKeywordException {

        if (splitCommand.length == 1) {
            throw new MissingKeywordException();
        }
    }
}
